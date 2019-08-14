package com.nttdata.springsecurityjwt.domain;

public class Findings {

	private String level;

	private Long noOfProblems;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Long getNoOfProblems() {
		return noOfProblems;
	}

	public void setNoOfProblems(Long noOfProblems) {
		this.noOfProblems = noOfProblems;
	}


}
