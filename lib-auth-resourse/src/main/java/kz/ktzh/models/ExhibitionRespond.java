package kz.ktzh.models;

import java.util.HashMap;

public class ExhibitionRespond {
	
	private int id;
	private String name;
	private String linkdate;
	private String place;
	private String description;
	private HashMap<String,String> fileslist;	

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
	public String getLinkdate() {
		return linkdate;
	}
	public void setLinkdate(String linkdate) {
		this.linkdate = linkdate;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public HashMap<String,String> getFileslist() {
		return fileslist;
	}

	public void setFileslist(HashMap<String,String> fileslist) {
		this.fileslist = fileslist;
	}
	
	
	

}
