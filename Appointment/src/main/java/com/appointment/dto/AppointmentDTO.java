package com.appointment.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class AppointmentDTO {

	private Long apptId;

	private LocalDate appointmentDate;

	private String createdBy;

	private LocalDateTime createdTS;

	private String lastUpdatedBy;

	private LocalDateTime lastUpdatedTS;

	private DistributedCenterDTO distributedCenterDTO;

	private TruckDTO truckDTO;

	private DCSlotDTO dcSlotDTO;
	
	private List<AppointmentPoDTO> appointmentPoDTOList;

}
