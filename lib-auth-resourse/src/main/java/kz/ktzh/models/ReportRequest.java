package kz.ktzh.models;

public class ReportRequest {

	public ReportRequest(String repName, String fromDt, String toDt) {
		this.repName = repName;
		this.fromDt = fromDt;
		this.toDt = toDt;
	}
	
	private String repName;
	private String fromDt;
	private String toDt;
	public String getRepName() {
		return repName;
	}
	public void setRepName(String repName) {
		this.repName = repName;
	}
	public String getFromDt() {
		return fromDt;
	}
	public void setFromDt(String fromDt) {
		this.fromDt = fromDt;
	}
	public String getToDt() {
		return toDt;
	}
	public void setToDt(String toDt) {
		this.toDt = toDt;
	}
	
}
