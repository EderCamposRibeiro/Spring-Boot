package project.springboot.controller;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class ReportUtil implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/*Returns our PDF file on Byte for download using a browser*/
	public byte[] createReport(List listData, String report, ServletContext servletContext) throws Exception {
		
		/*Create the data report list with our list of objects to print*/
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listData);
		
		/*Load a jasper compiled file path*/
		String jasperPath = servletContext.getRealPath("reports") 
				+ File.separator + report + ".jasper";
		
		/*Load the jasper file passing the data*/
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperPath, new HashMap(), jrbcds);
		
		/*Exports to byte[] in order to download the PDF*/
		return JasperExportManager.exportReportToPdf(jasperPrint);
		
	}

}
