package kz.ktzh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kz.ktzh.models.TechnicalLessonYearPlan;
import kz.ktzh.repo.YearPlansDAO;

@Service
public class TechnicalLessonYearPlansService {
	
	@Autowired
	YearPlansDAO yearPlansDao;
	
	public List<TechnicalLessonYearPlan> getAllPlans(){
		List<TechnicalLessonYearPlan> tlsypList = yearPlansDao.getPlansList();
		for (TechnicalLessonYearPlan tlsyp : tlsypList) {
			tlsyp.setInfo(yearPlansDao.getPlanInfo(tlsyp.getId()));
		}
		return tlsypList;
	}

}
