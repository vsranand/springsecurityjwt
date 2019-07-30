package com.nttdata.mongosecurity.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nttdata.mongosecurity.service.UserMongoService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserMongoService userMongoService;

	@Autowired
	private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	com.nttdata.mongosecurity.domain.User user;
    	try {
			user = userMongoService.getUser(username);
			if (user==null) {
				throw new UsernameNotFoundException("user name not found");
			}
		} catch (Exception e) {
			throw new UsernameNotFoundException("database connection erro");
		}
    	return buildUserFromUserEntity(user);
    }

	private UserDetails buildUserFromUserEntity(com.nttdata.mongosecurity.domain.User user) {
        // convert model user to spring security user
        String username               = user.getUser();
        String password               = passwordEncoder.encode(user.getPassword());
        boolean enabled               = true;
        boolean accountNonExpired     = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked      = true;
        
        //Currently hardcoding as ADMIN role, need to fetch from database the role of the user
        //and accordingly set the permissions.
        List<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        authorities.add(authority);

        return new User(username, 
                                   password, 
                                   enabled,
                                   accountNonExpired, 
                                   credentialsNonExpired, 
                                   accountNonLocked,
                                   authorities);
	}
}
