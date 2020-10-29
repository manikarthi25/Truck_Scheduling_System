package com.appointment.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DownstreamMessage {

	private Integer dcNumber;

	private String eventType;

	private Long truckNumber;

	private Long appointmentNumber;

	private String appointmentDate;

	private List<PO> purchaseOrderList;

}
