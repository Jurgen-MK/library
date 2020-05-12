package kz.ktzh.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kz.ktzh.models.AllBook;
import kz.ktzh.service.AllBookService;

@RestController
@RequestMapping("/allbook")
public class AllBookController {
	
	@Autowired
	AllBookService allBookServ;
	
	@PostMapping(value = "/viewall")
	public ResponseEntity<List<AllBook>> getAllCatalog(){
		return ResponseEntity.ok(allBookServ.getAllBooks());
	}
	
	@PostMapping(value = "/viewbystatus")
	public ResponseEntity<List<AllBook>> getByStatus(@RequestParam int status){
		return ResponseEntity.ok(allBookServ.getBooksByStatus(status));
	}
	
}
