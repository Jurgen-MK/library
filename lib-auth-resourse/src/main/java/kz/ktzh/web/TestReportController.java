package kz.ktzh.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kz.ktzh.service.JasperReportTest;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/report")
public class TestReportController {
	
	@Autowired
	JasperReportTest jrt;
	
	@GetMapping("/get")
	public ResponseEntity<ByteArrayResource> report() throws JRException {
		byte[] data = jrt.buildReport();		
		ByteArrayResource resource = new ByteArrayResource(data);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "reptor.pdf")
				.contentLength(data.length).body(resource);
	}

}
