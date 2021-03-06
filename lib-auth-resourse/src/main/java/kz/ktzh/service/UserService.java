package kz.ktzh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kz.ktzh.models.UserInfo;
import kz.ktzh.repo.UserInfoRepository;
import kz.ktzh.repo.UserRepository;


@Service
public class UserService {
	


	
	@Autowired
	UserInfoRepository userInfoRepo;
	
	@Autowired 
	UserRepository userRepo;
	
	public UserInfo getUserInfo(String username) {		
		return userInfoRepo.findByUsername(username);
	}
	
	public String updateUserInfo(UserInfo userInfo) {		
		try {
		userInfoRepo.save(userInfo);
		} catch(Exception e) {
			e.printStackTrace();			
			return "0";
		}
		return "1";
	}
	
}
