package com.redhat.kafkasizing.model;

// class containing the kafka sizing results
public class SizingResults {

	private int dailyDiskUsage;
	private int totalDiskStorage;
	private int zkNodes;
	private int brokerNodes;
	private int cores;
	private int partitions;
	private int diskPerBroker;
	private int memPerBroker;
	private int vcpusPerZkNode;
	private int memPerZkNode;
	private int vcpusPerBroker;
	private int diskPerZkNode;
	private int topicThroughput;

	
	
	public int getTopicThroughput() {
		return topicThroughput;
	}



	public void setTopicThroughput(int topicThroughput) {
		this.topicThroughput = topicThroughput;
	}



	public int getDiskPerZkNode() {
		return diskPerZkNode;
	}



	public void setDiskPerZkNode(int diskPerZkNode) {
		this.diskPerZkNode = diskPerZkNode;
	}



	public int getMemPerBroker() {
		return memPerBroker;
	}



	public void setMemPerBroker(int memPerBroker) {
		this.memPerBroker = memPerBroker;
	}



	public int getVcpusPerZkNode() {
		return vcpusPerZkNode;
	}



	public void setVcpusPerZkNode(int vcpusPerZkNodes) {
		this.vcpusPerZkNode = vcpusPerZkNodes;
	}



	public int getMemPerZkNode() {
		return memPerZkNode;
	}



	public void setMemPerZkNode(int memPerZkNode) {
		this.memPerZkNode = memPerZkNode;
	}



	public int getVcpusPerBroker() {
		return vcpusPerBroker;
	}



	public void setVcpusPerBroker(int vcpusPerBroker) {
		this.vcpusPerBroker = vcpusPerBroker;
	}


	
	public int getDiskPerBroker() {
		return diskPerBroker;
	}



	public void setDiskPerBroker(int diskPerBroker) {
		this.diskPerBroker = diskPerBroker;
	}



	public int getDailyDiskUsage() {
		return dailyDiskUsage;
	}



	public void setDailyDiskUsage(int dailyDiskUsage) {
		this.dailyDiskUsage = dailyDiskUsage;
	}



	public int getTotalDiskStorage() {
		return totalDiskStorage;
	}



	public void setTotalDiskStorage(int totalDiskStorage) {
		this.totalDiskStorage = totalDiskStorage;
	}



	public int getZkNodes() {
		return zkNodes;
	}



	public void setZkNodes(int zkNodes) {
		this.zkNodes = zkNodes;
	}



	public int getBrokerNodes() {
		return brokerNodes;
	}



	public void setBrokerNodes(int btokerNodes) {
		this.brokerNodes = btokerNodes;
	}



	public int getCores() {
		return cores;
	}



	public void setCores(int cores) {
		this.cores = cores;
	}



	public int getPartitions() {
		return partitions;
	}



	public void setPartitions(int partitions) {
		this.partitions = partitions;
	}



	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("SizingResults [\n");
		buf.append("\tdailyDiskUsage=" + dailyDiskUsage + ",\n");
		buf.append("\ttotalDiskStorage=" + totalDiskStorage + ",\n");
		buf.append("\tzkNodes=" + zkNodes + ",\n");
		buf.append("\tbrokerNodes=" + brokerNodes + ",\n");
		buf.append("\tcores=" + cores + ",\n");
		buf.append("\tdiskPerBroker=" + diskPerBroker + ",\n");
		buf.append("\tmemPerBroker=" + memPerBroker + ",\n");
		buf.append("\tvcpusPerZkNodes=" + vcpusPerZkNode + ",\n");
		buf.append("\tmemPerZkNode=" + memPerZkNode + ",\n");
		buf.append("\tvcpusPerBroker=" + vcpusPerBroker + ",\n");
		buf.append("\tdiskPerZkNode=" + diskPerZkNode + ",\n");
		buf.append("\ttopicThroughput=" + topicThroughput + ",\n");
		buf.append("\tpartitions=" + partitions + "\n]\n");
		
		return buf.toString();
	}

}
