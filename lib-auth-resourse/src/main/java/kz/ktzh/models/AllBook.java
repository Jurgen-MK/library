package kz.ktzh.models;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AllBook")
public class AllBook {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String author;	
	private String doc_name;	
	private Date public_date;
	private Date registration_date;
	private String doc_type;
	private String n_pages;
	private String udc;
	private String gasnti;
	private String short_desc;
	private String applic_area;
	private String public_house;
	private String filePath;
	private String user_add;
	private Integer status;
	private String filetype;
	
	private String fileName;
	
	protected AllBook() {
		
	}
	
	public AllBook(String author, String doc_name, Date public_date, Date registration_date, String doc_types, String n_pages, String udc, String gasnti,
			String short_desc, String applic_area, String public_house, String filePath, String fileName, String user_add, Integer status, String filetype ) {
			
		this.author=author;	
		this.doc_name=doc_name;	
		this.public_date=public_date;
		this.registration_date=registration_date;
		this.doc_type=doc_types;
		this.n_pages=n_pages;
		this.udc=udc;
		this.gasnti=gasnti;
		this.short_desc=short_desc;
		this.applic_area=applic_area;
		this.public_house=public_house;
		this.filePath=filePath;
		this.fileName = fileName;
		this.user_add=user_add;
		this.status=status;	
		this.filetype=filetype;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDoc_name() {
		return doc_name;
	}

	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}

	public Date getPublic_date() {
		return public_date;
	}

	public void setPublic_date(Date public_date) {
		this.public_date = public_date;
	}

	public Date getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}

	public String getDoc_type() {
		return doc_type;
	}

	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}

	public String getN_pages() {
		return n_pages;
	}

	public void setN_pages(String n_pages) {
		this.n_pages = n_pages;
	}

	public String getUdc() {
		return udc;
	}

	public void setUdc(String udc) {
		this.udc = udc;
	}

	public String getGasnti() {
		return gasnti;
	}

	public void setGasnti(String gasnti) {
		this.gasnti = gasnti;
	}

	public String getShort_desc() {
		return short_desc;
	}

	public void setShort_desc(String short_desc) {
		this.short_desc = short_desc;
	}

	public String getApplic_area() {
		return applic_area;
	}

	public void setApplic_area(String applic_area) {
		this.applic_area = applic_area;
	}

	public String getPublic_house() {
		return public_house;
	}

	public void setPublic_house(String public_house) {
		this.public_house = public_house;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getUser_add() {
		return user_add;
	}

	public void setUser_add(String user_add) {
		this.user_add = user_add;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
	
	
}
