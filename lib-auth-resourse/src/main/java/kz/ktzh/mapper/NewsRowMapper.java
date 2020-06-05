package kz.ktzh.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kz.ktzh.models.News;

public class NewsRowMapper implements RowMapper<News>{

	@Override
	public News mapRow(ResultSet rs, int rowNum) throws SQLException {
		News news = new News();
		news.setId(rs.getInt("id"));
		news.setHeader(rs.getString("header"));
		news.setDateUpdated(rs.getString("dateUpdated"));
		return news;
	}

}
