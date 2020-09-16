package kz.ktzh.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kz.ktzh.models.ReportRequest;
import kz.ktzh.service.ReportService;

@RestController
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	ReportService reportService;
	
	@PostMapping("/get")
	public ResponseEntity<ByteArrayResource> getInnovationReportPdf(@RequestBody ReportRequest repRequest) throws Exception {
		byte[] data = reportService.buildReport(repRequest.getRepName(), repRequest.getFromDt(), repRequest.getToDt());

		ByteArrayResource baResource = new ByteArrayResource(data);
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + repRequest.getRepName() + ".pdf").
				contentLength(data.length).body(baResource);
	}

}
