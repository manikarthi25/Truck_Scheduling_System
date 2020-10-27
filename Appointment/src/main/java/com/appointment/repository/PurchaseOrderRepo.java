package com.appointment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appointment.entity.PurchaseOrderEO;

@Repository
public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrderEO, Integer> {

	List<PurchaseOrderEO> findByPoNumber(String poNumber);

}
