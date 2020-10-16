package com.dc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dc.entity.DistributedCenterTypeEO;

@Repository
public interface DistributedCenterTypeRepo extends JpaRepository<DistributedCenterTypeEO, Integer> {

}
