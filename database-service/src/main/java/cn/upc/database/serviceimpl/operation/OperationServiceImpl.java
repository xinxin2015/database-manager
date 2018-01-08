package cn.upc.database.serviceimpl.operation;

import java.util.List;

import cn.upc.database.constants.Constants;
import cn.upc.database.model.operation.Station;
import cn.upc.database.utils.HttpUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.upc.database.dao.operation.OperationDao;
import cn.upc.database.model.operation.City;
import cn.upc.database.service.operation.OperationService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationDao operationDao;

    @Override
    public List<City> getAllCity() throws Exception {
        return operationDao.getAllCities();
    }


    @Transactional
    @Override
    public boolean addCity() throws Exception {
        boolean result = false;
        String url = new String((Constants.CITY_URL + "?appkey=" + Constants.APP_KEY)
                .getBytes(), "utf-8");
        String json = HttpUtil.sendGet(url);
        System.out.println(json);
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        JsonArray cities = jsonObject.get("result").getAsJsonArray();
        for (JsonElement elem : cities) {
            JsonObject jObject = elem.getAsJsonObject();
            City city = new City();
            city.setId(null);
            city.setCityId(jObject.get("cityid").getAsInt());
            city.setName(jObject.get("name").getAsString());
            city.setCode(jObject.get("code").getAsString());
            result = operationDao.addCity(city);
            if (!result) {
                break;
            }
        }
        return result;
    }

    @Transactional
    @Override
    public boolean addStation(Integer id) throws Exception {
        String url = "http://qingdao.8684.cn/city1.php?t=140/p" + id;
        String result = HttpUtil.sendGet(url);
        System.out.println("result = " + id + " " + result);
        String stations[] = result.split(",");
        City city = operationDao.getCityByCode("qingdao");
        if (city == null) {
            return false;
        }
        for (int i = 1;i < stations.length;i ++) {
            Station station = new Station();
            station.setCityId(city.getId());
            station.setName(stations[i]);
            station.setCategory(city.getName());
            System.out.println(station);
            boolean res = operationDao.addStation(station);
            if (!res) {
                return false;
            }
        }
        return true;
    }
}
