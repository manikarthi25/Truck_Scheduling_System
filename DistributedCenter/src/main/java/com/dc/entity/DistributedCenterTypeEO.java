package com.dc.entity;

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
@Table(name = "dc_type")
public class DistributedCenterTypeEO {

	@Id
	@GeneratedValue
	@Column(name = "dc_type_id")
	private Integer dcTypeId;

	@Column(name = "dc_type_name", unique = true, nullable = false)
	private String dcTypeName;

}
