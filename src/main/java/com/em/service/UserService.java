package com.em.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.em.dao.UserDao;
import com.em.entity.User;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	
	public User findUserByName(String account){
		return userDao.findUserByName(account);
	}
	
}
