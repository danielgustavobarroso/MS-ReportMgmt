package com.retooling.report.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.retooling.report.entity.Chicken;
import com.retooling.report.entity.CurrentStatusReport;
import com.retooling.report.entity.Egg;
import com.retooling.report.entity.Farm;

@Service
public class ReportServiceImpl implements ReportService {

	private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);
	
	@Autowired
	private ApiCall apiCall;

	@Value("${report.path}")
	private String reportPath;

	@Value("${report.file.name}")
	private String reportFileName;
	
	@Value("${report.file.extension}")
	private String reportFileExtension;
	
	@Override
	public CurrentStatusReport getCurrentStatusReport(String id) {
		logger.info("Service - Llamando a método getCurrentStatusReport...");
		CurrentStatusReport currentStatusReport = new CurrentStatusReport();
		
		Farm farm = apiCall.getFarm(id);

		List<Egg> eggs = apiCall.getEggs(id);
		
		List<Chicken> chickens = apiCall.getChickens(id);
			
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
		logger.info("Service - Llamando a método generateFile...");
		
		CurrentStatusReport csf = new CurrentStatusReport();
		csf = this.getCurrentStatusReport(id);
		
		logger.info("Escribiendo el reporte de situación actual...");
	
		Date fileDate = apiCall.getDate();

		String fileSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(fileDate);
		
		File file = new File(reportPath + "/" + reportFileName + "_" + fileSuffix + "." + reportFileExtension);
		
		FileWriter fw = new FileWriter(file);
		
		PrintWriter pw = new PrintWriter(fw);
		
		writeInFile(pw, csf);
		
		pw.close();
		
		logger.info("Generación de archivo OK");
		return "Generación de archivo OK";
	}
	
	private void writeInFile(PrintWriter pw, CurrentStatusReport csf) {
		pw.println("Reporte de Situación Actual de la Granja");
		pw.println("----------------------------------------");
		pw.println("Nombre: " + csf.getFarmName());
		pw.println();
		pw.println("Dinero disponible ($): " + csf.getFarmMoney());
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
	}
	
}
