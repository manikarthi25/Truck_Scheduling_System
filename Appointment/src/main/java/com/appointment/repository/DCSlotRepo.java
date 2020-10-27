package com.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appointment.entity.DCSlotEO;

public interface DCSlotRepo extends JpaRepository<DCSlotEO, Integer> {

}
