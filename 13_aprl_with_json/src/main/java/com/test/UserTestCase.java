package com.test;

import static org.junit.Assert.*;

import java.util.InputMismatchException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mkyong.common.controller.User;
import com.mkyong.common.controller.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
// To Configure Xml beans
@ContextConfiguration(locations = { "classpath:mvc-dispatcher-servlet.xml" })
public class UserTestCase {

	@Autowired
	UserService userService;

	@Test
	public void testAddUser() {
		System.out.println("Testing Add User.");

		User user = new User();
		user.setName("TestName");
		user.setUsername("TestUserName");
		try {
			User addUser = userService.addUser(user);
			assertNotNull(addUser);
			assertNotNull(addUser.getId());
			assertEquals("TestName", addUser.getName());
			System.out.println("User added: " + addUser.toString());
		} catch (InputMismatchException ex) {
			assertFalse("Invalid Input", true);
		} catch (RuntimeException ex) {
			assertFalse("Input Contain Null", true);
		}

	}

	@Test
	public void testUpdateUser() {
		System.out.println("Testing Update User.");
		User user = new User();
		user.setId(28);
		user.setName("TestUpdate");
		user.setUsername("TestUpdate2");

		try {
			User updateUser = userService.UpdateUser(user);
			if (updateUser == null) {
				System.out.println("Not Record Found");
				User finduser = userService.FindUser(user);// Call to
															// UserService to
															// find User

				assertNull(finduser);
			} else {
				assertNotNull(updateUser);
				assertEquals("TestUpdate", updateUser.getName());
				System.out.println("User updated: " + updateUser.toString());
			}
		} catch (InputMismatchException ex) {
			assertFalse("Invalid Input", true);
		} catch (RuntimeException ex) {
			assertFalse("Input Contain Null", true);
		}

	}

	@Test
	public void testdeleteUser() {
		System.out.println("Testing delete User.");
		User user = new User();
		user.setId(70);
		User deleteUser = userService.DeleteUser(user);// Call to UserService to
														// Delete
		if (deleteUser == null) {
			System.out.println("Not Record Found");
			User finduser = userService.FindUser(user);
			assertNull(finduser); // If Record is Not Present
		} else {
			User finduser = userService.FindUser(deleteUser);// Call to
																// UserService
																// to FindUser
			assertNull(finduser); // After Deleting Data.

		}
	}

	@Test
	public void testSelectUser() {
		System.out.println("Testing Select User.");

		User user = new User();
		user.setId(49);
		User selectUser = userService.SelectUser(user);// Call to UserService to
														// SelectUser
		if (selectUser == null) {
			System.out.println("Not Record Found");
			assertNull(user.getName());// If not Record Found
		} else {
			assertEquals(49, selectUser.getId());
			assertEquals("TestName_1", selectUser.getName());
		}
	}

	@Test
	public void testListUser() {
		System.out.println("Testing List of User.");
		List<User> user = userService.ListUser();
		System.out.println(user.size());
		assertEquals(33, user.size());
	}

}
