package com.nttdata.springsecurityjwt.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.nttdata.springsecurityjwt.domain.Analysis;

@Repository
public interface ApplicationScanDetailsRepository extends MongoRepository<Analysis, String>{

	Analysis findByApplicationName(String applicationName);

	@Query(value = "{'findings.noOfProblems':{$gt:?0}}")
	List<Analysis> findByNoOfProblems(Long noOfProblems);

}
