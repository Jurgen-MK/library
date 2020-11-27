package kz.ktzh.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kz.ktzh.models.TechnicalLessonPlanInfo;

public class TechnicalLessonPlanInfoMapper implements RowMapper<TechnicalLessonPlanInfo> {

	@Override
	public TechnicalLessonPlanInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		TechnicalLessonPlanInfo techInfo = new TechnicalLessonPlanInfo();
		techInfo.setFullName(rs.getString("fullName"));
		techInfo.setLessonType(rs.getString("lessonType"));
		techInfo.setPosition(rs.getString("position"));
		techInfo.setTechLessonTheme(rs.getString("techLessonTheme"));
		return techInfo;
	}

}
