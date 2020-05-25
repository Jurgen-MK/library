package kz.ktzh.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kz.ktzh.models.SearchRespond;

public class SearchRespondRowMapper implements RowMapper<SearchRespond>{
	
	

	@Override
	public SearchRespond mapRow(ResultSet rs, int rowNum) throws SQLException {
		SearchRespond sr = new SearchRespond();
		sr.setDocName(rs.getString("name"));
		sr.setFileName(rs.getString("filename"));
		sr.setFilePath(rs.getString("filepath"));
		return sr;
	}

}
