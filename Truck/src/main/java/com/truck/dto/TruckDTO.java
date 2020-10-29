package com.truck.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

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

	@NotNull(message = "Truck Number should not be empty")
	private Long truckNumber;

	@NotNull(message = "Truck Name should not be empty")
	private String truckName;

	private String createdBy;

	private LocalDateTime createdTS;

	private String lastUpdatedBy;

	private LocalDateTime lastUpdatedTS;

	private TruckTypeDTO truckTypeDTO;

}
