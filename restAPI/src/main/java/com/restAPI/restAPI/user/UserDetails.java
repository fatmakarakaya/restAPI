package com.restAPI.restAPI.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDetails {
	@NotNull (message="First name cannot be empty")
	private String name;
	
	@NotNull (message="Last name cannot be empty")
	private String lastname;
	
	@NotNull (message="Email cannot be empty")
	@Email
	private String email;
	
	@NotNull (message="Password cannot be empty")
	@Size(min= 4, max=8, message="password size must be between 4 and 8 character")
	private String password;
	

}
