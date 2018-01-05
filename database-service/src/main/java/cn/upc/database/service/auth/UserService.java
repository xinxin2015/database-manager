package cn.upc.database.service.auth;

import org.springframework.stereotype.Service;

import cn.upc.database.model.user.User;

@Service
public interface UserService {
	
	public User adminLogin(String username,String password) throws Exception;
}
