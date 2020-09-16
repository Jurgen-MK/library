package kz.ktzh.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	@Column(name = "password_md5")
	private String passwordmd5;
	private int ud_tb;
	private int idea;
	@Column(name = "secret_question")
	private String secretquestion;
	private String answer;
	
	private byte enabled;

	/*protected Users() {
	}*/

	public Users() {		
	}
	
	/*
	 * public Users(String username, String password, byte enabled) { this.username
	 * = username; this.password = password; this.enabled = enabled; }
	 */
	
	
	public Users(String username, String password, String passwordmd5, int ud_tb, int idea, String secretquestions,
			String answer, byte enabled) {		
		this.username = username;
		this.password = password;
		this.passwordmd5 = passwordmd5;
		this.ud_tb = ud_tb;
		this.idea = idea;
		this.secretquestion = secretquestions;
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
		return secretquestion;
	}

	public void setSecretquestions(String secretquestions) {
		this.secretquestion = secretquestions;
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
