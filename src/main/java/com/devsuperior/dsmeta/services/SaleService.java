package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SummarySaleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleReportDTO> getReport(String minDate, String maxDate, String name, Pageable pageable) {
		LocalDate currentDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

		LocalDate endDate = (maxDate == null || maxDate.isBlank())
				? currentDate
				: LocalDate.parse(maxDate);

		LocalDate startDate = (minDate == null || minDate.isBlank())
				? endDate.minusYears(1)
				: LocalDate.parse(minDate);

		return repository.searchSaleReport(startDate, endDate, name, pageable);
	}

	public List<SummarySaleDTO> getSummary(String minDate, String maxDate) {
		LocalDate currentDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

		LocalDate endDate = (maxDate == null || maxDate.isBlank())
				? currentDate
				: LocalDate.parse(maxDate);

		LocalDate startDate = (minDate == null || minDate.isBlank())
				? endDate.minusYears(1)
				: LocalDate.parse(minDate);

		return repository.searchSummarySale(startDate, endDate);
	}
}
