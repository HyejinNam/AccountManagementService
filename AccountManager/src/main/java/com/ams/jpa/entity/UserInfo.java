package com.ams.jpa.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author vi05
 *
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class UserInfo implements UserDetails {

	@Id
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "auth")
	private String auth;

	@Builder
	public UserInfo(String email, String password, String auth) {
		this.email = email;
		this.password = password;
		this.auth = auth;
	}

	/**
	 * 사용자의 권한을 콜렉션 형태로 반환 단, 클래스 자료형은 GrantedAuthority를 구현해야한다
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 사용자의 권한을 콜렉션 형태로 반환해야하는데
		// 권한이 중복되면 안되기 때문에 Set을 사용한다
		Set<GrantedAuthority> roles = new HashSet<>();
		for (String role : auth.split(",")) {
			roles.add(new SimpleGrantedAuthority(role));
		}
		return roles;
	}

	/**
	 * 사용자의 id 를 반환 ( unique 한 값 )
	 */
	@Override
	public String getUsername() {
		return email;
	}

	/**
	 * 사용자의 password를 반환
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * 계정 만료 여부 반환
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true; // true : 만료되지 않았음
	}

	/**
	 * 계정 잠금 여부 반환
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true; // true : 잠금되지 않았음
	}

	/**
	 * 패스워드의 만료 여부 반환
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true; // true : 만료되지 않았음
	}

	/**
	 * 계정 사용가능 여부 반환
	 */
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true; // true : 사용가능
	}

}
