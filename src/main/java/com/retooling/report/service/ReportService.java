package com.retooling.report.service;

import java.io.IOException;
import java.text.ParseException;

import com.retooling.report.entity.CurrentStatusReport;

public interface ReportService {

	public CurrentStatusReport getCurrentStatusReport(String id);
	
	public String generateFile(String id) throws IOException, ParseException;
	
}
