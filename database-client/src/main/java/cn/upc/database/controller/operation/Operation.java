package cn.upc.database.controller.operation;

import cn.upc.database.controller.base.BaseController;
import cn.upc.database.model.operation.InquiryT1M;
import cn.upc.database.service.operation.OperationService;
import cn.upc.database.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/op",method= {RequestMethod.GET,RequestMethod.POST},produces="application/json;charset=UTF-8")
public class Operation extends BaseController {

    @Autowired
    private OperationService operationService;

    @RequestMapping("/sendRedirect")
    @ResponseBody
    public String sendRedirect(String t) {
        String url = "http://qingdao.8684.cn/city1.php?t=" + t;
        try {
            String result = operationService.sendRedirect(url);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @RequestMapping("/getLines")
    @ResponseBody
    public ResultData<Map<String,Object>> getLines(String lineName) {
        try {
            Map<String,Object> map = new HashMap<>();
            List<String> lines = operationService.getLines(lineName);
            if (lines == null || lines.isEmpty()) {
                return ResultData.error("获取数据失败，请检查线路名称");
            }
            map.put("lines",lines);
            map.put("lineName",lineName);
            return ResultData.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.error(ResultData.EXCEPTION);
        }
    }

    @RequestMapping("/getLinesByStation")
    @ResponseBody
    public ResultData<List<String>> getLinesByStation(String stationName) {
        try {
            List<String> result = operationService.getLinesByStation(stationName);
            if (result == null || result.isEmpty()) {
                return ResultData.error("获取数据失败，请检查站点名称");
            }
            return ResultData.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.error(ResultData.EXCEPTION);
        }
    }

    @RequestMapping("/getChangeLine")
    @ResponseBody
    public ResultData<Map<String,Object>> getChangeLine(String startStation,String endStation) {
        try {
            Map<String,Object> map = new HashMap<>();
            List<InquiryT1M> list = operationService.getChangeLine(startStation,endStation);
            if (list == null || list.isEmpty()) {
                return ResultData.error("查询失败，请检查输入的站点，暂只支持一次换乘");
            }
            map.put("startStation",startStation);
            map.put("endStation",endStation);
            map.put("list",list);
            return ResultData.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.error(ResultData.EXCEPTION);
        }
    }
}