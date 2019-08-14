package com.nttdata.springsecurityjwt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.springsecurityjwt.domain.Analysis;
import com.nttdata.springsecurityjwt.repository.ApplicationScanDetailsRepository;

@Service
public class ApplicationScanDetailsServiceImpl {

	@Autowired
	private ApplicationScanDetailsRepository applicationScanDetailsRepository;

	public void createAnalysis(Analysis analysis) {
		applicationScanDetailsRepository.save(analysis);
	}

	public void createAnalysis(List<Analysis> analysisList) {
		applicationScanDetailsRepository.saveAll(analysisList);
	}

	public Analysis getAnalysis(String applicationName) {
		return applicationScanDetailsRepository.findByApplicationName(applicationName);
	}

}
