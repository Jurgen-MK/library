package kz.ktzh.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import kz.ktzh.models.RegulatoryDocumentationFiles;

public interface RegulatoryDocumentationFilesRepository extends CrudRepository<RegulatoryDocumentationFiles, Long> {
	ArrayList<RegulatoryDocumentationFiles> findById(int id);
}
