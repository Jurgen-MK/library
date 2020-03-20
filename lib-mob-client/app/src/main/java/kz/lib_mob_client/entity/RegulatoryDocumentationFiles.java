package kz.lib_mob_client.entity;

public class RegulatoryDocumentationFiles {

	private int idfile;
	private int id;
	private String path;
	private String name;
	
	


	public RegulatoryDocumentationFiles(int idfile, String path, String name) {	
		this.idfile = idfile;
		this.path = path;
		this.name = name;
	}

	public int getIdfile() {
		return idfile;
	}

	public void setIdfile(int idfile) {
		this.idfile = idfile;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
