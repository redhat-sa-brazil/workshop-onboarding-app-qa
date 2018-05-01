package com.redhat.workshop.questions.autoconfig;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin")
                .password("redhat@123").roles("USER");
    }
	
	@Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests().anyRequest()
	      .hasRole("USER")
	      .and()
	      .httpBasic();
	  }

}
