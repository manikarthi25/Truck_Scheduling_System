package com.po.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.po.entity.PurchaseOrderEO;

@Repository
public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrderEO, Integer> {

}
