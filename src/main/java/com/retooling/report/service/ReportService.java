package com.retooling.report.service;

import java.io.IOException;
import java.text.ParseException;

import com.retooling.report.entity.CurrentStatusReport;
import com.retooling.report.exception.CurrentStatusReportException;

public interface ReportService {

	public CurrentStatusReport getCurrentStatusReport(String id) throws CurrentStatusReportException;
	
	public String generateFile(String id) throws IOException, ParseException;
	
}
