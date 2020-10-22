package com.dc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dc.entity.DistributedCenterEO;

@Repository
public interface DCSlotRepo extends JpaRepository<DistributedCenterEO, Integer> {

}