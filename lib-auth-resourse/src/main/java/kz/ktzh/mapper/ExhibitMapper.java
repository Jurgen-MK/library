package kz.ktzh.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kz.ktzh.models.Exhibit;



public class ExhibitMapper implements RowMapper<Exhibit> {
	
	@Override
	public Exhibit mapRow(ResultSet rs, int rowNum) throws SQLException{
		Exhibit ex = new Exhibit();
		ex.setId(rs.getInt("id_file"));
		ex.setLink(rs.getString("link_photo"));
		ex.setName(rs.getString("naimen"));
		ex.setDescription(rs.getString("opis_file"));
		return ex;
	}

}
