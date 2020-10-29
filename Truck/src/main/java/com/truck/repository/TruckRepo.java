package com.truck.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truck.entity.TruckEO;

@Repository
public interface TruckRepo extends JpaRepository<TruckEO, Integer> {
	List<TruckEO> findByTruckNumber(Long truckNumber);
}
