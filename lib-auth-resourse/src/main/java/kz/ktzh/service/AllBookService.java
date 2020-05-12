package kz.ktzh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kz.ktzh.models.AllBook;
import kz.ktzh.repo.AllBookRepository;

@Service
public class AllBookService {
	
	@Autowired
	AllBookRepository allBookRepo;
	
	public List<AllBook> getAllBooks(){
		List<AllBook> booklist = allBookRepo.findAll();
		return booklist;
	}
	
	public List<AllBook> getBooksByStatus(int status){
		List<AllBook> booklist = allBookRepo.findByStatus(status);
		return booklist;
	}
	

}
