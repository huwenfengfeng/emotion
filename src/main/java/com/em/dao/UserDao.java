package com.em.dao;

import com.em.entity.User;

public interface UserDao {

	User findUserByName(String account);
	
}
