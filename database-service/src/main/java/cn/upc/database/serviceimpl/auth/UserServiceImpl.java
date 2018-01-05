package cn.upc.database.serviceimpl.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.upc.database.dao.user.UserDao;
import cn.upc.database.model.user.User;
import cn.upc.database.service.auth.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User adminLogin(String username, String password) throws Exception {
		return userDao.adminLogin(username,password);
	}

}
