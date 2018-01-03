package cn.upc.database.dao.user;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.upc.database.model.user.User;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSessionTemplate temp;

	
	public User adminLogin(String username,String password) throws Exception {
		Map<String, String> map = new HashMap<>();
		return temp.selectOne("", map);
	}
}
