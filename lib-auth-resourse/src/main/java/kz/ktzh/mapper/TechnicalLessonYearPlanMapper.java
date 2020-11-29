package kz.ktzh.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kz.ktzh.models.TechnicalLessonYearPlan;

public class TechnicalLessonYearPlanMapper implements RowMapper<TechnicalLessonYearPlan>{

	@Override
	public TechnicalLessonYearPlan mapRow(ResultSet rs, int rowNum) throws SQLException {
		TechnicalLessonYearPlan tlyp = new TechnicalLessonYearPlan();
		tlyp.setId(rs.getInt("id"));
		tlyp.setBranch(rs.getString("branch"));
		tlyp.setSubbranch(rs.getString("subbranch"));
		tlyp.setDepartment(rs.getString("department"));
		tlyp.setYear(rs.getString("year"));
		tlyp.setLink_file(rs.getString("link_file"));
		return tlyp;
	}

}
