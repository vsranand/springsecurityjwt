package com.nttdata.springsecurityjwt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.springsecurityjwt.domain.User;

@Repository
public interface UserMongoRepository extends MongoRepository<User, String> {

	User findByUser(String name);
}
