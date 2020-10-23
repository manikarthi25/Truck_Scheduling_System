package com.po.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.po.entity.PurchaseOrderEO;
import com.po.model.PurchaseOrder;
import com.po.repository.PurchaseOrderRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PurchaseOrderService {

	@Autowired
	private PurchaseOrderRepo purchaseOrderRepo;

	@KafkaListener(topics = "po-topic", groupId = "po-consumer", containerFactory = "kafkaListenerContainerFactory")
	public void listenToKafkaPOTopic(PurchaseOrder purchaseOrder) {
		ModelMapper modelMapper = new ModelMapper();
		try {
			PurchaseOrderEO purchaseOrderEO = modelMapper.map(purchaseOrder, PurchaseOrderEO.class);
			purchaseOrderRepo.save(purchaseOrderEO);
		} catch (Exception e) {
			log.error(
					"PO consumer from kafka : PurchaseOrderService : consumerPOMessage : purchaseOrder : {},  Exception :{}",
					purchaseOrder, e.getMessage());
		}
	}
}
