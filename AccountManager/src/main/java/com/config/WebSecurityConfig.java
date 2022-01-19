package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ams.jpa.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity //spring security 활성화
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	private final UserService userService;
	
	/**
	 * 인증을 무시할 경로를 설정하는 method
	 * static 하위폴더 (css,js,img)는 
	 * 무조건 접근이 가능해야하기 때문에 인증무시를 설정해야한다
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
	}

	/**
	 * http 관련 인증 설정하는 method
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() //접근설정
				.antMatchers("/login", "/signup", "user").permitAll()//1. 누구나접근허용
				.antMatchers("/").hasRole("USER")//2. USER, ADMIN 접근가능
				.antMatchers("/admin").hasRole("ADMIN")//3. ADMIN 만 접근가능
				.anyRequest().authenticated()//4. 나머지 요청들은 종류 관계없이 권한이 있어야 접근가능
			.and()
				.formLogin()//로그인설정
				.loginPage("/login")//로그인 페이지 링크
				.defaultSuccessUrl("/")//로그인 성공 후 redirect 주소
			.and()
				.logout()//로그아웃설정
				.logoutSuccessUrl("/login")//로그아웃 성공 후 redirect 주소
				.invalidateHttpSession(true);//세션 삭제
			
	}
	
	/**
	 * login할 때 필요한 정보를 가져오는 method
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService)
		//해당 서비스(userService)에서는 UserDetailsService를 implements 해서 
		//loadUserByUsername()을 구현해야 한다
			.passwordEncoder(new BCryptPasswordEncoder());
	}

	
	
}
