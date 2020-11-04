package kz.ktzh.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kz.ktzh.models.ExhibitionRespond;
import kz.ktzh.service.ExhibitionService;


@RestController
@RequestMapping("/exhibition")
public class ExhibitionController {
	
	@Autowired
	ExhibitionService exhServ;
	
	@GetMapping(value = "/getall")
	public ResponseEntity<List<ExhibitionRespond>> getAll(){
		return ResponseEntity.ok(exhServ.getAllExhibitionList());		
	}

}
