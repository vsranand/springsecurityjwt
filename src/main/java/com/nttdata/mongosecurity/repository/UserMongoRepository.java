package com.nttdata.mongosecurity.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.mongosecurity.domain.User;

@Repository
public interface UserMongoRepository extends MongoRepository<User, String> {

	User findByUser(String name);
}
