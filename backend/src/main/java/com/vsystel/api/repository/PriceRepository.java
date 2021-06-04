package com.vsystel.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsystel.api.models.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

	public List<Price> findByOriginAreaCode(Integer originAreaCode);
}
