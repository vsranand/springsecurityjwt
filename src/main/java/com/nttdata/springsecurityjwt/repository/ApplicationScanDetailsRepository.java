package com.nttdata.springsecurityjwt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.springsecurityjwt.domain.Analysis;

@Repository
public interface ApplicationScanDetailsRepository extends MongoRepository<Analysis, String>{

	Analysis findByApplicationName(String applicationName);

}
