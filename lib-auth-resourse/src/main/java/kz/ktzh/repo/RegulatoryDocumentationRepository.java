package kz.ktzh.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import kz.ktzh.models.RegulatoryDocumentation;

public interface RegulatoryDocumentationRepository extends CrudRepository<RegulatoryDocumentation, Long>{
	ArrayList<RegulatoryDocumentation> findByCategory(int category);
}
