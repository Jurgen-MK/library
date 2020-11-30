package kz.ktzh.models;

public class TechnicalLessonPlan {

	private int id;
	private String branch;
	private String subdivision;
	private String department;
	private int yearOf;
	private String link;
	
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
	public String getSubdivision() {
		return subdivision;
	}
	public void setSubdivision(String subdivision) {
		this.subdivision = subdivision;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getYearOf() {
		return yearOf;
	}
	public void setYearOf(int yearOf) {
		this.yearOf = yearOf;
	}
	
}
