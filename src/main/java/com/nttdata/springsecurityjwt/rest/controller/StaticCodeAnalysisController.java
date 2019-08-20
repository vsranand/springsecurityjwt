package com.nttdata.springsecurityjwt.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.springsecurityjwt.domain.Analysis;
import com.nttdata.springsecurityjwt.domain.AnalysisCollection;
import com.nttdata.springsecurityjwt.service.ApplicationScanDetailsServiceImpl;

@RestController
@RequestMapping("/api/staticCodeAnalysis")
public class StaticCodeAnalysisController {

	@Autowired
	private ApplicationScanDetailsServiceImpl applicationScanDetailsService;

	@PostMapping(value="/createAnalysis")
	public String createAnalysis(@RequestBody Analysis analysis) {
		applicationScanDetailsService.createAnalysis(analysis);
		return "Analysis Created successfully";
	}

	@PostMapping(value="/createAnalysisAll")
	public String createAnalysisAll(@RequestBody AnalysisCollection analysisCollection) {
		applicationScanDetailsService.createAnalysis(analysisCollection.getAnalysisList());
		return "All Analysis Created successfully";
	}

	@GetMapping(value="/findAnalysis")
	public Analysis findAnalysis(@RequestParam String applicationName) {
		return applicationScanDetailsService.getAnalysis(applicationName);
	}

	@PostMapping(value="/startAnalysis")
	public String startAnalysis(@RequestParam String applicationName) {
		applicationScanDetailsService.startAnalysis(applicationName);
		return "Analysis Started successfully";
	}

}
