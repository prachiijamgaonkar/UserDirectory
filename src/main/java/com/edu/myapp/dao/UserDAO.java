package com.edu.myapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.edu.myapp.dto.UserDTO;
@Component
public class UserDAO {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;

	public UserDTO addUser(UserDTO user) {
		openConnection();
		entityTransaction.begin();
		entityManager.persist(user);
		entityTransaction.commit();
		closeConnection();
		return user;
	}

	public UserDTO login(String email, String password) {
		openConnection();
		Query query = entityManager
				.createQuery("SELECT user from UserDTO user WHERE user.email = ?1 AND user.password = ?2");
		query.setParameter(1, email);
		query.setParameter(2, password);
		UserDTO user = (UserDTO) query.getSingleResult();
		closeConnection();
		return user;
	}

	public UserDTO updateUser(int id, String firstName, String lastName, long mobile, String email,String password, String address) {
		openConnection();
		UserDTO user = entityManager.find(UserDTO.class, id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setMobile(mobile);
		user.setEmail(email);
		user.setPassword(password);
		user.setAddress(address);
		entityTransaction.begin();
		entityManager.persist(user);
		entityTransaction.commit();
		closeConnection();
		return user;
	}

	public void deleteUser(int id) {
		openConnection();
		UserDTO user = entityManager.find(UserDTO.class, id);
		entityTransaction.begin();
		entityManager.remove(user);
		entityTransaction.commit();
		closeConnection();
	}

	@SuppressWarnings("unchecked")
	public List<UserDTO> findAllUsers() {
		openConnection();
		Query query = entityManager.createQuery("SELECT users FROM UserDTO users");
		List<UserDTO> users = query.getResultList();
		closeConnection();
		return users;
	}
	public UserDTO findUserById(int id) {
		openConnection();
		UserDTO user = entityManager.find(UserDTO.class, id);
		closeConnection();
		return user;
	}
	private void openConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		entityManagerFactory = Persistence.createEntityManagerFactory("webapp");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}

	private void closeConnection() {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
		if (entityManager != null) {
			entityManager.close();
		}
		if (entityTransaction != null) {
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}

		}
	}

}
