package com.dc.slot.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class DCTimeSlotPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "dc_id", insertable = false, updatable = false)
	private Integer dcId;

	@Column(name = "dc_time_slot")
	private String dcTimeSlot;

}
