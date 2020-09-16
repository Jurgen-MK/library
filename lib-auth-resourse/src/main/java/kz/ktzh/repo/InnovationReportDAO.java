package kz.ktzh.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kz.ktzh.mapper.InnovationReportMapper;
import kz.ktzh.models.InnovationReport;

@Repository
public class InnovationReportDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
		
	public List<InnovationReport> getInnovationReport(String fromDt, String toDt){
		
		String sql = "select " + 
				"	IF(ISNULL(ntiName)=1,'Общий итог:', " + 
				"         IF(ISNULL(filName)=1, " + 
				"				CONCAT('Итог по НТИ: ', QUOTE(ntiName)), " + 
				"	         CONCAT('Итог по филиалу: ', QUOTE(filName))) " + 
				"	          " + 
				"     ) as statistic, quantityAuthor, quantityAuthorAge, edH, edST, edS, edH35, edST35, edS35 " + 
				"FROM " + 
				"(	select  " + 
				"		distinct nti.full_name as ntiName,  " + 
				"		filial.full_name as filName,   " + 
				"		linia.full_name as linName,   " + 
				"		sum(rp_info.Quantity_Author) as quantityAuthor,   " + 
				"		sum(rp_info.Quantity_Author_Age) as quantityAuthorAge,   " + 
				"		SUM(rp_info.EDUC_H) AS edH, 	 " + 
				"		SUM(rp_info.EDUC_ST) AS edST, 	 " + 
				"		SUM(rp_info.EDUC_S) AS edS,   " + 
				"		SUM(rp_info.EDUC_H35) AS edH35, 	 " + 
				"		SUM(rp_info.EDUC_ST35) AS edST35, 	 " + 
				"		SUM(rp_info.EDUC_S35) AS edS35   " + 
				"	from rp_info   " + 
				"		left join nti on nti.id = rp_info.ID_NTI   " + 
				"		left join filial on filial.id = rp_info.ID_FILIAL   " + 
				"		left join linia on linia.id = rp_info.ID_LINIA   " + 
				"		left join rp_reward on rp_reward.REG_N = rp_info.REG_N   " + 
				"	WHERE DATE_REG BETWEEN ? AND ?  " + 
				"	group by ntiName, filName, linName with rollup " + 
				") A WHERE (ISNULL(ntiName) + ISNULL(filName) + ISNULL(linName)) <> 1";
		List<InnovationReport> irList = jdbcTemplate.query(
				sql,
				new Object[] { fromDt, toDt},
				new InnovationReportMapper());
		return irList;
		
	}
	
}
