package kz.ktzh.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@RequestMapping("/signUp")
	public String signUp(Model model) {
		UserCreationRequest userCreationRequest = new UserCreationRequest();
		userCreationRequest.setUser(new Users());
		userCreationRequest.setUserInfo(new UserInfo());
		model.addAttribute("userCreationRequest", userCreationRequest);
		model.addAttribute("messagePass", "");
		model.addAttribute("message", "");
		return "index";
	}

	@RequestMapping("/checkUser")
	public String checkUser() {
		return "checkUser";
	}

	@RequestMapping("/resetPassword")
	public String resetPassword(@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "answer", required = false) String answer,
			@RequestParam(name = "newPassword", required = false) String newPassword, ModelMap model) {

		String msgCode = userServ.resetPassword(username, answer, newPassword);

		if (msgCode.equals("200")) {
			model.addAttribute("message", "Вы успешно сменили пароль!");
			return "message";
		} else {
			model.addAttribute("username", username);
			model.addAttribute("secretQuestion", userServ.getSecretQuestion(username));
			model.addAttribute("message", msgCode.equals("100") ? "Вы указали неверный ответ на секретный вопрос!"
					: "Произошла ошибка при смене пароля!");
			return "forgot";
		}
	}

	@RequestMapping("/forgot")
	public String forgot(@RequestParam(name = "username", required = false) String username, ModelMap model) {
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
	public String save(@ModelAttribute UserCreationRequest userCreationRequest,
			@RequestParam(name = "confirmPass", required = false) String confirmPass, Model model) {

		if (!userCreationRequest.getUser().getPassword().equals(confirmPass)) {
			// model.addAttribute("userCreationRequest", userCreationRequest);
			model.addAttribute("messagePass", "Пароли не совпадают!");
			return "index";
		}

		String msgCode = userServ.regUser(userCreationRequest.getUser(), userCreationRequest.getUserInfo());

		if (msgCode.equals("200")) {
			model.addAttribute("message", "Вы успешно зарегистрировались!");
			return "message";
		} else {
			model.addAttribute("userCreationRequest", userCreationRequest);
			model.addAttribute("message",
					msgCode.equals("100") ? "Пользователь с таким же логином уже существует, укажите другой!"
							: "Произошла ошибка при регистрации!");
			return "index";
		}

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
