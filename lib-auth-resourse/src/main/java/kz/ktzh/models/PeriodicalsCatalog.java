package kz.ktzh.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "PeriodicalsCatalog")
public class PeriodicalsCatalog {	
	
	@Id
	private Integer id;
	private String type;
	private String docN;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String docDate;
	private String docName;
	private String fileType;
	private String periodicity;
	private String website;	
	private Date registrationDate;
	private String user;	
	private String filePath;
	private String fileName;	
	
	protected PeriodicalsCatalog() {
		
	}
	
	public PeriodicalsCatalog(String type, String docN, String docDate, String docName, String fileType,
			String periodicity, String website, Date registrationDate, String user, String filePath, String fileName) {
		super();
		this.type = type;
		this.docN = docN;
		this.docDate = docDate;
		this.docName = docName;
		this.fileType = fileType;
		this.periodicity = periodicity;
		this.website = website;
		this.registrationDate = registrationDate;
		this.user = user;
		this.filePath = filePath;
		this.fileName = fileName;
	}	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDocN() {
		return docN;
	}
	public void setDocN(String docN) {
		this.docN = docN;
	}
	public String getDocDate() {
		return docDate;
	}
	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getPeriodicity() {
		return periodicity;
	}
	public void setPeriodicity(String periodicity) {
		this.periodicity = periodicity;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
