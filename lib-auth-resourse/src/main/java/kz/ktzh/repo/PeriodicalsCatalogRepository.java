package kz.ktzh.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import kz.ktzh.models.PeriodicalsCatalog;

public interface PeriodicalsCatalogRepository extends CrudRepository<PeriodicalsCatalog, Long>{
	ArrayList<PeriodicalsCatalog> findAll();
}
