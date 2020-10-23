package com.truck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truck.entity.TruckTypeEO;

@Repository
public interface TruckTypeRepo extends JpaRepository<TruckTypeEO, Integer> {

}
