package com.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.appointment.entity.AppointmentEO;

@Repository
public interface AppointmentRepo extends JpaRepository<AppointmentEO, Long> {
	
	@Query(value = "select count(*) from appointment where slot_id = :dcSlotId", nativeQuery = true)
	int getCountBySlotId(@Param("dcSlotId") int dcSlotId);

}
