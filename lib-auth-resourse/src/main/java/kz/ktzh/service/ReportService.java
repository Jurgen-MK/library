package kz.ktzh.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kz.ktzh.models.InnovationReport;
import kz.ktzh.repo.InnovationReportDAO;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;

@Service
public class ReportService {
	
	@Autowired
	InnovationReportDAO irDAO;
	
	public byte[] buildReport(String repName, String fromDt, String toDt) throws JRException {
		System.out.println("TEST " + repName);
		List<InnovationReport> innoList = StreamSupport.stream(irDAO.getInnovationReport(fromDt, toDt).spliterator(), false).collect(Collectors.toList());
//		InputStream reportStream = getClass().getResourceAsStream("/innoReport.jrxml");
		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile("D:\\innoReport\\innoReport.jasper"); //JasperCompileManager.compileReport(reportStream);
//		JRSaver.saveObject(jasperReport, "D:\\innoReport\\innoReport.jasper");
		JRBeanCollectionDataSource innoDS = new JRBeanCollectionDataSource(innoList);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(JRParameter.REPORT_LOCALE, new Locale("ru", "RU"));
//		parameters.put("CollectionBeanParam", innoList);
		JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, innoDS); //new JREmptyDataSource()
		return JasperExportManager.exportReportToPdf(print);		
	}

}
