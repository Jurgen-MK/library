package kz.lib_mob_client.entity;

public class SearchRequest {
	
	String catalogMode;
	String catalogType;
	String searchString;

	public String getCatalogMode() {
		return catalogMode;
	}

	public void setCatalogMode(String catalogMode) {
		this.catalogMode = catalogMode;
	}

	public String getCatalogType() {
		return catalogType;
	}

	public void setCatalogType(String catalogType) {
		this.catalogType = catalogType;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

}
