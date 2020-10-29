package com.appointment.dto;

import java.time.LocalDateTime;

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
public class TruckDTO {

	private Integer truckId;

	private Long truckNumber;

	private String truckName;

	private String createdBy;

	private LocalDateTime createdTS;

	private String lastUpdatedBy;

	private LocalDateTime lastUpdatedTS;

}
