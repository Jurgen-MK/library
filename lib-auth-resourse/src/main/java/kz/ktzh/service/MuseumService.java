package kz.ktzh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kz.ktzh.models.Museum;
import kz.ktzh.repo.MuseumDAO;

@Service
public class MuseumService {
	
	@Autowired
	MuseumDAO museumDao;
	
	public List<Museum> getAllMuseum(){
		List<Museum> mList = new ArrayList<>();
		mList = museumDao.getMuseumList();
		for (Museum m : mList) {
			m.setExhibitList(museumDao.getExhibitList(m.getId()));
		}
		return mList;
	}

}
