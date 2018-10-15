package com.em.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.em.common.RestResult;
import com.em.entity.User;
import com.em.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/getUser")
	public RestResult<User> getUserById(String account){
		return RestResult.SUCCESS(userService.getUserById(account));
	}
	
}
