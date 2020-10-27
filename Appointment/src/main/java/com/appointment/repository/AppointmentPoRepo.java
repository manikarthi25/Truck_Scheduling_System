package com.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appointment.entity.AppointmentPoEO;

@Repository
public interface AppointmentPoRepo extends JpaRepository<AppointmentPoEO, Integer> {

}
