package kz.ktzh.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import kz.ktzh.models.BooksInstructionsCatalog;

public interface BooksInstructionsCatalogRepository extends CrudRepository<BooksInstructionsCatalog, Long>{
	ArrayList<BooksInstructionsCatalog> findAll();
}
