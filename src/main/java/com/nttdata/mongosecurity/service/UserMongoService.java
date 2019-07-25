package com.nttdata.mongosecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.mongosecurity.domain.User;
import com.nttdata.mongosecurity.repository.UserMongoRepository;

@Service
public class UserMongoService {

	@Autowired
	UserMongoRepository userMongoRepository;

	public User getUser(String name) {
		User user = userMongoRepository.findByUser(name);
		return user;
	}

}
