package kz.ktzh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public String getSecretQuestion(String username) {
		return jdbcTemplate.queryForObject("SELECT secret_question FROM users WHERE username = ?",
				new Object[] { username }, String.class);
	}

	public String getAnswer(String username) {
		return jdbcTemplate.queryForObject("SELECT answer FROM users WHERE username = ?", new Object[] { username },
				String.class);
	}
	
	public String getPassword(String username) {
		return jdbcTemplate.queryForObject("SELECT password FROM users WHERE username = ?", new Object[] { username },
				String.class);
	}	

	public void resetPassword(String username, String newpass, String newpassmd5) {
		jdbcTemplate.update("UPDATE users SET password = ?, password_md5 = ? where username = ?", newpass, newpassmd5,
				username);
	}

}
