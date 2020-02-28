package kz.ktzh.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kz.ktzh.models.UserCreationRequest;
import kz.ktzh.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userServ;

	@PostMapping(value = "/register")
	public String regUser(@RequestBody UserCreationRequest userCreationRequest) {
		return userServ.regUser(userCreationRequest.getUser(), userCreationRequest.getUserInfo());
	}

	@PostMapping(value = "/secretquestion")
	public String secretQuestion(@RequestParam String username) {
		return userServ.getSecretQuestion(username);
	}

	@PostMapping(value = "/resetpass")
	public String resetPass(@RequestParam String username, String answer, String newpass) {
		return userServ.resetPassword(username, answer, newpass);
	}
	
	@PostMapping(value = "/changepass")
	public String changePass(@RequestParam String username, String oldpass, String newpass) {
		return userServ.changePassword(username, oldpass, newpass);
	}

}
