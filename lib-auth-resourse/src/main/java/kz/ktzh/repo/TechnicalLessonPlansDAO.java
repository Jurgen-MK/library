package kz.ktzh.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kz.ktzh.mapper.TechnicalLessonPlanInfoMapper;
import kz.ktzh.mapper.TechnicalLessonPlanMapper;
import kz.ktzh.models.TechnicalLessonPlan;
import kz.ktzh.models.TechnicalLessonPlanInfo;

@Repository
public class TechnicalLessonPlansDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<TechnicalLessonPlan> getListTechPlan(){
		String sql = "SELECT plans_zan_list.id, filial.mnemocode as branch, podrazdel.mnemocode as subdivision, " + 
				"otdel.OTDEL_NAME as department, plans_zan_list.GOD as yearOf, LINK_FILE as link " + 
				"FROM  plans_zan_list " + 
				"LEFT JOIN filial ON filial.ID = plans_zan_list.ID_FILIAL " + 
				"LEFT JOIN podrazdel ON podrazdel.id = plans_zan_list.ID_POD " + 
				"LEFT JOIN otdel ON otdel.ID = plans_zan_list.ID_OTD";
		List<TechnicalLessonPlan> listPlan = jdbcTemplate.query(sql, new TechnicalLessonPlanMapper());
		return listPlan;
	}
	
	public TechnicalLessonPlanInfo getInfoTechPlan(int id) {
		String sql = "SELECT tems_lessons.NAME AS techLessonTheme, " + 
				"CASE " + 
				"WHEN plans_zan_list.TIP_ZAN = 0 THEN ''" + 
				"WHEN plans_zan_list.TIP_ZAN = 1 THEN 'теоритическое'" + 
				"WHEN plans_zan_list.TIP_ZAN = 2 THEN 'практическое'" + 
				"WHEN plans_zan_list.TIP_ZAN = 3 THEN 'дистанционное обучение'" + 
				"END as lessonType, " + 
				"CONCAT(user_info.SURNAME,' ',LEFT(user_info.NAME, 1),'. ',LEFT(user_info.PATRONYMIC,1),'. ') AS fullName," + 
				"dolzhnost.DOLZHNOST as position " + 
				"from  plans_zan_list   " + 
				"LEFT JOIN user_info ON user_info.id=plans_zan_list.LECTOR_ID  " + 
				"LEFT JOIN dolzhnost ON dolzhnost.id=user_info.position  " + 
				"LEFT JOIN tems_lessons ON tems_lessons.ID = plans_zan_list.N_TEMY  " + 
				"WHERE plans_zan_list.ID = ?";
		return jdbcTemplate.queryForObject(sql,
				new Object[] { id }, new TechnicalLessonPlanInfoMapper());
	}
}
