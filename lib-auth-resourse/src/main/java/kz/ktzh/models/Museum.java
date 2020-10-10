package kz.ktzh.models;

import java.util.List;

public class Museum {
	
	private int id;
	private String name;
	private String description;
	private List<Exhibit> ExhibitList;		

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Exhibit> getExhibitList() {
		return ExhibitList;
	}

	public void setExhibitList(List<Exhibit> exhibitList) {
		ExhibitList = exhibitList;
	}
	
	
	

}
