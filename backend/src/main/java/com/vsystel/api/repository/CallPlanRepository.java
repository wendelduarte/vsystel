package com.vsystel.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsystel.api.models.CallPlan;

@Repository
public interface CallPlanRepository extends JpaRepository<CallPlan, Long> {

}
