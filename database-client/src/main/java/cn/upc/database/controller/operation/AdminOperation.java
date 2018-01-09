package cn.upc.database.controller.operation;

import cn.upc.database.utils.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.upc.database.controller.base.BaseController;
import cn.upc.database.service.operation.OperationService;
import cn.upc.database.utils.ResultData;

import java.util.Iterator;

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
				boolean result = operationService.addStation();
				if (!result) {
                    return ResultData.error("添加站点失败");
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
			e.printStackTrace();
			return ResultData.error(ResultData.EXCEPTION);
		}
	}

	@RequestMapping("/addTransitBycategory")
	@ResponseBody
	public ResultData<Boolean> addTransitBycategory() {
        try {
            operationService.addTransitByCategory("西海岸线路");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultData.ok(true);
	}

	@RequestMapping("/test")
    @ResponseBody
    public void test() {
	    String url = "http://qingdao.8684.cn/so.php?k=pp&q=开发区1路";
	    String param = "k=pp&q=开发区4路";
	    String result = HttpUtil.sendGet(url);

    }
}
