package com.nttdata.springsecurityjwt.rest.controller;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.springsecurityjwt.domain.User;
import com.nttdata.springsecurityjwt.exception.UnauthorizedException;
import com.nttdata.springsecurityjwt.model.UserDTO;
import com.nttdata.springsecurityjwt.security.JwtTokenUtil;
import com.nttdata.springsecurityjwt.security.JwtUser;

@RestController
@RequestMapping("/api/v1")
public class RestMongoController {

	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping(value="/login")
	public ResponseEntity<UserDTO> doLogin(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUser(),user.getPassword()));
			final JwtUser userDetails = (JwtUser)authentication.getPrincipal();
			SecurityContextHolder.getContext().setAuthentication(authentication);
			final String token =jwtTokenUtil.generateToken(userDetails);
			response.setHeader("Token", token);
			return new ResponseEntity<UserDTO>(new UserDTO(userDetails.getUser(),token),HttpStatus.OK);
		} catch (Exception e) {
			throw new UnauthorizedException(e.getMessage());
		}
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
