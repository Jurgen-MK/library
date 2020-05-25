package kz.ktzh.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kz.ktzh.models.SearchRequest;
import kz.ktzh.models.SearchRespond;
import kz.ktzh.repo.SearchDAO;
import kz.ktzh.service.SearchService;

@RestController
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
	SearchDAO searchDao;
	
	@Autowired
	SearchService searchServ;
	
	@PostMapping(value = "/searchall")
	public ResponseEntity<List<SearchRespond>> searchAll(){
		return ResponseEntity.ok(searchDao.searchAll());
	}
	
	@PostMapping(value = "/searchallbyname")
	public ResponseEntity<List<SearchRespond>> searchAllByName(@RequestParam String name){
		return ResponseEntity.ok(searchDao.searchAllByName(name));		
	}
	
	@PostMapping(value = "/searchbycategory")
	public ResponseEntity<List<SearchRespond>> searchByCategory(@RequestBody SearchRequest searchRequest){
		return ResponseEntity.ok(searchServ.searchByCategory(searchRequest));
	}

}
