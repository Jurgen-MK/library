package kz.ktzh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kz.ktzh.models.PeriodicalsCatalogRepository;
import kz.ktzh.models.PeriodicalsCatalog;

@Service
public class PeriodicalsCatalogService {
	
	@Autowired
	PeriodicalsCatalogRepository perCatRepo;
	
	
	public List<PeriodicalsCatalog> getCatalogList(){
		List<PeriodicalsCatalog> perCatList = perCatRepo.findAll();
		return perCatList;
	}
	
	
}
