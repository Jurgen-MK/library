package kz.ktzh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kz.ktzh.models.RegulatoryDocumentation;
import kz.ktzh.repo.RegulatoryDocumentationFilesRepository;
import kz.ktzh.repo.RegulatoryDocumentationRepository;


@Service
public class RegulatoryDocumentationService {
	
	@Autowired
	RegulatoryDocumentationRepository regDocRepo;
	
	@Autowired
	RegulatoryDocumentationFilesRepository regDocFilesRepo;
	
	public List<RegulatoryDocumentation> getRegDocWithFiles(int category){
		List<RegulatoryDocumentation> regDocList = regDocRepo.findByCategory(category);
		for (RegulatoryDocumentation regdoc : regDocList) {
		    regdoc.setFileslist(regDocFilesRepo.findById(regdoc.getId()));
		}
		return regDocList;
	}

}
