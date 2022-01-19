package com.ams.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ams.dao.UserDAO;
import com.ams.dto.UserDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService2, UserDetailsService {

	@Autowired
	UserDAO dao;
	
	@Autowired
	SqlSessionTemplate session;
	
	@Override
	public List<UserDTO> userList() {
		return dao.userList(session);
	}

	/**
	 *	Spring Security 필수 method
	 *	@param email / 이메일
	 *	@return UserDetails
	 *	@throws UsernameNotFoundException 유저가 없을 때 예외 발생
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}

}
