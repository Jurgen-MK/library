package kz.ktzh.models;

import java.util.List;

public class TechnicalLessonYearPlan {
	
	private int id;
	private String branch;
	private String subbranch;
	private String department;
	private String year;
	private String link_file;
	private List<TechnicalLessonYearPlanThemeInfo> info;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getSubbranch() {
		return subbranch;
	}
	public void setSubbranch(String subbranch) {
		this.subbranch = subbranch;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getLink_file() {
		return link_file;
	}
	public void setLink_file(String link_file) {
		this.link_file = link_file;
	}
	public List<TechnicalLessonYearPlanThemeInfo> getInfo() {
		return info;
	}
	public void setInfo(List<TechnicalLessonYearPlanThemeInfo> info) {
		this.info = info;
	}
	
	

}
