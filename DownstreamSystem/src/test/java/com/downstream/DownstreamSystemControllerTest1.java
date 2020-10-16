package com.downstream;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.downstream.controller.DownstreamMessageController;
import com.downstream.dto.DownstreamMessage;
import com.downstream.producer.SchedulerEventProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DownstreamMessageController.class)
@AutoConfigureMockMvc
public class DownstreamSystemControllerTest1 {

	@Autowired
	MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();

	@MockBean
	SchedulerEventProducer schedulerEventProducer;

	@Test
	public void testPostSchedulerEvent() throws Exception, JsonProcessingException {

		DownstreamMessage downstreamMessage = DownstreamMessage.builder().dcNumber("DC7079").eventType("CREATE")
				.truckNumber(Long.valueOf(9999)).caseQty(Long.valueOf(9999)).appointmentNumber(Long.valueOf(9999))
				.appointmentDate("2020-10-10").poNumber(Long.valueOf(9999)).build();

		String json = objectMapper.writeValueAsString(downstreamMessage);

		String url = "/scheduler/postschedulerevent";

		doNothing().when(schedulerEventProducer).sendSchedulerEvent(downstreamMessage);

		mockMvc.perform(post(url).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
