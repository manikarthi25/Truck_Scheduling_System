package com.vendor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vendor.entity.VendorEO;

@Repository
public interface VendorRepo extends JpaRepository<VendorEO, Integer> {

	List<VendorEO> findByVendorNumber(Integer vendorNumber);

}
