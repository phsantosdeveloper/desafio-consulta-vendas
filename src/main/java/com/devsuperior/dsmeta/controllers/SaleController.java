package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SummarySaleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleReportDTO>> getReport(@RequestParam(name = "minDate", required = false) String minDate,
	                                                     @RequestParam(name = "maxDate", required = false) String maxDate,
	                                                     @RequestParam(name = "name", defaultValue = "") String name,
	                                                     Pageable pageable) {

		Page<SaleReportDTO> dtos = service.getReport(minDate, maxDate, name, pageable);
		return ResponseEntity.ok().body(dtos);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SummarySaleDTO>> getSummary(@RequestParam(name = "minDate", required = false) String minDate,
	                                                       @RequestParam(name = "maxDate", required = false) String maxDate) {

		List<SummarySaleDTO> dtos = service.getSummary(minDate, maxDate);
		return ResponseEntity.ok().body(dtos);
	}
}

