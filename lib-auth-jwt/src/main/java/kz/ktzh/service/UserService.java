package kz.ktzh.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

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
	PasswordEncoder passwordEnc;
	


	public String regUser(Users user, UserInfo userInfo) {		
		if (userRepo.findByUsername(user.getUsername()).isEmpty()) {
			try {
				user.setPasswordmd5(md5String(user.getPassword()));
				user.setPassword(passwordEnc.encode(user.getPassword()));
				user.setAnswer(md5String(user.getAnswer()));
				System.out.println("bcrypt - " + user.getPassword());				
				userRepo.save(user);
				userRoleRepo.save(new Authorities(user.getUsername(), ROLE));
				userGroupsRepo.save(new UserGroups(user.getId(), 1));
				//List<Users> tempuser = userRepo.findByUsername(user.getUsername());
				userInfo.setId(user.getId());
				userInfoRepo.save(userInfo);
			} catch (Exception e) {
				e.printStackTrace();
				return "Ошибка регистрации";
			}
		} else {
			return "1";
		}
		return "0";
	}
	
	public String md5String(String pass) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
	    md.update(pass.getBytes());
	    byte[] digest = md.digest();
	    String hashedPass = DatatypeConverter.printHexBinary(digest);
	    return hashedPass;
	}

}
