package com.restAPI.restAPI.user;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateUserDetails {

	@NotNull (message="First name cannot be empty")
	private String name;
	
	@NotNull (message="Last name cannot be empty")
	private String lastname;
	
}
