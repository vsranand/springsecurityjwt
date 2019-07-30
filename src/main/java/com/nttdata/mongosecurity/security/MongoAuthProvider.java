package com.nttdata.mongosecurity.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.nttdata.mongosecurity.domain.User;
import com.nttdata.mongosecurity.service.UserMongoService;

//@Component
public class MongoAuthProvider implements AuthenticationProvider {

	@Autowired
	UserMongoService userMongoService;

    @Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
  
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
         
        if (authenticate(name,password)) {
            return new UsernamePasswordAuthenticationToken(
              name, password, new ArrayList<>());
        } else {
            return null;
        }
    }
 
    private boolean authenticate(String name, String password) {
    	if (StringUtils.isEmpty(name)) return false;
    	if (StringUtils.isEmpty(password)) return false;
    	User user = userMongoService.getUser(name);
    	if (name.equals(user.getUser()) && password.equals(user.getPassword())) {
    		return true;
    	}
		return false;
	}

	@Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
