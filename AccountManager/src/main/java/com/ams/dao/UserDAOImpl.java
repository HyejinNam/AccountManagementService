package com.ams.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.ams.dto.UserDTO;

@Repository
public class UserDAOImpl implements UserDAO{

	@Override
	public List<UserDTO> userList(SqlSessionTemplate session) {
		return session.selectList("UserMapper.userList");
	}

	
	
}
