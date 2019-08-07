package com.nttdata.springsecurityjwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.springsecurityjwt.domain.User;
import com.nttdata.springsecurityjwt.repository.UserMongoRepository;

@Service
public class UserMongoService {

	@Autowired
	UserMongoRepository userMongoRepository;

	public User getUser(String name) {
		User user = userMongoRepository.findByUser(name);
		return user;
	}

}
