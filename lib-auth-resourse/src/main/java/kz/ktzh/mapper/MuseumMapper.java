package kz.ktzh.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kz.ktzh.models.Museum;

public class MuseumMapper implements RowMapper<Museum>{
	
	@Override
	public Museum mapRow(ResultSet rs, int rowNum) throws SQLException{
		Museum m = new Museum();
		m.setId(rs.getInt("id"));
		m.setName(rs.getString("name"));
		m.setDescription(rs.getString("opisanie"));
		return m;
	}

}
