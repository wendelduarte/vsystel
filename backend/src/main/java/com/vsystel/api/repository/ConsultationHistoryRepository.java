package com.vsystel.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsystel.api.models.PriceConsultResult;

@Repository
public interface ConsultationHistoryRepository extends JpaRepository<PriceConsultResult, Long> {

	public Page<PriceConsultResult> findAll(Pageable pageable);
}
