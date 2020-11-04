package kz.ktzh.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kz.ktzh.models.PublishingProducts;
import kz.ktzh.service.PublishingProductsService;

@RestController
@RequestMapping("/pubprod")
public class PublishingProductsController {
	
	@Autowired
	PublishingProductsService publishingProductsServ;
	
	@GetMapping("/getall")
	public ResponseEntity<List<PublishingProducts>> getAll(){
		return ResponseEntity.ok(publishingProductsServ.getAllPublishingProducts());
	}

}
