package com.appointment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.appointment.entity.AppointmentPoEO;

@Repository
public interface AppointmentPoRepo extends JpaRepository<AppointmentPoEO, Integer> {
	
	@Query(value = "delete from appointment_po where appt_id = :apptId", nativeQuery = true)
	void deleteByAppointmentId(@Param("apptId") Long apptId);
	
	@Query(value = "select appt_po_id, appt_id, po_number from appointment_po where appt_id = :apptId", nativeQuery = true)
	List<AppointmentPoEO> getAppointmentPoByApptId (@Param("apptId") Long apptId);

}
