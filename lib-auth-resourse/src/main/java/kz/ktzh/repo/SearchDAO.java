package kz.ktzh.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kz.ktzh.mapper.SearchRespondRowMapper;
import kz.ktzh.models.SearchRequest;
import kz.ktzh.models.SearchRespond;

@Repository
public class SearchDAO {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public List<SearchRespond> searchAll(){
		String sql = "SELECT doc_name as name, filepath, filename FROM AllBook " + 
				"union " + 
				"SELECT namedoc as name, filepath,filename  FROM Video " + 
				"union " + 
				"SELECT namedoc as name, filepath,filename  FROM Article " + 
				"union " + 
				"SELECT namedoc as name, filepath,filename FROM npdNtd " + 
				"union " + 
				"SELECT docName as name, filepath,filename FROM PeriodicalsCatalog ";
		List<SearchRespond> srList = jdbcTemplate.query(
                sql,
                new SearchRespondRowMapper());
		return srList;	
	}
	
	public List<SearchRespond> searchAllByName(String name){
		String sql = "SELECT doc_name as name, filepath, filename FROM AllBook where doc_name = :name "
				+ "union "
				+ "SELECT namedoc as name, filepath,filename  FROM Video where namedoc = :name "
				+ "union "
				+ "SELECT namedoc as name, filepath,filename  FROM Article where namedoc = :name "
				+ "union "
				+ "SELECT namedoc as name, filepath,filename FROM npdNtd where namedoc = :name "
				+ "union "
				+ "SELECT docName as name, filepath,filename FROM PeriodicalsCatalog where docName = :name";
		List<SearchRespond> srList = jdbcTemplate.query(
                sql,
                new MapSqlParameterSource()
                .addValue("name", name),
                new SearchRespondRowMapper());
		return srList;	
		
	}
	
	public List<SearchRespond> searchInNpdNtd(String category, String name){		
		String sql = "SELECT namedoc as name, filepath, filename FROM npdNtd where namedoc like :name and groupname = :groupname ";
				List<SearchRespond> srList = jdbcTemplate.query(
		                sql,
		                new MapSqlParameterSource()
		                .addValue("name", "%"+name+"%")
		                .addValue("groupname", category),
		                new SearchRespondRowMapper());				
				return srList;
	}
	
	public List<SearchRespond> searchInAllBook(String category, String name){
		String sql = "SELECT doc_name as name, filepath, filename FROM AllBook where doc_name like :name and status = :status ";		
				List<SearchRespond> srList = jdbcTemplate.query(
		                sql,
		                new MapSqlParameterSource()
		                .addValue("name", "%"+name+"%")
		                .addValue("status", category),
		                new SearchRespondRowMapper());
				return srList;
	}
	
	public List<SearchRespond> searchInArticle(String name){
		String sql = "SELECT namedoc as name, filepath, filename FROM Article where namedoc like :name ";
				List<SearchRespond> srList = jdbcTemplate.query(
		                sql,
		                new MapSqlParameterSource()
		                .addValue("name", "%"+name+"%"),
		                new SearchRespondRowMapper());
				return srList;
	}
	
	public List<SearchRespond> searchInVideo(String name){
		String sql = "SELECT namedoc as name, filepath, filename FROM Video where namedoc like :name ";
				List<SearchRespond> srList = jdbcTemplate.query(
		                sql,
		                new MapSqlParameterSource()
		                .addValue("name", "%"+name+"%"),
		                new SearchRespondRowMapper());
				return srList;
	}
	
	public List<SearchRespond> searchInPeriodicalsCatalog(String name){
		String sql = "SELECT docName as name, filepath, filename FROM PeriodicalsCatalog where docName like :name ";
				List<SearchRespond> srList = jdbcTemplate.query(
		                sql,
		                new MapSqlParameterSource()
		                .addValue("name", "%"+name+"%"),
		                new SearchRespondRowMapper());
				return srList;
	}

}
