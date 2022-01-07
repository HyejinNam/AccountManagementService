package com.ams.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.ams.dto.UserDTO;

public interface UserDAO {

	public abstract List<UserDTO> userList(SqlSessionTemplate session);
	
}
