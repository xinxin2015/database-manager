package cn.upc.database.controller.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.upc.database.controller.base.BaseController;
import cn.upc.database.service.operation.OperationService;
import cn.upc.database.utils.ResultData;

@Controller
@RequestMapping(value="/admin",method= {RequestMethod.GET,RequestMethod.POST},produces="application/json;charset=UTF-8")
public class AdminOperation extends BaseController {

	@Autowired
	private OperationService operationService;
	
	@RequestMapping("/addCities")
	@ResponseBody
	public ResultData<Boolean> addCities() {
		try {
			boolean result = operationService.addCity();
			if (!result) {
				return ResultData.error("添加城市失败");
			}
			return ResultData.ok(true);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultData.error("exception");
		}
	}

	@RequestMapping("/addStation")
	@ResponseBody
	public ResultData<Boolean> addStation() {
		try {
			for (int i = 1;i <= 24;i ++) {
				boolean result = operationService.addStation(i);
				if (!result) {
					return ResultData.error("添加站点失败");
				}
			}
			return ResultData.ok(true);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultData.error(ResultData.EXCEPTION);
		}
	}

	@RequestMapping("/addLine")
	@ResponseBody
	public ResultData<Boolean> addLine() {
		try {
			boolean result = operationService.addLine();
			if (!result) {
				return ResultData.error("添加路线失败");
			}
			return ResultData.ok(true);
		} catch (Exception e) {
			return ResultData.error(ResultData.EXCEPTION);
		}
	}
}
