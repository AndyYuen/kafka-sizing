package com.redhat.kafkasizing.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.redhat.kafkasizing.model.SizingParams;
import com.redhat.kafkasizing.model.SizingResults;

@Component
public class SizingServiceImpl implements SizingService {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	// retrieve default parameters from applications.properties
	@Value("${app.default.safetyFactor}")
	private float safetyFactor;
	
	@Value("${app.default.vcpusPerBroker}")
	private int vcpusPerBrokerDefault;
	
	@Value("${app.default.vcpuIncrement}")
	private int vcpuIncrement;
	
	@Value("${app.default.memPerBroker}")
	private int memPerBroker;
	
	@Value("${app.default.vcpusPerZkNode}")
	private int vcpusPerZkNode;
	
	@Value("${app.default.memPerZkNode}")
	private int memPerZkNode;
	
	@Value("${app.default.diskPerZkNode}")
	private int diskPerZkNode;
	


	@Override
	public SizingResults sizeKafkaCluster(SizingParams parms) {

		
		// Intermediate computed parameters for verifying the results
		StringBuffer buf = new StringBuffer();
		buf.append("Intermediate variables for result verification: [\n");

		float writes = (float) (parms.getMessageRate() * parms.getMessageSize() / 1000000.0);
		buf.append("\twrites=" + writes + ",\n");
		float netWriteThroughput = (float) (parms.getReplicas() * writes);
		buf.append("\tnetWriteThroughput=" + netWriteThroughput + ",\n");
		float netReadThroughput = (float) ((parms.getConsumerGroups() + parms.getReplicas() - 1) * writes);
		buf.append("\tnetReadThroughput=" + netReadThroughput + ",\n");
		float diskReadWriteThroughput = (float) ((parms.getReplicas() + parms.getLaggingConsumers()) * writes);
		buf.append("\tdiskReadWriteThroughput=" + diskReadWriteThroughput+ ",\n");
		float netUtilisation = (float) (Math.max(netWriteThroughput, netReadThroughput) / (parms.getNetSpeed() / 8. * 1000.));
		buf.append("\tnetUtilisation=" + netUtilisation + ",\n");
		
		// scale up # of CPUs when using higher speed network
		int vcpusPerBroker = vcpusPerBrokerDefault;
		if ((parms.getNetSpeed() > 3.0) && (netUtilisation > 0.3)){
			vcpusPerBroker += vcpuIncrement;
			buf.append("\tBumped up vcpusPerBroker due to high speed network adapter,\n");
		}
		buf.append("\tvcpusPerBroker=" + vcpusPerBroker + ",\n");
		
		float diskUtilisation = (float) ((float) diskReadWriteThroughput / (float) parms.getDiskThroughput());
		buf.append("\tdiskUtilisation=" + diskUtilisation + ",\n");
		float maxUtilisation = Math.max(diskUtilisation, netUtilisation);
		float brokersNeeded = maxUtilisation / parms.getMaxUtil();
		buf.append("\tbrokersNeeded=" + brokersNeeded + ",\n");
		
		// multiply brokersNeeded by safety factor which accounts for protocol overheads and data imbalance
		// because under-provisioning the Kafka cluster and expanding it after going production will be costly
		float brokersNeededByBottleneck = Math.max(brokersNeeded * safetyFactor, (float) (parms.getReplicas() + 1));
		buf.append("\tbrokersNeededByBottleneck=" + brokersNeededByBottleneck + ",\n");
		int zfNodes = (parms.getZkFailures() == 1)? 3: 5;
		
		// skip partition estimation if either ConsumerThroughput or ProducerThroughput is zero
		int producersNeeded = 0, consumersNeeded = 0;
		if ((parms.getTopicThroughput() > 0) && (parms.getProducerThroughput() > 0) && (parms.getConsumerThroughput() > 0)) {
			producersNeeded = (int) Math.ceil(parms.getTopicThroughput() / parms.getProducerThroughput());
			buf.append("\tproducersNeeded=" + producersNeeded + ",\n");
			consumersNeeded = (int) Math.ceil(parms.getTopicThroughput() / parms.getConsumerThroughput());
			buf.append("\tconsumersNeeded=" + consumersNeeded + ",\n");
			buf.append("\tzfNodes=" + zfNodes + "\n]\n");
		}
		
		LOG.info(buf.toString());
		
		// perform kafka sizing using intermediate computed parameters
		SizingResults results = new SizingResults();
		//disk usage in GB
		results.setDailyDiskUsage((int) Math.ceil(writes * 60. * 60. * 24 / 1000. * parms.getReplicas()));
		results.setTotalDiskStorage(results.getDailyDiskUsage() * parms.getRetentionPariod());
		results.setBrokerNodes((int) Math.ceil(brokersNeededByBottleneck));
		results.setCores((int) (results.getBrokerNodes() * vcpusPerBroker) / 2);
		results.setDiskPerBroker((int) Math.ceil((float) results.getTotalDiskStorage() / (float) results.getBrokerNodes() * 1.1));
		results.setZkNodes(((zfNodes > 3) || (results.getBrokerNodes() > 50))? 5: 3);
		results.setMemPerBroker(memPerBroker);
		results.setVcpusPerZkNode(vcpusPerZkNode);
		results.setMemPerZkNode(memPerZkNode);
		results.setDiskPerZkNode(diskPerZkNode);
		results.setVcpusPerBroker(vcpusPerBroker);
		results.setTopicThroughput(parms.getTopicThroughput());
		results.setPartitions((int) Math.ceil(Math.max((float) producersNeeded, (float) consumersNeeded)));
		
		return results;
	}

}
