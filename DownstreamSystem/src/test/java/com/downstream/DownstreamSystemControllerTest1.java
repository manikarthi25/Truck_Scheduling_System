package com.downstream;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.downstream.controller.DownstreamMessageController;
import com.downstream.dto.DownstreamMessage;
import com.downstream.dto.PurchaseOrder;
import com.downstream.producer.TruckSchedulingEventProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ConditionalOnClass
@WebMvcTest(DownstreamMessageController.class)
@AutoConfigureMockMvc
public class DownstreamSystemControllerTest1 {

	@Autowired
	MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();

	@MockBean
	TruckSchedulingEventProducer schedulerEventProducer;

	@Test
	public void testPostTruckSchedulingEvent() throws Exception, JsonProcessingException {

		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setPoNumber("8888");
		purchaseOrder.setCaseQty(Long.valueOf(99));

		List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
		purchaseOrderList.add(purchaseOrder);

		DownstreamMessage downstreamMessage = DownstreamMessage.builder().dcNumber(7079).eventType("CREATE")
				.truckNumber(Long.valueOf(1234)).appointmentNumber(Long.valueOf(9940)).appointmentDate("2020-10-10")
				.purchaseOrderList(purchaseOrderList).build();

		String json = objectMapper.writeValueAsString(downstreamMessage);

		String url = "/downstream/post/appointment";

		doNothing().when(schedulerEventProducer).sendTruckSchedulingEvent(downstreamMessage);

		mockMvc.perform(post(url).content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}
