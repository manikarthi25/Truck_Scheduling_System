package com.appointment.repository1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appointment.model.POEO;

@Repository
public interface PORepo extends JpaRepository<POEO, String> {

}
