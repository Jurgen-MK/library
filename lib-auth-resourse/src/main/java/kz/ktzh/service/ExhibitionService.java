package kz.ktzh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kz.ktzh.models.ExhibitionRespond;
import kz.ktzh.repo.ExhibitionDAO;

@Service
public class ExhibitionService {

	@Autowired
	ExhibitionDAO exhibitionDao;
	
	public List<ExhibitionRespond> getAllExhibitionList(){
		List<ExhibitionRespond> exList = new ArrayList<>();
		exList = exhibitionDao.getExhibitionList();
		for (ExhibitionRespond exresp : exList) {
			exresp.setFileslist(exhibitionDao.getFilesList(exresp.getId()));
		}
		return exList;
	}
	
	
}
