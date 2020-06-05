package kz.ktzh.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kz.ktzh.models.News;
import kz.ktzh.repo.NewsDAO;


@RestController
@RequestMapping("/news")
public class NewsController {
	
	@Autowired
	NewsDAO newsDao;
	
	@PostMapping(value = "/listnews")
	public ResponseEntity<List<News>> getNewsList(){
		return ResponseEntity.ok(newsDao.getNewsList());
	}
	
	@PostMapping(value = "/newstextbyid", produces="text/plain")
	public String getNewsTextById(@RequestParam int id) {
		return newsDao.getNewsText(id);
	}

}
