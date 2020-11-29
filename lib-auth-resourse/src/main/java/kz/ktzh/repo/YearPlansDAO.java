package kz.ktzh.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kz.ktzh.mapper.TechnicalLessonYearPlanMapper;
import kz.ktzh.mapper.TechnicalLessonYearPlanThemeInfoMapper;
import kz.ktzh.models.TechnicalLessonYearPlan;
import kz.ktzh.models.TechnicalLessonYearPlanThemeInfo;

@Repository
public class YearPlansDAO {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public List<TechnicalLessonYearPlan> getPlansList(){
		String sql = "SELECT plans_list.id, filial.mnemocode as branch, podrazdel.mnemocode as subbranch, otdel.otdel_name as department, plans_list.god as year, plans_list.link_file "
				+ "FROM plans_list "
				+ "LEFT JOIN filial ON filial.ID=plans_list.ID_FILIAL "
				+ "LEFT JOIN podrazdel ON podrazdel.id=plans_list.ID_POD "
				+ "LEFT JOIN otdel ON otdel.ID=plans_list.ID_OTD "
				+ "ORDER BY god";
		List<TechnicalLessonYearPlan> plansList = jdbcTemplate.query(sql, new TechnicalLessonYearPlanMapper());
		return plansList;		
	}
	
	public List<TechnicalLessonYearPlanThemeInfo> getPlanInfo(int plan_id){
		String sql = "SELECT plans.id, plans.month_nom as month, tems_lessons.NAME as theme_name, plans.mesto as place, dolzhnost.DOLZHNOST as position, plans.prim as note FROM plans "
				+ "LEFT JOIN tems_lessons ON tems_lessons.ID=plans.n_temy "
				+ "LEFT JOIN dolzhnost ON plans.dolzhn_lector=dolzhnost.ID "
				+ "where ID_PLAN_LIST = :id";
		List<TechnicalLessonYearPlanThemeInfo> planInfoList = jdbcTemplate.query(sql,
				new MapSqlParameterSource().addValue("id", plan_id), new TechnicalLessonYearPlanThemeInfoMapper());
		return planInfoList;
	}

}
