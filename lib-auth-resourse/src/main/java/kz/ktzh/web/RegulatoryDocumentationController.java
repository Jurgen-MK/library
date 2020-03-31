package kz.ktzh.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kz.ktzh.models.RegulatoryDocumentation;
import kz.ktzh.service.RegulatoryDocumentationService;

@RestController
@RequestMapping("/regdoc")
public class RegulatoryDocumentationController {
	
	@Autowired
	RegulatoryDocumentationService regDocServ;
	
	
	@PostMapping(value = "/viewall")
	public ResponseEntity<List<RegulatoryDocumentation>> getAllRegDoc(@RequestParam int category) {
		return ResponseEntity.ok(regDocServ.getRegDocWithFiles(category));
	}

}
