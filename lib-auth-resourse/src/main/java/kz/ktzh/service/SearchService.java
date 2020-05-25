package kz.ktzh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kz.ktzh.models.SearchRequest;
import kz.ktzh.models.SearchRespond;
import kz.ktzh.repo.SearchDAO;

@Service
public class SearchService {

	@Autowired
	SearchDAO searchDao;

	public List<SearchRespond> searchByCategory(SearchRequest searchRequest) {
		List<SearchRespond> srList = new ArrayList<>();
		switch (searchRequest.getCatalogMode()) {
		case "npdntd":			
			srList = searchDao.searchInNpdNtd(searchRequest.getCatalogType(), searchRequest.getSearchString());
			break;
		case "allbook":
			srList = searchDao.searchInAllBook(searchRequest.getCatalogType(), searchRequest.getSearchString());
			break;
		case "article":
			srList = searchDao.searchInArticle(searchRequest.getSearchString());
			break;
		case "video":
			srList = searchDao.searchInVideo(searchRequest.getSearchString());
			break;
		case "periodicalscatalog":
			srList = searchDao.searchInPeriodicalsCatalog(searchRequest.getSearchString());
			break;
		}
		return srList;
	}

}
