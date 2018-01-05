package cn.upc.database.controller.base;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import cn.upc.database.model.user.User;

public class BaseController {
	
	@Autowired
	protected HttpSession session;
	
	public User getLoginUser() {
		return (User)session.getAttribute("loginUser");
	}
}
