package com.mkyong.common.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	public User validateUser(User user) {
		String patternUserName = "^([a-zA-Z]{0,9})+([1-9]{1,9})+([A-z]{0,9})*$";
		String patternName = "^([A-Za-z])*$";
		if (user.getName().isEmpty()) {
			throw new RuntimeException(user.getName());
		} else if (user.getUsername().isEmpty()) {
			throw new RuntimeException(user.getUsername());
		} else if (!Pattern.matches(patternName, user.getName())) {
			throw new InputMismatchException(user.getName());
		} else if (!user.getUsername().matches(patternUserName)) {
			throw new InputMismatchException(user.getUsername());
		} else {
			return user;
		}

	}

	public User addUser(User user) {
		validateUser(user);
		// Get EntityManger from factory.
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		// Being Transaction
		entityManager.getTransaction().begin();
		entityManager.persist(user);

		entityManager.flush();
		entityManager.getTransaction().commit();
		// End and flush transaction to database.
		return user;

	}

	public User UpdateUser(User user) {
		validateUser(user);
		// Get EntityManger from factory.
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		// System.out.println("in User service update");
		// Being Transaction
		entityManager.getTransaction().begin();

		User updateUser = entityManager.find(User.class, user.getId());// find
																		// id to
		// be
		// updated
		if (updateUser == null) {
			return null;
		} else {
			updateUser.setName(user.getName());// updating name
			updateUser.setUsername(user.getUsername());// updating userName
			entityManager.persist(updateUser);

			entityManager.flush();
			entityManager.getTransaction().commit();
			// End and flush transaction to database.
			return user;
		}
	}

	public User FindUser(User user) {

		// Get EntityManger from factory.
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		System.out.println("just for testing update call");
		// Being Transaction
		entityManager.getTransaction().begin();

		User findUser = entityManager.find(User.class, user.getId());// find id
		// System.out.println("User id " + user.getId());
		entityManager.getTransaction().commit();
		// End and flush transaction to database.
		return findUser;
	}

	public User DeleteUser(User user) {

		// Get EntityManger from factory.
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		// Being Transaction
		entityManager.getTransaction().begin();

		User delUser = entityManager.find(User.class, user.getId());// find id
																	// to be
																	// deleted
		// System.out.println("User id "+user.getId());
		if (delUser == null) {
			return null;
		} else {
			entityManager.remove(delUser);// deleting data from database

			entityManager.getTransaction().commit();
			// End and flush transaction to database.
			return user;
		}
	}

	public User SelectUser(User user) {

		// Get EntityManger from factory.
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		// Being Transaction
		entityManager.getTransaction().begin();

		User selectUser = entityManager.find(User.class, user.getId());// find
																		// id to
																		// be
																		// updated
		if (selectUser == null) {
			return null;
		} else {
			// System.out.println("User id "+user.getId());
			selectUser.getName();// geting name from database
			selectUser.getUsername();// getting username from database

			entityManager.getTransaction().commit();
			// End and flush transaction to database.
			return selectUser;
		}
	}

	public List<User> ListUser() {

		// Get EntityManger from factory.
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		// Being Transaction
		entityManager.getTransaction().begin();
		List<User> listUser = entityManager
				.createQuery("from User", User.class).getResultList();

		entityManager.getTransaction().commit();
		// End and flush transaction to database.
		return listUser;
	}

	public UserService() {
	}
	public String FindUser1(Integer user) {

		// Get EntityManger from factory.
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		System.out.println("just for testing update call");
		// Being Transaction
		entityManager.getTransaction().begin();

		User findUser = entityManager.find(User.class, user);// find id
		// System.out.println("User id " + user.getId());
		entityManager.getTransaction().commit();
		// End and flush transaction to database.
		return ""+user;
	}
}
