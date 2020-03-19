package kz.ktzh.models;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

public interface RegulatoryDocumentationFilesRepository extends CrudRepository<RegulatoryDocumentationFiles, Long> {
	ArrayList<RegulatoryDocumentationFiles> findById(int id);
}
