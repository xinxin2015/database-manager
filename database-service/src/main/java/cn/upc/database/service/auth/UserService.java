package cn.upc.database.service.auth;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
	
	public boolean adminLogin(String username,String password) throws Exception;
}
