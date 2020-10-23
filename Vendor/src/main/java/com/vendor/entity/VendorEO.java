package com.vendor.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name = "vendor")
public class VendorEO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "vendor_id", unique = true)
	private Integer vendorId;

	@Column(name = "vendor_number", unique = true)
	private Integer vendorNumber;

	@Column(name = "vendor_email")
	private String vendorEmail;

	@Column(name = "vendor_contact")
	private String vendorContact;

	@Column(name = "vendor_address")
	private String vendorAddress;

	@Column(name = "created_ts")
	private LocalDateTime createdTS;

	@Column(name = "last_updated_by")
	private String lastUpdatedBy;

	@Column(name = "last_updated_ts")
	private LocalDateTime lastUpdatedTS;

}
