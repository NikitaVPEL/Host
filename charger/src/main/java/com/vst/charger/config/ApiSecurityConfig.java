//package com.vst.charger.config;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//@EnableWebSecurity
//public class ApiSecurityConfig {
//	
//	String admin = "ADMIN";
//	String user = "USER";
//
//	@Bean
//	public InMemoryUserDetailsManager userDetailsService() {
//
//		List<UserDetails> users = new ArrayList<>();
//		users.add(User.withUsername("user").password("{noop}user").roles(user).build());
//
//		users.add(User.withUsername("admin").password("{noop}admin").roles(admin).build());
//		
//		return new InMemoryUserDetailsManager(users);
//		
//	}
//
//	@SuppressWarnings("deprecation")
//	public void configure(HttpSecurity http) throws Exception {
//		http
//		.authorizeRequests()
//		
//		.requestMatchers("/charger").hasAnyRole(admin,user)
//		.requestMatchers("/charger/get/{chargerId}").hasRole(admin);
//	}
//	
//
//}
