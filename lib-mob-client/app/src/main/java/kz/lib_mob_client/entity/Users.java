package kz.lib_mob_client.entity;

public class Users {


	private int id;
	private String username;
	private String password;
	private String passwordmd5;
	private int ud_tb;
	private int idea;
	private String secretquestions;
	private String answer;
	private byte enabled;

	public Users(String username, String password, String passwordmd5, int ud_tb, int idea, String secretquestions,
				 String answer, byte enabled) {
		this.username = username;
		this.password = password;
		this.passwordmd5 = passwordmd5;
		this.ud_tb = ud_tb;
		this.idea = idea;
		this.secretquestions = secretquestions;
		this.answer = answer;
		this.enabled = enabled;
	}

	public String getPasswordmd5() {
		return passwordmd5;
	}



	public void setPasswordmd5(String passwordmd5) {
		this.passwordmd5 = passwordmd5;
	}

	public int getUd_tb() {
		return ud_tb;
	}

	public void setUd_tb(int ud_tb) {
		this.ud_tb = ud_tb;
	}

	public int getIdea() {
		return idea;
	}

	public void setIdea(int idea) {
		this.idea = idea;
	}

	public String getSecretquestions() {
		return secretquestions;
	}

	public void setSecretquestions(String secretquestions) {
		this.secretquestions = secretquestions;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte getEnabled() {
		return enabled;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

}
