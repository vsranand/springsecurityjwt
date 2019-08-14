package com.nttdata.springsecurityjwt.domain;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "analysisResult")
public class Analysis {

	@JsonIgnore
	@Id
	private ObjectId id;

	@Indexed
	private String applicationName;

	private Date createdDate;

	private String analysisType;

	private ApplicationInventory applicationInventory;

	private List<Findings> findings;

	private List<Scores> scores;

	private List<Problems> problems;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getAnalysisType() {
		return analysisType;
	}

	public void setAnalysisType(String analysisType) {
		this.analysisType = analysisType;
	}

	public ApplicationInventory getApplicationInventory() {
		return applicationInventory;
	}

	public void setApplicationInventory(ApplicationInventory applicationInventory) {
		this.applicationInventory = applicationInventory;
	}

	public List<Findings> getFindings() {
		return findings;
	}

	public void setFindings(List<Findings> findings) {
		this.findings = findings;
	}

	public List<Scores> getScores() {
		return scores;
	}

	public void setScores(List<Scores> scores) {
		this.scores = scores;
	}

	public List<Problems> getProblems() {
		return problems;
	}

	public void setProblems(List<Problems> problems) {
		this.problems = problems;
	}


}
