package com.appointment.repository1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appointment.model.DCSlotsEO;

@Repository
public interface DCSlotsRepo extends JpaRepository<DCSlotsEO, Integer>{

}
