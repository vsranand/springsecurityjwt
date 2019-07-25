package com.nttdata.mongosecurity.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RestMongoController {

	@GetMapping("/login")
	public String doLogin() {
		return "Success";
	}

}
