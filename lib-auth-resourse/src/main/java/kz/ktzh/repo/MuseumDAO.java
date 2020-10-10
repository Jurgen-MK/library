package kz.ktzh.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kz.ktzh.mapper.ExhibitMapper;
import kz.ktzh.mapper.MuseumMapper;
import kz.ktzh.models.Exhibit;
import kz.ktzh.models.Museum;

@Repository
public class MuseumDAO {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public List<Museum> getMuseumList(){
		String sql = "SELECT id, name, opisanie FROM muzei";
		List<Museum> mList = jdbcTemplate.query(sql, new MuseumMapper());
		return mList;		
	}
	
	public List<Exhibit> getExhibitList(int id){
		String sql = "SELECT id_file, link_photo, naimen, opis_file FROM muzei_photo WHERE id = :id";
		List<Exhibit> exList = jdbcTemplate.query(
                sql,
                new MapSqlParameterSource()
                .addValue("id", id),
                new ExhibitMapper());
		return exList;
	}

}
