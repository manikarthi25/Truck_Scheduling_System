package com.vendor.dto;

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
public class VendorDTO {
	
	private Integer vendorId;

	private Integer vendorNumber;

	private String vendorEmail;

	private String vendorContact;

	private String vendorAddress;

	private LocalDateTime createdTS;

	private String lastUpdatedBy;

	private LocalDateTime lastUpdatedTS;

}
