package cn.upc.database.serviceimpl.operation;

import java.util.List;

import cn.upc.database.constants.Constants;
import cn.upc.database.model.operation.Line;
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
        for (int i = 1; i < stations.length; i++) {
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

    @Override
    public String sendRedirect(String url) throws Exception {
        return HttpUtil.sendGet(url);
    }

    @Override
    public boolean addLine() throws Exception {
        for (int i = 1; i <= 7; i++) {
            String url = "http://qingdao.8684.cn/city1.php?t=140/pp" + i;
            String result = HttpUtil.sendGet(url);
            String[] lines = result.split(",");
            switch (i) {
                case 1:
                    for (int j = 1; j < lines.length; j++) {
                        Line line = new Line();
                        line.setCategory("市区线路");
                        line.setName(lines[j]);
                        operationDao.addLine(line);
                    }
                    break;

                case 2:
                    for (int j = 1; j < lines.length; j++) {
                        Line line = new Line();
                        line.setCategory("隧道线路");
                        line.setName(lines[j]);
                        operationDao.addLine(line);
                    }
                    break;
                case 3:
                    for (int j = 1; j < lines.length; j++) {
                        Line line = new Line();
                        line.setCategory("西海岸线路");
                        line.setName(lines[j]);
                        operationDao.addLine(line);
                    }
                    break;
                case 4:
                    for (int j = 1; j < lines.length; j++) {
                        Line line = new Line();
                        line.setCategory("机场快线");
                        line.setName(lines[j]);
                        operationDao.addLine(line);
                    }
                    break;
                case 5:
                    for (int j = 1; j < lines.length; j++) {
                        Line line = new Line();
                        line.setCategory("旅游专线");
                        line.setName(lines[j]);
                        operationDao.addLine(line);
                    }
                    break;
                case 6:
                    for (int j = 1; j < lines.length; j++) {
                        Line line = new Line();
                        line.setCategory("蓝色硅谷线路");
                        line.setName(lines[j]);
                        operationDao.addLine(line);
                    }
                    break;
                case 7:
                    for (int j = 1; j < lines.length; j++) {
                        Line line = new Line();
                        line.setCategory("轨道交通");
                        line.setName(lines[j]);
                        operationDao.addLine(line);
                    }
                    break;
            }
        }
        return true;
    }
}
