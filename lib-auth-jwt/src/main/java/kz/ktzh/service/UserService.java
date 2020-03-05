package kz.ktzh.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kz.ktzh.models.UserInfo;
import kz.ktzh.models.UserInfoRepository;
import kz.ktzh.models.UserRepository;
import kz.ktzh.models.Authorities;
import kz.ktzh.models.UserGroups;
import kz.ktzh.models.UserGroupsRepository;
import kz.ktzh.models.UserRoleRepository;
import kz.ktzh.models.Users;

@Service
public class UserService {

	private static final String ROLE = "ROLE_USER";

	@Autowired
	UserInfoRepository userInfoRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	UserRoleRepository userRoleRepo;

	@Autowired
	UserGroupsRepository userGroupsRepo;

	@Autowired
	UserDAO userDao;

	@Autowired
	PasswordEncoder passwordEnc;

	public String regUser(Users user, UserInfo userInfo) {
		if (userRepo.findByUsername(user.getUsername()).isEmpty()) {
			try {
				user.setPasswordmd5(md5String(user.getPassword()));
				user.setPassword(passwordEnc.encode(user.getPassword()));
				user.setAnswer(md5String(user.getAnswer()));
				System.out.println("quest" + user.getSecretquestions());
				userRepo.save(user);
				userRoleRepo.save(new Authorities(user.getUsername(), ROLE));
				userGroupsRepo.save(new UserGroups(user.getId(), 1));
				// List<Users> tempuser = userRepo.findByUsername(user.getUsername());
				userInfo.setId(user.getId());
				userInfoRepo.save(userInfo);
				return "1";
			} catch (Exception e) {
				e.printStackTrace();
				return "3";
			}
		} else {
			return "0";
		}
	}

	public String getSecretQuestion(String username) {
		return userDao.getSecretQuestion(username);
	}

	public String changePassword(String username, String oldpass, String newpass) {
		try {			
			if (passwordEnc.matches(oldpass, userDao.getPassword(username))) {
				userDao.resetPassword(username, passwordEnc.encode(newpass), md5String(newpass));
				return "1";
			} else {
				return "0";
			}
		} catch (NoSuchAlgorithmException e) {			
			e.printStackTrace();
			return "3";
		}
	}

	public String resetPassword(String username, String answer, String newpass) {
		try {
			if (md5String(answer).equals(userDao.getAnswer(username))) {
				userDao.resetPassword(username, passwordEnc.encode(newpass), md5String(newpass));
				return "1";
			} else {
				return "0";
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "3";
		}

	}

	public String md5String(String nonhashedstring) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(nonhashedstring.getBytes());
		byte[] digest = md.digest();
		String hashedstring = DatatypeConverter.printHexBinary(digest).toLowerCase();
		return hashedstring;
	}

}
