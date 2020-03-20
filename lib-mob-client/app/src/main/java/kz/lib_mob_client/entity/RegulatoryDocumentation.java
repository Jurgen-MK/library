package kz.lib_mob_client.entity;

import java.util.List;

public class RegulatoryDocumentation {	

	private int id;
	private int category;
	private String user;
	private String text;
	private String date;
	private List<RegulatoryDocumentationFiles> fileslist;

	public RegulatoryDocumentation(int category, String user, String text, String date) {
		this.category = category;
		this.user = user;
		this.text = text;
		this.date = date;		
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<RegulatoryDocumentationFiles> getFileslist() {
		return fileslist;
	}

	public void setFileslist(List<RegulatoryDocumentationFiles> fileslist) {
		this.fileslist = fileslist;
	}

}
