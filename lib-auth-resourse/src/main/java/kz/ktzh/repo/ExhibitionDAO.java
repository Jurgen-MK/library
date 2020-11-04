package kz.ktzh.repo;

import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kz.ktzh.mapper.ExhibitionRespondMapper;
import kz.ktzh.models.ExhibitionRespond;

@Repository
public class ExhibitionDAO {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public List<ExhibitionRespond> getExhibitionList(){
		String sql = "SELECT id, name, link_date, mesto_prov, opisanie FROM vystavki";		
		List<ExhibitionRespond> exList = jdbcTemplate.query(sql, new ExhibitionRespondMapper());
		return exList;		
	}
	
	public HashMap<String,String> getFilesList(int id){
		String sql = "SELECT id_file, link_photo FROM vystavki_photo WHERE vyst_id = :id";
		HashMap<String,String> fl = jdbcTemplate.query(sql, new MapSqlParameterSource().addValue("id", id), (ResultSetExtractor<HashMap<String, String>>) rs -> {
		    HashMap<String,String> resmap= new HashMap<String,String>();
		    while(rs.next()){
		        resmap.put(rs.getString("id_file"),rs.getString("link_photo"));
		    }
		    return resmap;
		});
		 return fl;
	}

}
