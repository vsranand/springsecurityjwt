package com.nttdata.springsecurityjwt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nttdata.springsecurityjwt.domain.Analysis;
import com.nttdata.springsecurityjwt.repository.ApplicationScanDetailsRepository;

@Service
public class ApplicationScanDetailsServiceImpl {

	@Autowired
	private ApplicationScanDetailsRepository applicationScanDetailsRepository;

	@Transactional
	public void createAnalysis(Analysis analysis) {
		applicationScanDetailsRepository.save(analysis);
	}

	@Transactional
	public void createAnalysis(List<Analysis> analysisList) {
		applicationScanDetailsRepository.saveAll(analysisList);
	}

	@Transactional(readOnly = true)
	public Analysis getAnalysis(String applicationName) {
		return applicationScanDetailsRepository.findByApplicationName(applicationName);
	}

	@Async
	public void startAnalysis(String applicationName) {
		//All logic in this method will run asynchronously.
		try {
			Thread.sleep(10000);
			
			//if any exceptions happen as below then it will be thrown to the 
			//CustomAsyncExceptionHandler where we can neatly handle it
			String a = null;
			a.toString();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("This is printed after 10 seconds");
	}

	@Transactional(readOnly = true)
	public List<Analysis> findByNoOfProblems(Long noOfProblems) {
		return applicationScanDetailsRepository.findByNoOfProblems(noOfProblems);
	}

}
