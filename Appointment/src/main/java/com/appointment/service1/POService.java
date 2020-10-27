package com.appointment.service1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appointment.repository1.PORepo;

@Service
public class POService {
	
	@Autowired
	PORepo poRepository;
	
	public boolean isPOExists (String poNumber) {
		return poRepository.existsById(poNumber);
	}

}
