package com.ams.jpa.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ams.dto.UserInfoDto;
import com.ams.jpa.entity.UserInfo;
import com.ams.jpa.repo.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;

	/**
	 * Spring Security 필수 method 구현
	 * 
	 * @param email 이메일
	 * @return UserDetails
	 * @throws UsernameNotFoundException 유저가 없을 때 예외 발생
	 * 
	 */
	@Override
	// 기본적인 반환 타입은 UserDetails, UserDetails를 상속받은
	// UserInfo로 반환 타입 지정 (자동으로 다운 캐스팅됨)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException((email)));
	}

	public Long save(UserInfoDto infoDto) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		infoDto.setPassword(encoder.encode(infoDto.getPassword()));
		return userRepository.save(UserInfo.builder()
				.email(infoDto.getEmail())
				.auth(infoDto.getAuth())
				.password(infoDto.getPassword()).build()).getCode();
	}
	
}
