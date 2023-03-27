package com.vst.charger.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("vst1/charger").permitAll().antMatchers("vst1/chargers").hasRole("ADMIN")
				.anyRequest().authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/dashboard")
				.and().logout().logoutUrl("/logout");
	}

	@Bean
	public Filter authFilter() {
		return new OncePerRequestFilter() {

			@Override
			protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
					throws ServletException, IOException {
				String authHeader = request.getHeader("X-Auth-User");
				if (authHeader != null) {
					String[] parts = authHeader.split(":");
					String username = parts[0];
					String[] roles = parts[1].split(",");
					List<GrantedAuthority> authorities = Arrays.stream(roles).map(SimpleGrantedAuthority::new)
							.collect(Collectors.toList());
					SecurityContextHolder.getContext()
							.setAuthentication(new UsernamePasswordAuthenticationToken(username, null, authorities));
				}
				chain.doFilter(request, response);
			}
		};
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}
}
