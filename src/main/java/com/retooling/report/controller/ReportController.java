package com.retooling.report.controller;

import java.io.IOException;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retooling.report.entity.CurrentStatusReport;
import com.retooling.report.exception.CurrentStatusReportException;
import com.retooling.report.service.ReportService;

@RestController
@RequestMapping("/api/v1")
public class ReportController {

	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
	
	@Autowired
	ReportService service;
	
	//Obtener reporte por id de granja
	@GetMapping("reports/currentStatusReport/{id}")
	public ResponseEntity<CurrentStatusReport> createCurrentStatusReport(@PathVariable("id") String id) throws CurrentStatusReportException {
		logger.info("Iniciando servicio createCurrentStatusReport...");
		return new ResponseEntity<>(service.getCurrentStatusReport(id), HttpStatus.OK);
	}
	
	@PostMapping("reports/currentStatusReport/generateFile")
	public ResponseEntity<String> generateFileCurrentStatusReport(@RequestBody String id) throws IOException, ParseException {
		logger.info("Iniciando servicio generateFileCurrentStatusReport...");
		return new ResponseEntity<>(service.generateFile(id), HttpStatus.OK);
	}
	
}
