package com.edu.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.myapp.dto.UserDTO;
import com.edu.myapp.service.UserService;


@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	protected String getSignUpPage() {
		return "signup";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	protected String getLogInPage() {
		return "login";
	}

	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	protected String getHomePage(HttpSession httpSession, ModelMap modelMap) {
		UserDTO user = (UserDTO) httpSession.getAttribute("user");
		if (user != null) {
			modelMap.addAttribute("user", user);
			return "home";
		} else {
			return "login";
		}
	}

	@RequestMapping(value = "/add-user", method = RequestMethod.POST)
	protected String addUser(@RequestParam(name = "firstname") String firstName,@RequestParam(name = "lastname") String lastName,
			 @RequestParam(name = "mobile") long mobile, @RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password, @RequestParam(name = "address") String address, @RequestParam(name = "role") String role,
			ModelMap modelMap) {
		UserDTO addedUser = userService.addUser(firstName, lastName, mobile, email,password, address,role);
		if (addedUser != null) {
			modelMap.addAttribute("message", "User added..");
			return "login";
		} else {
			modelMap.addAttribute("message", "Something went wrong..");
			return "signup";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	protected String login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password,
			ModelMap modelMap, HttpSession httpSession) {
		UserDTO user = userService.login(email, password);
		if (user != null) {
			httpSession.setAttribute("user", user);
			httpSession.setMaxInactiveInterval(30 * 24 * 60 * 60);
			modelMap.addAttribute("user", user);
			return "home";
		} else {
			modelMap.addAttribute("message", "Invalid email or password..");
			return "login";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	protected String logout(HttpSession httpSession) {
		httpSession.invalidate();
		return "login";
	}

	@RequestMapping(value = "/edit-user", method = RequestMethod.GET)
	protected String getEditPage(HttpSession httpSession, ModelMap modelMap) {
		UserDTO user = (UserDTO) httpSession.getAttribute("user");
		if (user != null) {
			modelMap.addAttribute("user", userService.findUserById(user.getId()));
			return "edit";
		} else {
			return "login";
		}
	}

	@RequestMapping(value = "/update-user", method = RequestMethod.POST)
	protected String updateUser(@RequestParam(name = "id") int id, @RequestParam(name = "firstname") String firstName,@RequestParam(name = "lastname") String lastName,
			 @RequestParam(name = "mobile") long mobile, @RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password, @RequestParam(name = "address") String address, ModelMap modelMap, HttpSession httpSession) {
		UserDTO updatedUser = userService.updateUser(id, firstName,lastName,  mobile,email, password,address);
		if (updatedUser != null) {
			modelMap.addAttribute("message", "User updated..");
		} else {
			modelMap.addAttribute("message", "Something went wrong..");
		}
		
		//    Still pass the session user in case update fail
		UserDTO user = (UserDTO) httpSession.getAttribute("user");
		modelMap.addAttribute("user", user);
		return "home";
	}

	@RequestMapping(value = "/delete-user", method = RequestMethod.GET)
	protected String deleteUser(HttpSession httpSession, ModelMap modelMap) {
		UserDTO user = (UserDTO) httpSession.getAttribute("user");
		if (user != null) {
			userService.deleteUser(user.getId());
			modelMap.addAttribute("message", "User deleted..");
		}
		httpSession.invalidate();
		return "login";
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	protected String findAllUsers(HttpSession httpSession, ModelMap modelMap) {
		UserDTO user = (UserDTO) httpSession.getAttribute("user");
		if (user != null) {
			List<UserDTO> users = userService.findAllUsers();
			modelMap.addAttribute("users", users);
			return "users";
		} else {
			return "login";
		}
	}

}
