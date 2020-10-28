package com.downstream.producer;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.downstream.dto.DownstreamMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SchedulerEventProducer {

	@Autowired
	KafkaTemplate<Long, String> kafkaTemplate;

	@Autowired
	ObjectMapper objectMapper;
	
	public void sendSchedulerEvent(DownstreamMessage downstreamMessage)
			throws JsonProcessingException {
		String topic = "truck-topic";
		Long key = downstreamMessage.getAppointmentNumber();
		String value = objectMapper.writeValueAsString(downstreamMessage);

		ProducerRecord<Long, String> producerRecord = buildProducerRecord(key, topic, value);
		ListenableFuture<SendResult<Long, String>> listenableFuture = kafkaTemplate.send(producerRecord);
		listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Long, String>>() {

			@Override
			public void onFailure(Throwable ex) {
				handleFailure(key, value, ex);
			}

			@Override
			public void onSuccess(org.springframework.kafka.support.SendResult<Long, String> result) {
				handleSuccess(key, value, result);
			}
		});

	}

	private ProducerRecord<Long, String> buildProducerRecord(Long key, String topic, String value) {
		Integer partition = null;
		List<Header> headers = new ArrayList<>();
		headers.add(new RecordHeader("headerKey", "headerValue".getBytes()));
		return new ProducerRecord<Long, String>(topic, partition, key, value, headers);
	}

	protected void handleSuccess(Long key, String value, SendResult<Long, String> result) {
		log.info("Message sent sucessfully. Key : {} value : {}, result : {}", key, value, result.getRecordMetadata());

	}

	protected void handleFailure(Long key, String value, Throwable ex) {
		log.error("Error sending the message and exception is : {}", ex.getMessage());
		try {
			throw ex;
		} catch (Throwable throwable) {
			log.error("Error sending the message and exception is : {}", throwable.getMessage());
		}

	}

}
