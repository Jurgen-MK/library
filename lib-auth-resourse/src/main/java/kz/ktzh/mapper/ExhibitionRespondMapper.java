package kz.ktzh.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kz.ktzh.models.ExhibitionRespond;

public class ExhibitionRespondMapper implements RowMapper<ExhibitionRespond>{
	
	@Override
	public ExhibitionRespond mapRow(ResultSet rs, int rowNum) throws SQLException {
		ExhibitionRespond er = new ExhibitionRespond();
		er.setId(rs.getInt("id"));
		er.setName(rs.getString("name"));
		er.setLinkdate(rs.getString("link_date"));
		er.setPlace(rs.getString("mesto_prov"));
		er.setDescription(rs.getString("opisanie"));
		return er;
	}

}
