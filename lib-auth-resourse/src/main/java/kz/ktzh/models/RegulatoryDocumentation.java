package kz.ktzh.models;


import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "RegulatoryDocumentation")
public class RegulatoryDocumentation {	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int category;
	private String user;
	private String text;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date date;
	@Transient
	private List<RegulatoryDocumentationFiles> fileslist;

	protected RegulatoryDocumentation() {
	}

	public RegulatoryDocumentation(int category, String user, String text, Date date) {
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
