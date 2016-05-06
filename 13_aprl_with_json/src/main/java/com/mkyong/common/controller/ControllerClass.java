package com.mkyong.common.controller;

import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerClass {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseBody
	// for reflecting View on screen
	public Map<String, Object> add(@RequestBody User user) {
		Map<String, Object> mapVeiw = new LinkedHashMap<String, Object>();
		// Service to Add user.
		try {
			User addUser = userService.addUser(user);
			// Create response and send to View.
			mapVeiw.put("Record is Successfully Saved", addUser);
			return mapVeiw;
		} catch (InputMismatchException ex) {
			if (ex.getLocalizedMessage() == user.getName()) {
				mapVeiw.put("error ", "Name must be in AlphaBet only");
				mapVeiw.put("feildName", "name");

			}
			if (ex.getLocalizedMessage() == user.getUsername()) {
				mapVeiw.put("error ", "UserName must have numeric Value");
				mapVeiw.put("feildName", "userName");

			}

			// "Please Enter only Alphabet in Name & UserName Must have one numeric";
		} catch (RuntimeException exp) {

			if (exp.getLocalizedMessage() == user.getName()) {

				mapVeiw.put("Error Invalid input", user.getName());
				mapVeiw.put("FeildName", " Name");
			}
			if (exp.getLocalizedMessage() == user.getUsername()) {

				mapVeiw.put("Error Invalid input", user.getUsername());
				mapVeiw.put("FieldName ", " UserName");
			}
		}

		return mapVeiw;
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	@ResponseBody
	// for Reflecting View on screen
	// Assigning name and username from JSON to User and id from URL
	public Map<String, Object> update(@RequestBody User user,
			@PathVariable("id") Integer id) {
		user.setId(id);
		Map<String, Object> mapVeiw = new LinkedHashMap<String, Object>();
		// Service to Add user.
		try {
			User updateUser = userService.UpdateUser(user);
			// Create response and send to View.
			mapVeiw.put("user", updateUser);
			return mapVeiw;
		} catch (InputMismatchException ex) {
			if (ex.getLocalizedMessage() == user.getName()) {
				mapVeiw.put("error ", "Name must be in AlphaBet only");
				mapVeiw.put("feildName", "name");

			}
			if (ex.getLocalizedMessage() == user.getUsername()) {
				mapVeiw.put("error ", "UserName must have numeric Value");
				mapVeiw.put("feildName", "userName");

			}

			// "Please Enter only Alphabet in Name & UserName Must have one numeric";
		} catch (RuntimeException exp) {

			if (exp.getLocalizedMessage() == user.getName()) {

				mapVeiw.put("Error Invalid input", user.getName());
				mapVeiw.put("FeildName", " Name");
			}
			if (exp.getLocalizedMessage() == user.getUsername()) {

				mapVeiw.put("Error Invalid input", user.getUsername());
				mapVeiw.put("FieldName ", " UserName");
			}
		}

		return mapVeiw;
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	// for Reflecting View on screen
	// Get Id from URL
	String delete(@PathVariable("id") Integer id) {
		// int UserId = id;
		User user = new User();
		user.setId(id);
		// Service to delete user.
		User deleteUser = userService.DeleteUser(user);
		// Create response and send to View.
		if (deleteUser == null) {
			return "The Id You Enter Was Not Found";// If Id not Present in
													// Database
		} else {
			return "Id :" + user.getId() + " has been deleted from Database";
		}

	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public @ResponseBody
	List<User> listV() {
		// Create response and send to View.
		return userService.ListUser();
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public @ResponseBody
	// GET Id from URL
	String select(@PathVariable("id") Integer id) {
		// Get Parameter
		// int UserId = id;
		User user = new User();// Creating User Object
		user.setId(id);
		User selectUser = userService.SelectUser(user);

		if (selectUser == null) {
			return "NO data Found";// If no Data found of given id in Database
		} else {
			return "Searched Record : " + selectUser;
		}
	}

	// Model View Just for TEST
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		// Service to list of user.
		List<User> listUser = this.listUser();

		// Create response and send to View.
		ModelAndView model = new ModelAndView("list");
		model.addObject("msg3", listUser);
		return model;
	}

	public List<User> listUser() {
		List<User> user = userService.ListUser();// Call to UserService to List
													// User
		return user;
	}
	
	@RequestMapping(value="/user1",method= RequestMethod.GET)
	public ModelAndView login()
	{
		ModelAndView model;
		model=new ModelAndView("index");
		return model;
	}

}

/*
 * public User select(User user) { User selectUser =
 * userService.SelectUser(user);// Call to UserService // for Selecting Record
 * if (selectUser == null) { return null; } else { return selectUser; } }
 */
/*
 * public User delete(User user) { User deleteUser =
 * userService.DeleteUser(user);// Call to UserService // for Delete if
 * (deleteUser == null) { return null; } else { return deleteUser; }
 * 
 * }
 */

/*
 * @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
 * 
 * @ResponseBody//for Reflecting View on screen //Assigning name and username
 * from JSON to User and id from URL public String update1(@RequestBody User
 * user, @PathVariable("id") Integer id) {
 * 
 * int userId = id; //Creating User Object user.setId(userId);
 * user.setName(user.getName()); user.setUsername(user.getUsername());
 * System.out.println(user.getId() + " " + user.getName()); // Service to update
 * user. User checkUser=userService.checkUser(user); if(checkUser!=null){ User
 * updateUser = this.update(user);
 * 
 * // Create response and send to View. if (updateUser.getName() == null) {
 * return "The Id You Enter Was Not Found";//If Id not Present in Database }
 * else { return "Record of Id " + updateUser.getId() +
 * " has been successfully updated :" + updateUser; } } else return
 * "Please Enter only Alphabet in Name & UserName Must have one numeric";
 * 
 * }
 */
/*
 * public User update(User user) { User updateUser =
 * userService.UpdateUser(user);//Call to service for update if (updateUser ==
 * null) { return null; } else { return updateUser; } }
 */
/*
 * @RequestMapping(value = "/user", method = RequestMethod.POST)
 * 
 * @ResponseBody//for reflecting View on screen public String add(@RequestBody
 * User user) {
 * 
 * // Service to Add user. try{ User addUser = userService.addUser(user); //
 * Create response and send to View. return "Record Added Successfully : " +
 * addUser; } catch(NullPointerException exp) { return "error"; }
 * catch(InputMismatchException ex) { return
 * "Please Enter only Alphabet in Name & UserName Must have one numeric"; } }
 */
/*
 * public User add(String name, String userName) { User user = new User();
 * //Creating User Object user.setName(name); user.setUsername(userName); User
 * checkUser=userService.checkUser(user); if(checkUser!=null) { User savedUser =
 * userService.addUser(user);//Call to Services for add return savedUser; }
 * return null; }
 */

/*
 * @RequestMapping(value = "/add/{lastname}/{uname}", method =
 * RequestMethod.GET) public ModelAndView add(@PathVariable Map<String, String>
 * pathVars) {
 * 
 * // Get parameter. String name = pathVars.get("uname"); String lastname =
 * pathVars.get("lastname");
 * 
 * // Service to save user. User user = this.add(name, lastname);
 * System.out.println(user.getName());
 * 
 * // Create response and send to View. ModelAndView model = new
 * ModelAndView("HelloWorldPage"); model.addObject("msg", "data added ");
 * model.addObject("msg", "data added " + name); model.addObject("user", user);
 * 
 * return model; }
 */

/*
 * @RequestMapping(value = "/add", method = RequestMethod.POST) public
 * ModelAndView add(@RequestParam("name") String name,
 * 
 * @RequestParam("username") String userName, Map<String, String> pathVars) {
 * 
 * // Service to save user. User user = this.add(name, userName);
 * System.out.println(user.getName());
 * 
 * // Create response and send to View. ModelAndView model = new
 * ModelAndView("HelloWorldPage"); model.addObject("msg", "data added ");
 * model.addObject("msg", "data added " + name); model.addObject("user", user);
 * 
 * return model; }
 */

/* JSON */
/*
 * @RequestMapping(value = "/add", method = RequestMethod.POST)
 * 
 * @ResponseBody public String add1(@RequestParam("name") String name,
 * 
 * @RequestParam("userName") String userName, Map<String, String> pathVars) {
 * 
 * // Service to save user. User addUser = this.add(name, userName);
 * if(addUser==null){ return "Please Enter only Alphabet"; } else{ //
 * System.out.println(user.getName());
 * 
 * // Create response and send to View.
 * 
 * return "Record Added Successfully : " + addUser; } }
 */
/*
 * @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE) public
 * ModelAndView delete(@PathVariable Map<String, String> pathVars) { int id =
 * Integer.parseInt(pathVars.get("id")); User user = new User(); user.setId(id);
 * // Service to delete user. User deleteUser = this.delete(user);
 * 
 * // Create response and send to View. ModelAndView model; if (deleteUser ==
 * null) { model = new ModelAndView("Not_Found"); model.addObject("msg",
 * "Not found id " + id); } else {
 * 
 * model = new ModelAndView("update"); model.addObject("msg2",
 * "deleted record of  =" + id); } return model; }
 */
/*
 * @RequestMapping(value = "/select", method = RequestMethod.GET) public
 * ModelAndView select(@RequestParam("id") Integer id, Map<String, String>
 * pathVars) {
 * 
 * User user = new User(); user.setId(id); User selectUser = this.select(user);
 * ModelAndView model;
 * 
 * if (selectUser == null) { model = new ModelAndView("Not_Found");
 * model.addObject("msg", "Not Found = " + id); } else { model = new
 * ModelAndView("select"); model.addObject("user", selectUser); } return model;
 * }
 */
/*
 * @RequestMapping(value = "/delete", method = RequestMethod.DELETE) public
 * ModelAndView delete(@RequestParam Integer id, Map<String, String> pathVars) {
 * 
 * User user = new User(); user.setId(id); // Service to delete user. User
 * deleteUser = this.delete(user);
 * 
 * // Create response and send to View. ModelAndView model; if (deleteUser ==
 * null) { model = new ModelAndView("Not_Found"); model.addObject("msg",
 * "Not found id " + id); } else {
 * 
 * model = new ModelAndView("update"); model.addObject("msg2",
 * "deleted record of  =" + id); } return model; }
 */
/*
 * @RequestMapping(value = "/update/{id}/{name}/{uname}", method =
 * RequestMethod.GET) public ModelAndView update(@PathVariable Map<String,
 * String> pathVars) { // Get parameter. int id =
 * Integer.parseInt(pathVars.get("id")); String name = pathVars.get("name");
 * String uname = pathVars.get("uname");
 * 
 * User user = new User(); user.setId(id); user.setName(name);
 * user.setUsername(uname);
 * 
 * // Service to update user. user = this.update(user); ModelAndView model;
 * 
 * // Create response and send to View. if (user == null) { model = new
 * ModelAndView("Not_Found"); model.addObject("msg", "Not Found = " + id); }
 * else { model = new ModelAndView("update"); model.addObject("msg1", "id =" +
 * id); } return model; }
 * 
 * @RequestMapping(value = "/update", method = RequestMethod.PUT) public
 * ModelAndView update(@RequestParam("id") Integer id,
 * 
 * @RequestParam("name") String name,
 * 
 * @RequestParam("uname") String uname, Map<String, String> pathVars) { // Get
 * parameter. System.out.println("test-----test----test");
 * 
 * User user = new User(); user.setId(id); user.setName(name);
 * user.setUsername(uname); System.out.println(user.getId() + " " +
 * user.getName()); // Service to update user. user = this.update(user);
 * ModelAndView model;
 * 
 * // Create response and send to View. if (user == null) { model = new
 * ModelAndView("Not_Found"); model.addObject("msg", "Not Found = " + id); }
 * else { model = new ModelAndView("update"); model.addObject("msg1", "id =" +
 * id); } return model; }
 */

/*
 * @RequestMapping(value = "/update1/", method = RequestMethod.PUT)
 * 
 * @ResponseBody public String update(@RequestParam("id") Integer id,
 * 
 * @RequestParam("name") String name,
 * 
 * @RequestParam("userName") String userName, Map<String, String> pathVars) { //
 * Get parameter. System.out.println("test-----test----test");
 * 
 * User user = new User(); user.setId(id); user.setName(name);
 * user.setUsername(userName); System.out.println(user.getId() + " " +
 * user.getName()); // Service to update user. User updateUser =
 * this.update(user);
 * 
 * // Create response and send to View. if (updateUser == null) { return
 * "The Id You Enter Was Not Found"; } else {
 * 
 * return "Record of Id " + updateUser.getId() +
 * " has been successfully updated :" + updateUser; }
 * 
 * }
 */
/*
 * @RequestMapping(value = "/select/{id}", method = RequestMethod.GET) public
 * ModelAndView select(@PathVariable Map<String, String> pathVars) { int id =
 * Integer.parseInt(pathVars.get("id")); User user = new User(); user.setId(id);
 * // Service to Select User User selectUser = this.select(user);
 * 
 * // Create response and send to View. ModelAndView model; if (selectUser ==
 * null) { model = new ModelAndView("Not_Found"); model.addObject("msg",
 * "Not Found = " + id); } else { model = new ModelAndView("select");
 * model.addObject("user", selectUser); }
 * 
 * return model; }
 */
