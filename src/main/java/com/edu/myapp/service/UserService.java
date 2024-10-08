package com.edu.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edu.myapp.dao.UserDAO;
import com.edu.myapp.dto.Role;
import com.edu.myapp.dto.UserDTO;

@Component
public class UserService {
	@Autowired
	private UserDAO userDAO;

	public UserDTO addUser(String firstName, String lastName, long mobile, String email,String password, String address, String role) {
		if (role.equals("ADMIN")) {
			boolean flag = false;
			List<UserDTO> users = userDAO.findAllUsers();
			for (UserDTO user : users) {
				if (user.getRole().equals(Role.ADMIN)) {
					flag = true;
					break;
				}
			}
			
			if (flag) {
				return null;
			}
		}
		UserDTO user = new UserDTO();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setMobile(mobile);
		user.setEmail(email);
		user.setPassword(password);
		user.setAddress(address);
		if (role.equals("USER")) {
			user.setRole(Role.USER);
		} else {
			user.setRole(Role.ADMIN);
		}
		try {
			return userDAO.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public UserDTO login(String email, String password) {
		try {
			return userDAO.login(email, password);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public UserDTO updateUser(int id, String firstName, String lastName, long mobile, String email,String password, String address) {
		try {
			return userDAO.updateUser(id, firstName, lastName,  mobile, email, password,  address);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void deleteUser(int id) {
		userDAO.deleteUser(id);
	}

	public List<UserDTO> findAllUsers() {
		return userDAO.findAllUsers();
	}

	public UserDTO findUserById(int id) {
		return userDAO.findUserById(id);
	}
}
