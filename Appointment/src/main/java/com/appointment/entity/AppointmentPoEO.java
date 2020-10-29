package com.appointment.entity;

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
@Table(name = "appointment_po")
public class AppointmentPoEO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appt_po_id")
	private Integer apptPoId;

	@Column(name = "po_number")
	private String poNumber;
	
	@Column(name = "po_qty")
	private Long poQty;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_ts")
	private LocalDateTime createdTS;

	@Column(name = "last_updated_by")
	private String lastUpdatedBy;

	@Column(name = "last_updated_ts")
	private LocalDateTime lastUpdatedTS;

	@ManyToOne
	@JoinColumn(name = "appt_id")
	private AppointmentEO appointmentEO;

}
