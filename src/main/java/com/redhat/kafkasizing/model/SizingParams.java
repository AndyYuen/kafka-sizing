package com.redhat.kafkasizing.model;

import java.io.Serializable;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

// class containing all the input sizing parameters
public class SizingParams implements Serializable {
	

	@Digits(fraction = 0, integer = 10)
	@DecimalMin(value = "0", inclusive = false)
	private int messageRate;
	

	@Digits(fraction = 0, integer = 10)
	@DecimalMin(value = "0", inclusive = false)
	private int messageSize;
	

	@Digits(fraction = 0, integer = 2)
	@DecimalMin(value = "0", inclusive = false)
	private int replicas;
	

	@Digits(fraction = 1, integer = 3)
	@DecimalMin(value = "0.", inclusive = false)
	private float netSpeed;
	

	@Digits(fraction = 0, integer = 5)
	@DecimalMin(value = "0", inclusive = false)
	private int diskThroughput;
	

	@Digits(fraction = 2, integer = 1)
	@DecimalMin(value = "0.00", inclusive = false)
	@DecimalMax(value = "1.00", inclusive = true)
	private float maxUtil;
	

	@Digits(fraction = 0, integer = 3)
	@DecimalMin(value = "0", inclusive = false)
	private int consumerGroups;
	

	@Digits(fraction = 0, integer = 3)
	@DecimalMin(value = "0", inclusive = true)
	private int laggingConsumers;
	

	@Digits(fraction = 0, integer = 6)
	@DecimalMin(value = "0", inclusive = false)
	private int retentionPariod;

	@Digits(fraction = 0, integer = 5)
	@DecimalMin(value = "0", inclusive = true)
	private int producerThroughput;
	

	@Digits(fraction = 0, integer = 5)
	@DecimalMin(value = "0", inclusive = true)
	private int consumerThroughput;
	

	@Digits(fraction = 0, integer = 1)
	@DecimalMin(value = "0", inclusive = false)
	@DecimalMax(value = "2", inclusive = true)
	private int zkFailures;
	
	@Digits(fraction = 0, integer = 5)
	@DecimalMin(value = "0", inclusive = true)
	private int topicThroughput;
	
	

	public int getTopicThroughput() {
		return topicThroughput;
	}



	public void setTopicThroughput(int topicThroughput) {
		this.topicThroughput = topicThroughput;
	}



	public int getMessageRate() {
		return messageRate;
	}



	public void setMessageRate(int messageRate) {
		this.messageRate = messageRate;
	}



	public int getMessageSize() {
		return messageSize;
	}



	public void setMessageSize(int messageSize) {
		this.messageSize = messageSize;
	}



	public int getReplicas() {
		return replicas;
	}



	public void setReplicas(int replicas) {
		this.replicas = replicas;
	}



	public float getNetSpeed() {
		return netSpeed;
	}



	public void setNetSpeed(float netSpeed) {
		this.netSpeed = netSpeed;
	}



	public int getDiskThroughput() {
		return diskThroughput;
	}



	public void setDiskThroughput(int diskThroughput) {
		this.diskThroughput = diskThroughput;
	}



	public float getMaxUtil() {
		return maxUtil;
	}



	public void setMaxUtil(float maxUtil) {
		this.maxUtil = maxUtil;
	}



	public int getConsumerGroups() {
		return consumerGroups;
	}



	public void setConsumerGroups(int consumerGroups) {
		this.consumerGroups = consumerGroups;
	}



	public int getLaggingConsumers() {
		return laggingConsumers;
	}



	public void setLaggingConsumers(int laggingConsumers) {
		this.laggingConsumers = laggingConsumers;
	}



	public int getRetentionPariod() {
		return retentionPariod;
	}



	public void setRetentionPariod(int retentionPariod) {
		this.retentionPariod = retentionPariod;
	}



	public int getProducerThroughput() {
		return producerThroughput;
	}



	public void setProducerThroughput(int producerThroughput) {
		this.producerThroughput = producerThroughput;
	}



	public int getConsumerThroughput() {
		return consumerThroughput;
	}



	public void setConsumerThroughput(int consumerThroughput) {
		this.consumerThroughput = consumerThroughput;
	}



	public int getZkFailures() {
		return zkFailures;
	}



	public void setZkFailures(int zkFailures) {
		this.zkFailures = zkFailures;
	}



	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("SizingParams [\n");
		buf.append("\tmessageRate=" + messageRate + ",\n");
		buf.append("\tmessageSize=" + messageSize + ",\n");
		buf.append("\treplicas=" + replicas + ",\n");
		buf.append("\tnetSpeed=" + netSpeed + ",\n");
		buf.append("\tdiskThroughput=" + diskThroughput + ",\n");
		buf.append("\tmaxUtil=" + maxUtil + ",\n");
		buf.append("\tconsumerGroups=" + consumerGroups + ",\n");
		buf.append("\tlaggingConsumers=" + laggingConsumers + ",\n");
		buf.append("\tretentionPariod=" + retentionPariod + ",\n");
		buf.append("\ttopicThroughput=" + topicThroughput + ",\n");
		buf.append("\tproducerThroughput=" + producerThroughput + ",\n");
		buf.append("\tconsumerThroughput=" + consumerThroughput + ",\n");
		buf.append("\tzkFailures=" + zkFailures + "\n]\n");
		
		return buf.toString();
	}

}
