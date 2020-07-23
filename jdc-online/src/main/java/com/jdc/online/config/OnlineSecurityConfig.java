package com.jdc.online.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jdc.online.service.InitUserService;
import com.jdc.online.service.OnlineUserDetailsService;

@EnableWebSecurity
public class OnlineSecurityConfig extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	private InitUserService initUser;
	
	@Autowired
	private OnlineUserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/login", "/public/**", "/resources/**").permitAll()
				.anyRequest().authenticated()
			.and()
				.formLogin()
				.loginPage("/public")
			.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/public");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}
	
	
	@Bean
	public CommandLineRunner initializeUser() {
		return args -> {
			initUser.addInitUsers();
		};
	}
	
	@Bean
	public PasswordEncoder encoder()  {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
}
