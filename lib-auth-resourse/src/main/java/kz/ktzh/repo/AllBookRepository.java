package kz.ktzh.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import kz.ktzh.models.AllBook;

public interface AllBookRepository extends CrudRepository<AllBook, Long>{
	ArrayList<AllBook> findAll();
	ArrayList<AllBook> findByStatus(int status);

}
