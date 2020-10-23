package com.po.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.po.model.PurchaseOrder;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/po/producer")
@Slf4j
public class PoProducerController {

	@Value("${kafka.topic}")
	private String poTopicName;

	@Autowired
	private KafkaTemplate<String, PurchaseOrder> kafkaTemplate;

	@PostMapping
	public void uploadPO(@RequestBody PurchaseOrder purchaseOrder) {
		StringBuilder sb = new StringBuilder();
		final ListenableFuture<SendResult<String, PurchaseOrder>> producer = kafkaTemplate.send(poTopicName,
				purchaseOrder);
		producer.addCallback(new ListenableFutureCallback<SendResult<String, PurchaseOrder>>() {
			@Override
			public void onFailure(Throwable throwable) {
				sb.append(throwable.getMessage());
				throwable.printStackTrace();
			}

			@Override
			public void onSuccess(SendResult<String, PurchaseOrder> result) {
				log.info("Data successfully registered with Kafka Topic..");
				log.info("Partition -> " + result.getRecordMetadata().partition());
				log.info("Offset -> " + result.getRecordMetadata().offset());
			}
		});
	}
}
