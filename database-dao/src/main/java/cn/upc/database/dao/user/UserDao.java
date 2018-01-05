package cn.upc.database.dao.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.upc.database.model.user.User;
import cn.upc.database.utils.MySqlSessionTemplate;

@Repository
public class UserDao {
	
	@Autowired
	private MySqlSessionTemplate temp;

	
	public User adminLogin(String username,String password) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("username", username);
		map.put("password", password);
		return temp.selectOne("cn.upc.database.userdao.adminLogin", map);
	}
}
