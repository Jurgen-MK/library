package kz.ktzh.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kz.ktzh.mapper.NewsRowMapper;
import kz.ktzh.models.News;

@Repository
public class NewsDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<News> getNewsList() {
		String sql = "SELECT id, header, dateUpdated FROM News";
		List<News> listNews = jdbcTemplate.query(
                sql, new NewsRowMapper());
		return listNews;		
	}
	
	public String getNewsText(int id) {
		return jdbcTemplate.queryForObject("SELECT text FROM News where id= ?",
				new Object[] { id }, String.class);
	}
	
	
	
	

}
