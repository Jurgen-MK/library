package kz.ktzh.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kz.ktzh.models.TechnicalLessonPlan;
import kz.ktzh.models.TechnicalLessonPlanInfo;
import kz.ktzh.repo.TechnicalLessonPlansDAO;

@RestController
@RequestMapping("/plans")
public class TechnicalPlanController {
	
	@Autowired
	TechnicalLessonPlansDAO techPlanDAO;
	
	@PostMapping(value = "listtechplantheme")
	public ResponseEntity<List<TechnicalLessonPlan>> getTechPlanList(){
		return ResponseEntity.ok(techPlanDAO.getListTechPlan());
	}
	
	@PostMapping(value = "techplanbyid")
	public ResponseEntity<TechnicalLessonPlanInfo> getTechPlanById(@RequestParam int id) {
		return ResponseEntity.ok(techPlanDAO.getInfoTechPlan(id));
	}

}
