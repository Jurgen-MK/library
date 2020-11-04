package kz.ktzh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kz.ktzh.models.PublishingProducts;
import kz.ktzh.repo.PublishingProductsDAO;

@Service
public class PublishingProductsService {
	
	@Autowired
	PublishingProductsDAO publishingProductsDao;
	
	public List<PublishingProducts> getAllPublishingProducts(){
		List<PublishingProducts> ppList = new ArrayList<>();
		ppList = publishingProductsDao.getPubProdList();
		return ppList;		
	}

}
