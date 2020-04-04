package kz.ktzh.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kz.ktzh.models.PeriodicalsCatalog;
import kz.ktzh.service.PeriodicalsCatalogService;

@RestController
@RequestMapping("/percat")
public class PeriodicalsCatalogController {
	
	@Autowired
	PeriodicalsCatalogService perCatServ;
	
	@PostMapping(value = "/viewall")
	public ResponseEntity<List<PeriodicalsCatalog>> getAllCatalog(){
		return ResponseEntity.ok(perCatServ.getCatalogList());
	}

}
