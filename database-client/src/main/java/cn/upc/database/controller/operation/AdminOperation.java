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
				return ResultData.error("Ìí¼Ó³ÇÊÐÊ§°Ü");
			}
			return ResultData.ok(true);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultData.error("exception");
		}
	}
	
}
