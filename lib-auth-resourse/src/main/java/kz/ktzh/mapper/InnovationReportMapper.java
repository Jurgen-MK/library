package kz.ktzh.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kz.ktzh.models.InnovationReport;

public class InnovationReportMapper implements RowMapper<InnovationReport> {

	@Override
	public InnovationReport mapRow(ResultSet rs, int rowNum) throws SQLException {
		InnovationReport innovationReport = new InnovationReport();
		innovationReport.setStatistic(rs.getString("statistic"));
		innovationReport.setCountRabNti(rs.getInt("quantityAuthor"));
		innovationReport.setPlan(0);
		innovationReport.setTotalSum(rs.getInt("edH"));
		innovationReport.setTotalH(rs.getInt("edH"));
		innovationReport.setTotalST(rs.getInt("edST"));
		innovationReport.setTotalSV(rs.getInt("edS"));
		innovationReport.setTotalSum35(rs.getInt("quantityAuthorAge"));
		innovationReport.setTotalH35(rs.getInt("edH35"));
		innovationReport.setTotalST35(rs.getInt("edST35"));
		innovationReport.setTotalSV35(rs.getInt("edS35"));
		return innovationReport;
	}

}
