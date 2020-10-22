package com.truck.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "truck")
public class TruckEO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "truck_id")
	private Integer truckId;

	@Column(name = "truck_number", unique = true, nullable = false)
	private Integer truckNumber;

	@Column(name = "truck_name", nullable = false)
	private String truckName;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_ts")
	private LocalDateTime createdTS;

	@Column(name = "last_updated_by")
	private String lastUpdatedBy;

	@Column(name = "last_updated_ts")
	private LocalDateTime lastUpdatedTS;

	@ManyToOne
	@JoinColumn(name = "truck_type_id")
	private TruckTypeEO truckTypeEO;

}
