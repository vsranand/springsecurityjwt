package com.nttdata.mongosecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nttdata.mongosecurity.security.MongoAuthEntryPoint;
import com.nttdata.mongosecurity.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    private MongoAuthEntryPoint authenticationEntryPoint;

    @Bean
    public UserDetailsService userDetailsService() {
      return new UserDetailsServiceImpl();
    }
 
/*    @Autowired
    private MongoAuthProvider authProvider;
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }
*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
          .antMatchers("/securityNone").permitAll()
          .anyRequest().authenticated()
          .and()
          .httpBasic()
          .authenticationEntryPoint(authenticationEntryPoint);
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
