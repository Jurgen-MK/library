package kz.ktzh.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kz.ktzh.models.UserInfo;
import kz.ktzh.repo.UserInfoRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRSaver;

@Service
public class JasperReportTest {
	
	@Autowired
	UserInfoRepository userInfoRepo;
	
	public byte[] buildReport() throws JRException {
		List<UserInfo> userInfo = StreamSupport.stream(userInfoRepo.findAll().spliterator(), false)
			    .collect(Collectors.toList());
		InputStream userReportStream
		  = getClass().getResourceAsStream("/userReport.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(userReportStream);
		JRSaver.saveObject(jasperReport, "userReport.jasper");
		JRBeanCollectionDataSource userDS = new JRBeanCollectionDataSource(userInfo);
		JasperPrint print = JasperFillManager.fillReport(jasperReport, new HashMap<>(), userDS);		
		return JasperExportManager.exportReportToPdf(print);
	}

}
