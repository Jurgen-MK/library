package kz.ktzh.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kz.ktzh.models.UserCreationRequest;
import kz.ktzh.models.UserInfo;
import kz.ktzh.models.UserRepository;
import kz.ktzh.models.Users;
import kz.ktzh.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userServ;
	
	@Autowired
	UserRepository userRepo;
	
	@Value("${ecb-service}")
    private String ecbService;
	
	@RequestMapping("/signUp")
    public String segnUp() {
        return "index";
    }
	
	@RequestMapping("/checkUser")
    public String checkUser() {
        return "checkUser";
    }
	
	@RequestMapping("/resetPassword")
    public String resetPassword(
    		@RequestParam (name = "username", required = false) String username,
    		@RequestParam (name = "answer", required = false) String answer, 
    		@RequestParam (name = "newPassword", required = false) String newPassword,
    		ModelMap model
    		) {
		
		String msgCode = userServ.resetPassword(username, answer, newPassword);
		model.addAttribute("ecbService", ecbService);
		if (msgCode.equals("200")) {
			model.addAttribute("message", "Вы успешно сменили пароль!");
			return "message";
		} else {
			model.addAttribute("username", username);
			model.addAttribute("secretQuestion", userServ.getSecretQuestion(username));
			model.addAttribute(
					"message", msgCode.equals("100") ? 
					"Вы указали неверный ответ на секретный вопрос!" :
					"Произошла ошибка при смене пароля!");
			return "forgot";
		}
	}
	
	@RequestMapping("/forgot")
    public String forgot(
    		@RequestParam (name = "username", required = false) String username,
    		ModelMap model
    		) {		
		if (!userRepo.findByUsername(username).isEmpty()) {				
			model.addAttribute("username", username);
			model.addAttribute("secretQuestion", userServ.getSecretQuestion(username));				
			return "forgot";
		} else {
			model.addAttribute("message", "Пользователь " + username + " не найден!");
			return "checkUser";
		}				
    }		
	
	@PostMapping(value = "/message")
	public String save(
			@RequestParam (name = "username", required = false) String username,
			@RequestParam (name = "password", required = false) String password,
			@RequestParam (name = "confirmPass", required = false) String confirmPass,
			@RequestParam (name = "secretquestion", required = false) String secretquestion,
			@RequestParam (name = "answerQuestion", required = false) String answerQuestion,
			@RequestParam (name = "birthday", required = false) 
			@DateTimeFormat(pattern="yyyy-MM-dd") Date birthday,
			@RequestParam (name = "address", required = false) String address,
			@RequestParam (name = "phone", required = false) String phone,
			@RequestParam (name = "email", required = false) String email,
			@RequestParam (name = "education", required = false) String education,			
			@RequestParam (name = "surname", required = false) String surname,
			@RequestParam (name = "name", required = false) String name,
			@RequestParam (name = "patronymic", required = false) String patronymic,
			ModelMap model
			) {
		
		UserCreationRequest userCreationRequest = new UserCreationRequest(
				new Users(), 
				new UserInfo()
				);		
		userCreationRequest.getUser().setUsername(username);
		userCreationRequest.getUser().setPassword(password);
		userCreationRequest.getUser().setSecretquestions(secretquestion);
		userCreationRequest.getUser().setAnswer(answerQuestion);
		userCreationRequest.getUserInfo().setUsername(username);
		userCreationRequest.getUserInfo().setBirthday(birthday);
		userCreationRequest.getUserInfo().setAddress(address);
		userCreationRequest.getUserInfo().setPhone(phone);
		userCreationRequest.getUserInfo().setEmail(email);
		userCreationRequest.getUserInfo().setEducation(education);
		userCreationRequest.getUserInfo().setSurname(surname);
		userCreationRequest.getUserInfo().setName(name);
		userCreationRequest.getUserInfo().setPatronymic(patronymic);
		model.addAttribute("ecbService", ecbService);
		
		if (!password.equals(confirmPass)) {
			model.addAllAttributes(attributes(userCreationRequest));
			model.addAttribute("messagePass", "Пароли не совпадают!");
			return "index";
		}
		
		String msgCode = userServ.regUser(userCreationRequest.getUser(), userCreationRequest.getUserInfo());
		
		if (msgCode.equals("200")) {
			model.addAttribute("message", "Вы успешно зарегистрировались!");
			return "message";
		} else {
			model.addAllAttributes(attributes(userCreationRequest));
			model.addAttribute(
					"message", msgCode.equals("100") ? 
					"Пользователь с таким же логином уже существует, укажите другой!" :
					"Произошла ошибка при регистрации!");						
			
			return "index";
		}
		
	}
	
	public ModelMap attributes(UserCreationRequest userCreationRequest) {
		ModelMap model = new ModelMap();
		model.addAttribute("username", userCreationRequest.getUser().getUsername());
		model.addAttribute("secretquestion", userCreationRequest.getUser().getSecretquestions());
		model.addAttribute("answerQuestion", userCreationRequest.getUser().getAnswer());
		model.addAttribute("birthday", userCreationRequest.getUserInfo().getBirthday());
		model.addAttribute("address", userCreationRequest.getUserInfo().getAddress());
		model.addAttribute("phone", userCreationRequest.getUserInfo().getPhone());
		model.addAttribute("email", userCreationRequest.getUserInfo().getEmail());
		model.addAttribute("education", userCreationRequest.getUserInfo().getEducation());
		model.addAttribute("surname", userCreationRequest.getUserInfo().getSurname());
		model.addAttribute("name", userCreationRequest.getUserInfo().getName());
		model.addAttribute("patronymic", userCreationRequest.getUserInfo().getPatronymic());
		
		return model;
	}

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
