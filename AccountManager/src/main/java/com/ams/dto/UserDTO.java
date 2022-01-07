package com.ams.dto;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Alias("User")
@Getter
@Setter
@ToString
public class UserDTO {

	String id;
	String pw;
	
	public UserDTO() {}

	public UserDTO(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}

	public UserDTO(String id) {
		super();
		this.id = id;
	}
	
}
