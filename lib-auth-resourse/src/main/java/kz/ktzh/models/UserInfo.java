package kz.ktzh.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "user_info")
public class UserInfo {

	@Id
	private int id;
	private String username;
	private String name;
	private String surname;
	private String patronymic;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	private String address;
	private int branch;
	private int department;
	private int nti;
	private int line;
	private int region;
	private int position;
	private String phone;
	private String email;
	private String education;

	protected UserInfo() {
	}	

	public UserInfo(int id, String username, String name, String surname, String patronymic, Date birthday,
			String address, int branch, int department, int nti, int line, int region, int position, String phone,
			String email, String education) {		
		this.id = id;
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.birthday = birthday;
		this.address = address;
		this.branch = branch;
		this.department = department;
		this.nti = nti;
		this.line = line;
		this.region = region;
		this.setPosition(position);
		this.phone = phone;
		this.email = email;
		this.education = education;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getBranch() {
		return branch;
	}

	public void setBranch(int branch) {
		this.branch = branch;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public int getNti() {
		return nti;
	}

	public void setNti(int nti) {
		this.nti = nti;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getRegion() {
		return region;
	}

	public void setRegion(int region) {
		this.region = region;
	}

	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}



	

}
