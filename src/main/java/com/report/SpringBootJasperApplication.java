package com.report;

import net.sf.jasperreports.engine.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SpringBootJasperApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJasperApplication.class, args);
	}

	@Bean
	CommandLineRunner init(){
		return args -> {
			String destinationPath = "src" + File.separator +
					"main" + File.separator +
					"resources" + File.separator +
					"static" + File.separator +
					"ReportGenerated.pdf";

			String filePath = "src" + File.separator +
					"main" + File.separator +
					"resources" + File.separator +
					"templates" + File.separator +
					"report" + File.separator +
					"Report.jrxml";


			LocalDateTime localDateTime = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

			Map<String, Object> parameters= new HashMap<>();
			parameters.put("folio", "00000045634");
			parameters.put("currentDate", new Date());
			parameters.put("name", "Samantha");
			parameters.put("firstName", "Timoteo");
			parameters.put("lastName", "Kim");
			parameters.put("institucion", "0 Sujeto obligado para pruebas Desarrollo");
			parameters.put("imageDir", "classpath:/static/images/");

			JasperReport report = JasperCompileManager.compileReport(filePath);
			JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
			JasperExportManager.exportReportToPdfFile(print, destinationPath);
			System.out.println("Report Created Successfully");
		};
	}
}
