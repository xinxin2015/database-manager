package cn.upc.database.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.upc.database.utils.ResultData;

@Controller
@RequestMapping(value="/auth",method=RequestMethod.GET,produces="application/json")
public class LoginController {

	@RequestMapping("/adminLogin")
	@ResponseBody
	public ResultData<Map<String, Integer>> adminLogin() {
		Map<String, Integer> map = new HashMap<>();
		map.put("one", 1);
		map.put("two", 2);
		return ResultData.ok(map);
	}
}
