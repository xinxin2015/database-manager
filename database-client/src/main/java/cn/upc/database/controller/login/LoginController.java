package cn.upc.database.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.upc.database.service.auth.UserService;
import cn.upc.database.utils.ResultData;

@Controller
@RequestMapping(value="/auth",method= {RequestMethod.GET,RequestMethod.POST},produces="application/json")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/adminLogin")
	@ResponseBody
	public ResultData<Boolean> adminLogin(String username,String password) {
		try {
			boolean result = userService.adminLogin(username, password);
			if (!result) {
				return ResultData.error("username or password is wrong");
			}
			return ResultData.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultData.error("exception");
		}
	}
	
	
}
