package kz.ktzh.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kz.ktzh.models.BooksInstructionsCatalog;
import kz.ktzh.service.BooksInstructionsCatalogService;

@RestController
@RequestMapping("/bookinscat")
public class BooksInstructionsCatalogController {
	
	@Autowired
	BooksInstructionsCatalogService bookInsServ;
	
	@PostMapping(value = "/viewall")
	public ResponseEntity<List<BooksInstructionsCatalog>> getAllCatalog(){
		return ResponseEntity.ok(bookInsServ.getBookInsList());
	}

}
