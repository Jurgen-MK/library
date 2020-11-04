package kz.ktzh.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kz.ktzh.models.Museum;
import kz.ktzh.service.MuseumService;

@RestController
@RequestMapping("/museum")
public class MuseumController {
	
	@Autowired
	MuseumService museumServ;
	
	@GetMapping("/getall")
	public ResponseEntity<List<Museum>> getAll(){
		return ResponseEntity.ok(museumServ.getAllMuseum());
	}

}
