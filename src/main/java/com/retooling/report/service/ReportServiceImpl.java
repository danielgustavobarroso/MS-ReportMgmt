package com.retooling.report.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.retooling.report.entity.Chicken;
import com.retooling.report.entity.CurrentStatusReport;
import com.retooling.report.entity.Egg;
import com.retooling.report.entity.Farm;
import com.retooling.report.exception.CurrentStatusReportException;

@Service
public class ReportServiceImpl implements ReportService {

	private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);
	
	@Autowired
	private ApiCall apiCall;

	@Value("${api.microservice.use-date-simulator}")
	private boolean useDateSimulator;

	@Value("${report.path}")
	private String reportPath;

	@Value("${report.file.name}")
	private String reportFileName;
	
	@Value("${report.file.extension}")
	private String reportFileExtension;
	
	@Override
	public CurrentStatusReport getCurrentStatusReport(String id) throws CurrentStatusReportException {
		logger.info("Iniciando servicio getCurrentStatusReport...");
		CurrentStatusReport currentStatusReport = new CurrentStatusReport();
		
		Farm farm = null;
		try {
			farm = apiCall.getFarm(id);
		} catch (Exception ex) {
			throw new CurrentStatusReportException("Error in ms-farm microservice");
		}

		List<Egg> eggs = null;
		try {
			eggs = apiCall.getEggs(id);
		} catch (HttpClientErrorException.NotFound ex) {
			eggs = new ArrayList<>();
		} catch (Exception ex) {
			throw new CurrentStatusReportException("Error in ms-egg microservice");
		}
				
		List<Chicken> chickens = null;
		try {
			chickens = apiCall.getChickens(id);
		} catch (HttpClientErrorException.NotFound ex) {
			chickens = new ArrayList<>();
		} catch (Exception ex) {
			throw new CurrentStatusReportException("Error in ms-chicken microservice");
		}
			
		currentStatusReport.setFarmId(farm.getFarmId());
		currentStatusReport.setFarmName(farm.getName());
		currentStatusReport.setFarmMoney(farm.getMoney());
		currentStatusReport.setChickenLimit(farm.getChickenLimit());
		currentStatusReport.setEggLimit(farm.getEggLimit());
		currentStatusReport.setEggsCount(eggs.size());
		currentStatusReport.setChickensCount(chickens.size());		
		return currentStatusReport;
	}
	
	@Override
	public String generateFile(String id) throws IOException, ParseException {
		logger.info("Iniciando servicio generateFile...");
		
		CurrentStatusReport csf = new CurrentStatusReport();
		csf = this.getCurrentStatusReport(id);
		
		logger.info("Escribiendo el reporte de situación actual...");
	
		Date fileDate;
		if (useDateSimulator) {
			fileDate = apiCall.getDate();
		} else {
			fileDate = new Date();
		}

		String fileSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(fileDate);
		
		File file = new File(reportPath + "/" + reportFileName + "_" + fileSuffix + "." + reportFileExtension);
		
		FileWriter fw = new FileWriter(file);
		
		PrintWriter pw = new PrintWriter(fw);
		
		pw.println("Reporte de Situación Actual de la Granja");
		pw.println("----------------------------------------");
		pw.println("Nombre: " + csf.getFarmName());
		pw.println();
		pw.println("Dinero disponible: " + csf.getFarmMoney());
		pw.println();
		pw.println("Límite de huevos: " + csf.getEggLimit());
		pw.println();
		pw.println("Cantidad de huevos disponibles: " + csf.getEggsCount());
		pw.println();
		if (csf.getEggLimit() == csf.getEggsCount()) {
			pw.println("SE HA ALCANZADO EL LIMITE DE HUEVOS!");
			pw.println();			
		}
		pw.println("Límite de gallinas: " + csf.getChickenLimit());
		pw.println();
		pw.println("Cantidad de gallinas disponibles: " + csf.getChickensCount());
		pw.println();
		if (csf.getChickenLimit() == csf.getChickensCount()) {
			pw.println("SE HA ALCANZADO EL LIMITE DE GALLINAS!");		
		}
		pw.close();
		
		logger.info("Generación de archivo OK");
		return "Generación de archivo OK";
	}
	
}
