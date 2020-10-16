package com.downstream.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DownstreamMessage {

	private String dcNumber;

	private String eventType;

	private Long poNumber;

	private Long truckNumber;

	private Long caseQty;

	private Long appointmentNumber;

	private String appointmentDate;

}
