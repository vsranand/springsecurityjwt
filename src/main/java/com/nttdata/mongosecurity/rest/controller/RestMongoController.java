package com.nttdata.mongosecurity.rest.controller;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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

    @GetMapping("/admin")
    @RolesAllowed({"ROLE_ADMIN"})
    public String hasRole() {
        return "Hello, I am admin!";
    }

	@GetMapping("/logout")
	public String doLogout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		SecurityContextHolder.getContext().setAuthentication(null);
		return "Logout";
	}

}
