package kz.ktzh.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BooksInstructionsCatalog")
public class BooksInstructionsCatalog {

	@Id
	private Integer id;
	private String applicationArea;
	private String docN;	
	private String docDate;
	private String docName;
	private String fileType;
	private String pagesCount;
	private Date registrationDate;
	private String user;
	private String udk;
	private String heading;
	private String status;
	private String filePath;
	private String fileName;
	private String catalogType;


	protected BooksInstructionsCatalog() {
	}

	public BooksInstructionsCatalog(String applicationArea, String docN, String docDate, String docName, String fileType, String pagesCount,
			Date registrationDate, String user, String udk, String heading, String status, String filePath,
			String fileName, String catalogType) {
		this.applicationArea = applicationArea;
		this.docN = docN;
		this.docDate = docDate;
		this.docName = docName;
		this.fileType = fileType;
		this.pagesCount = pagesCount;
		this.registrationDate = registrationDate;
		this.user = user;
		this.udk = udk;
		this.heading = heading;
		this.status = status;
		this.filePath = filePath;
		this.fileName = fileName;
		this.catalogType = catalogType;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApplicationArea() {
		return applicationArea;
	}

	public void setApplicationArea(String applicationArea) {
		this.applicationArea = applicationArea;
	}

	public String getDocN() {
		return docN;
	}

	public void setDocN(String docN) {
		this.docN = docN;
	}

	

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
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

	public String getUdk() {
		return udk;
	}

	public void setUdk(String udk) {
		this.udk = udk;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getPagesCount() {
		return pagesCount;
	}

	public void setPagesCount(String pagesCount) {
		this.pagesCount = pagesCount;
	}

	public String getCatalogType() {
		return catalogType;
	}

	public void setCatalogType(String catalogType) {
		this.catalogType = catalogType;
	}

	public String getDocDate() {
		return docDate;
	}

	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}

	

	

}
