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

	private Long appointmentId;
	private Integer truckNumber;
	private Integer dcNumber;
	private String timeSlot;
	private String appointmentDate;
	private List<String> pos;

}
