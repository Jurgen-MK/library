package kz.ktzh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kz.ktzh.models.BooksInstructionsCatalog;
import kz.ktzh.repo.BooksInstructionsCatalogRepository;

@Service
public class BooksInstructionsCatalogService {
	
	@Autowired
	BooksInstructionsCatalogRepository bookInsCatRepo;
	
	public List<BooksInstructionsCatalog> getBookInsList(){
		List<BooksInstructionsCatalog> bookInsList = bookInsCatRepo.findAll();
		return bookInsList;
	}

}
