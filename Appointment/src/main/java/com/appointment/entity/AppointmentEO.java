package com.appointment.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "appointment")
public class AppointmentEO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appt_id")
	private Long apptId;

	@Column(name = "appointment_date")
	private LocalDate appointmentDate;

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

	@ManyToOne
	@JoinColumn(name = "truck_id")
	private TruckEO truckEO;

	@ManyToOne
	@JoinColumn(name = "dc_slot_id")
	private DCSlotEO dcSlotEO;

	@OneToMany(mappedBy = "appointmentEO", fetch = FetchType.LAZY)
	private List<AppointmentPoEO> appointmentPoEOList;

}
