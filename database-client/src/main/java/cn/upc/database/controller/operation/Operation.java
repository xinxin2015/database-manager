package cn.upc.database.controller.operation;

import cn.upc.database.controller.base.BaseController;
import cn.upc.database.service.operation.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
}