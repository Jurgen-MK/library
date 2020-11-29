package kz.ktzh.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kz.ktzh.models.TechnicalLessonYearPlan;
import kz.ktzh.service.TechnicalLessonYearPlansService;

@RestController
@RequestMapping("/TechnicalLessonYearPlans")
public class TechnicalLessonYearPlansController {
	
	@Autowired
	TechnicalLessonYearPlansService tlypService;
	
	@GetMapping("/getall")
	public ResponseEntity<List<TechnicalLessonYearPlan>> getAllPlans(){
		return ResponseEntity.ok(tlypService.getAllPlans());
	}

}
