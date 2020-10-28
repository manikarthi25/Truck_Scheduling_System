package com.downstream.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DownstreamMessage {

	private Integer dcNumber;

	private String eventType;

	private Long truckNumber;

	private Long appointmentNumber;

	private String appointmentDate;

	private List<PurchaseOrder> purchaseOrderList;

}
