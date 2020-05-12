package kz.ktzh.models;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

public interface BooksInstructionsCatalogRepository extends CrudRepository<BooksInstructionsCatalog, Long>{
	ArrayList<BooksInstructionsCatalog> findAll();
}
