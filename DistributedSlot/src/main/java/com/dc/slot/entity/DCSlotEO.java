package com.dc.slot.entity;

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
@Table(name = "dc_slot")
public class DCSlotEO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dc_slot_id", unique = true)
	private Integer dcSlotId;

	@Column(name = "dc_time_slot")
	private String dcTimeSlot;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_ts")
	private LocalDateTime createdTS;

	@Column(name = "last_updated_by")
	private String lastUpdatedBy;

	@Column(name = "last_updated_ts")
	private LocalDateTime lastUpdatedTS;

	@ManyToOne
	@JoinColumn(name = "dc_id")
	private DistributedCenterEO distributedCenterEO;

}
