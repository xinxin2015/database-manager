package cn.upc.database.serviceimpl.operation;

import java.util.List;

import cn.upc.database.constants.Constants;
import cn.upc.database.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.upc.database.dao.operation.OperationDao;
import cn.upc.database.model.operation.City;
import cn.upc.database.service.operation.OperationService;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationDao operationDao;

    @Override
    public List<City> getAllCity() throws Exception {
        return operationDao.getAllCities();
    }


    @Override
    public boolean addCity() throws Exception {
        String url = new String((Constants.CITY_URL + "?appkey=" + Constants.APP_KEY)
                .getBytes(), "utf-8");
        String result = HttpUtil.sendGet(url);
        result = new String(result.getBytes(),"utf-8");
        System.out.println(result);
        return false;
    }

}
