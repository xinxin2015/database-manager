package cn.upc.database.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.upc.database.controller.base.BaseController;
import cn.upc.database.model.user.User;
import cn.upc.database.service.auth.UserService;
import cn.upc.database.utils.ResultData;

@Controller
@RequestMapping(value="/auth",method= {RequestMethod.GET,RequestMethod.POST},produces="application/json;charset=UTF-8")
public class LoginController extends BaseController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/adminLogin")
	@ResponseBody
	public ResultData<Boolean> adminLogin(String username,String password) {
		try {
			User result = userService.adminLogin(username, password);
			if (result == null) {
				return ResultData.error(ResultData.PASSWORDISWRONG);
			}
			session.setAttribute("loginUser", result);
			return ResultData.ok(true);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultData.error(ResultData.EXCEPTION);
		}
	}
	
	
}
