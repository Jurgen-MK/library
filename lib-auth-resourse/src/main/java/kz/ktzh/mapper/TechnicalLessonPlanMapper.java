package kz.ktzh.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kz.ktzh.models.TechnicalLessonPlan;

public class TechnicalLessonPlanMapper implements RowMapper<TechnicalLessonPlan> {

	@Override
	public TechnicalLessonPlan mapRow(ResultSet rs, int rowNum) throws SQLException {
		TechnicalLessonPlan techLessonPlan = new TechnicalLessonPlan();
		techLessonPlan.setId(rs.getInt("id"));
		techLessonPlan.setBranch(rs.getString("branch"));
		techLessonPlan.setDepartment(rs.getString("department"));
		techLessonPlan.setSubdivision(rs.getString("subdivision"));
		techLessonPlan.setLink(rs.getString("link"));
		techLessonPlan.setYearOf(rs.getInt("yearOf"));
		return techLessonPlan;
	}

}
