package com.dc.slot.entity;

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
@Table(name = "dc")
public class DistributedCenterEO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dc_id")
	private Integer dcId;

	@Column(name = "dc_number", unique = true, nullable = false)
	private String dcNumber;

	@Column(name = "dc_city", nullable = false)
	private String dcCity;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_ts")
	private LocalDateTime createdTS;

	@Column(name = "last_updated_by")
	private String lastUpdatedBy;

	@Column(name = "last_updated_ts")
	private LocalDateTime lastUpdatedTS;

	@Column(name = "dc_type_id")
	private Integer dcTypeId;
}