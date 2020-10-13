package com.restAPI.restAPI.user;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	User users= new User();
	
	@GetMapping
	public String getUsers(@RequestParam(value="page", defaultValue="1") int page,
			@RequestParam(value="limit", defaultValue="10") int limit, 
			@RequestParam(value="sort", defaultValue="desc", required=false) String sort ) {
		return "get users with page "+ page +" and with limit " + limit +" and sort "+sort;
	}
	
	@GetMapping(path = "{userID}")
	public User getUser(@PathVariable String userID) {
		
		users.setName("Fatma");
		users.setLastname("Karakaya");
		users.setEmail("exc@xxx.com");
		users.setUserID(userID);
		return users;
	}
	
	@PostMapping
	public String createUsers() {
		return "users created";
	}
	
	@PutMapping
	public String updateUser() {
		return "update user";
	}
	
	@DeleteMapping
	public String deleteUser() {
		return "delete users";
	}
}
