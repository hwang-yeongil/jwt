package com.example.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록 됨
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
//@RequiredArgsConstructor
public class SecurityConfig{
	
	private CorsFilter corsFilter;
	

    @Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.formLogin().disable()
				.httpBasic().disable()
//				.apply(new MyCustomDsl()) // 커스텀 필터 등록
//				.and()
				.authorizeRequests(authroize -> authroize.antMatchers("/api/v1/user/**")
						.access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
						.antMatchers("/api/v1/manager/**")
						.access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
						.antMatchers("/api/v1/admin/**")
						.access("hasRole('ROLE_ADMIN')")
						.anyRequest().permitAll())
				.build();
	}
	
}


//@Bean
//SecurityFilterChain filterChain(HttpSecurity http) throws Exception  {
//	return	http
//		.csrf().disable()
//      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//      .and()
////      .addFilter(corsFilter)
//      .formLogin().disable()
//      .httpBasic().disable()
//      .authorizeRequests() <- 여기가 본 영상이랑 다름 
//      .antMatchers("/api/v1/user/**")
//	        .access("hasRole('ROLE_USER')or hasRole('ROLE_MANAGER')or hasRole('ROLE_ADMIN')")
//	        .antMatchers("/api/v1/manager/**")
//	        .access("hasRole('ROLE_MANAGER')or hasRole('ROLE_ADMIN')")
//	        .antMatchers("/api/v1/admin/**")
//	        .access("hasRole('ROLE_ADMIN')")
//	        .anyRequest().permitAll()
//	        .build();
//}
