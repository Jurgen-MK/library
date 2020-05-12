package kz.ktzh.models;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

public interface PeriodicalsCatalogRepository extends CrudRepository<PeriodicalsCatalog, Long>{
	ArrayList<PeriodicalsCatalog> findAll();
}
