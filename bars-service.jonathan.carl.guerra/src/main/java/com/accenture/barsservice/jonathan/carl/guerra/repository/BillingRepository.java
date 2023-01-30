package com.accenture.barsservice.jonathan.carl.guerra.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.barsservice.jonathan.carl.guerra.entity.Billing;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Integer>{
	
	public Billing findByBillingCycleAndStartDateAndEndDate(int billingCycle, 
			LocalDate startDate, LocalDate endDate);
	
}