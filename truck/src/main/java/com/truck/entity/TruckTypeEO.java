package com.truck.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
@Table(name = "truck_type")
public class TruckTypeEO {

	@Id
	@GeneratedValue
	@Column(name = "truck_type_id")
	private Integer truckTypeId;

	@Column(name = "truck_type_name", unique = true, nullable = false)
	private String truckTypeName;

}
