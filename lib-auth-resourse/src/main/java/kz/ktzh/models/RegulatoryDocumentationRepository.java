package kz.ktzh.models;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

public interface RegulatoryDocumentationRepository extends CrudRepository<RegulatoryDocumentation, Long>{
	ArrayList<RegulatoryDocumentation> findByCategory(int category);
}
