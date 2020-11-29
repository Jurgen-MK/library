package kz.ktzh.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kz.ktzh.models.TechnicalLessonYearPlanThemeInfo;

public class TechnicalLessonYearPlanThemeInfoMapper implements RowMapper<TechnicalLessonYearPlanThemeInfo>{

	@Override
	public TechnicalLessonYearPlanThemeInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		TechnicalLessonYearPlanThemeInfo tlypti = new TechnicalLessonYearPlanThemeInfo();
		tlypti.setId(rs.getInt("id"));
		tlypti.setMonth(rs.getString("month"));
		tlypti.setThemeName(rs.getString("theme_name"));
		tlypti.setPlace(rs.getString("place"));
		tlypti.setPosition(rs.getString("position"));
		tlypti.setNote(rs.getString("note"));
		return tlypti;
	}

}
