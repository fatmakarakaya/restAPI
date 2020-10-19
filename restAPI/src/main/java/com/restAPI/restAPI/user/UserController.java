package com.restAPI.restAPI.user;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	User users= new User();
	
	Map<String,User> user;
	
	@GetMapping
	public String getUsers(@RequestParam(value="page", defaultValue="1") int page,
			@RequestParam(value="limit", defaultValue="10") int limit, 
			@RequestParam(value="sort", defaultValue="desc", required=false) String sort ) {
		return "get users with page "+ page +" and with limit " + limit +" and sort "+sort;
	}
	
	@GetMapping(path = "{userID}" , 
			produces = {MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<User> getUser(@PathVariable String userID) {
		
		if(user.containsKey(userID)) {
			return new ResponseEntity<>(user.get(userID), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE}, 
			produces = {MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<User> createUsers(@Valid @RequestBody UserDetails userDetails) {
		users.setName(userDetails.getName());
		users.setLastname(userDetails.getLastname());
		users.setEmail(userDetails.getEmail());
		users.setPassword(userDetails.getPassword());
		
		String userID=UUID.randomUUID().toString();
		users.setUserID(userID);
		
		if(user==null) {
			user= new HashMap<>();
			user.put(userID,users);
		}
		return new ResponseEntity<User>(users , HttpStatus.OK);
	}
	
	
	@PutMapping(path = "{userID}" ,
			consumes = {MediaType.APPLICATION_XML_VALUE, 
			MediaType.APPLICATION_JSON_VALUE}, 
	produces = {MediaType.APPLICATION_XML_VALUE, 
			MediaType.APPLICATION_JSON_VALUE})
	public User updateUser(@PathVariable String userID, @Valid @RequestBody UpdateUserDetails userDetails) {
		
		User storeUser = user.get(userID);
		storeUser.setName(userDetails.getName());
		storeUser.setLastname(userDetails.getLastname());
		
		user.put(userID, storeUser);
		
		return storeUser;
		
	}
	
	@DeleteMapping (path= "/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {
		
		user.remove(id);
		return ResponseEntity.noContent().build();
		
	}
}
