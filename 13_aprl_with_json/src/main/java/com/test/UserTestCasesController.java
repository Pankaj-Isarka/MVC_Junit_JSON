/*package com.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mkyong.common.controller.ControllerClass;
import com.mkyong.common.controller.User;
import com.mkyong.common.controller.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:mvc-dispatcher-servlet.xml"})
//@ContextConfiguration(classes=UserService.class)

public class UserTestCasesController {
	
	
	@Autowired
	private UserService userService;
	@InjectMocks
	private ControllerClass cc;
	private MockMvc mockMvc;


	@Before
	public void setUp() {
		
	
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(cc).build();
	}

	@Test
	public void testHome() throws Exception {

		this.mockMvc.perform(post("/update?id=51&name=pankaj&uname=isarka"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("WEB-INF/pages/update"));
	}
	@Test
	public void testUpdateUser() throws Exception {
	 
	 
	    this.mockMvc.perform(post("/update")
	    		.param("id", "62")
	            .param("name", "hello")
	            .param("username", "world"))
	            .andExpect(status().isOk())
	            .andExpect(forwardedUrl("WEB-INF/pages/HelloWorldPage.jsp"))
	            .andExpect(model().attributeExists("HelloWorldPage"));
	 
	}
	@Test
	public void testUpdateUser1() throws Exception {
		User user=new User();
		user.setId(51);
		User find=userService.FindUser(user);
		System.out.println(find.getId());
}
}
*/