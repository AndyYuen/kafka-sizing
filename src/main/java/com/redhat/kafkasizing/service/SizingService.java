package com.redhat.kafkasizing.service;

import com.redhat.kafkasizing.model.SizingParams;
import com.redhat.kafkasizing.model.SizingResults;


public interface SizingService {
	
	// size the Kafka cluster based on sizing parameters and return the sizing results
	public SizingResults sizeKafkaCluster(SizingParams parms);

}
