package com.downstream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.TestPropertySource;

import com.downstream.dto.DownstreamMessage;
import com.downstream.dto.PurchaseOrder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EmbeddedKafka(topics = { "truck-topic" }, partitions = 3)
@TestPropertySource(properties = { "spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}",
		"spring.kafka.admin.properties.bootstrap-servers=${spring.embedded.kafka.brokers}" })
public class DownstreamSystemControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	EmbeddedKafkaBroker embeddedKafkaBroker;

	private Consumer<Long, String> consumer;

	@BeforeEach
	void setUp() {
		String group = "group1";
		String autoCommit = "true";
		Map<String, Object> configs = new HashMap<>(
				KafkaTestUtils.consumerProps(group, autoCommit, embeddedKafkaBroker));
		consumer = new DefaultKafkaConsumerFactory<>(configs, new LongDeserializer(), new StringDeserializer())
				.createConsumer();
		embeddedKafkaBroker.consumeFromAllEmbeddedTopics(consumer);
	}

	@AfterEach
	void tearDown() {
		consumer.close();
	}

	@Test
	public void testPostSchedulerEvent() {

		String url = "/downstream/post/appointment";

		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setPoNumber("8888");
		purchaseOrder.setCaseQty(Long.valueOf(99));

		List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
		purchaseOrderList.add(purchaseOrder);

		DownstreamMessage downstreamMessage = DownstreamMessage.builder().dcNumber(7079).eventType("CREATE")
				.truckNumber(Long.valueOf(1234)).appointmentNumber(Long.valueOf(9940)).appointmentDate("2020-10-10")
				.purchaseOrderList(purchaseOrderList).build();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Content-Type", MediaType.APPLICATION_JSON.toString());
		HttpEntity<DownstreamMessage> requestEntity = new HttpEntity<>(downstreamMessage, httpHeaders);
		ResponseEntity<DownstreamMessage> responseSchedulerEvent = restTemplate.exchange(url, HttpMethod.POST,
				requestEntity, DownstreamMessage.class);

		assertEquals(HttpStatus.OK, responseSchedulerEvent.getStatusCode());

		ConsumerRecord<Long, String> consumerRecord = KafkaTestUtils.getSingleRecord(consumer, "truck-topic");
		String expectedRecord = "{\"dcNumber\":7079,\"eventType\":\"CREATE\",\"truckNumber\":1234,\"appointmentNumber\":9940,\"appointmentDate\":\"2020-10-10\",\"purchaseOrderList\":[{\"poNumber\":\"8888\",\"caseQty\":99}]}";
		String actualRecord = consumerRecord.value();
		assertEquals(expectedRecord, actualRecord);

	}

}
