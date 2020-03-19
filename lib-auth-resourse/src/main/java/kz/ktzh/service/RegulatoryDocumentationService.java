package kz.ktzh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kz.ktzh.models.RegulatoryDocumentation;
import kz.ktzh.models.RegulatoryDocumentationFilesRepository;
import kz.ktzh.models.RegulatoryDocumentationRepository;


@Service
public class RegulatoryDocumentationService {
	
	@Autowired
	RegulatoryDocumentationRepository regDocRepo;
	
	@Autowired
	RegulatoryDocumentationFilesRepository regDocFilesRepo;
	
	public List<RegulatoryDocumentation> getRegDocWithFiles(){
		List<RegulatoryDocumentation> regDocList = regDocRepo.findByCategory(19);
		for (RegulatoryDocumentation regdoc : regDocList) {
		    regdoc.setFileslist(regDocFilesRepo.findById(regdoc.getId()));
		}
		return regDocList;
	}

}
