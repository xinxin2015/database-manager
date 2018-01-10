package cn.upc.database.serviceimpl.operation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.upc.database.constants.Constants;
import cn.upc.database.model.operation.*;
import cn.upc.database.utils.HttpUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.upc.database.dao.operation.OperationDao;
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
    public boolean addStation() throws Exception {

        String[] categories = {"数字", "A", "B", "C", "D", "E", "F", "G",
                "H", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "W", "X", "Y", "Z"};
        for (int i = 0; i < categories.length; i++) {
            String url = "http://qingdao.8684.cn/city1.php?t=140/p" + (i + 1);
            String result = HttpUtil.sendGet(url);
            System.out.println("result = " + (i + 1) + " " + result);
            String stations[] = result.split(",");
            City city = operationDao.getCityByCode("qingdao");
            if (city == null) {
                return false;
            }
            for (int j = 1; j < stations.length; j++) {
                Station station = new Station();
                if ((i + 1) == 1) {
                    station.setCityId(city.getId());
                    station.setName(stations[j]);
                    station.setCategory("数字");
                } else {
                    station.setCityId(city.getId());
                    station.setName(stations[j]);
                    station.setCategory(categories[i]);
                }
                System.out.println(station);
                boolean res = operationDao.addStation(station);
                if (!res) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String sendRedirect(String url) throws Exception {
        return HttpUtil.sendGet(url);
    }

    @Transactional
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

    @Transactional
    @Override
    public boolean addTransitByCategory(String category) throws Exception {
        List<Line> lines = this.getLineByCategory(category);
        if (lines == null || lines.isEmpty()) {
            return false;
        }
        for (Line line : lines) {
            List<String> temp = parseLines(line.getName());
            Station startStation = operationDao.getStationByName(temp.get(0));
            Station endStation = operationDao.
                    getStationByName(temp.get(temp.size() / 2));
            Transit toTransit = new Transit();
            toTransit.setLineId(line.getId());
            toTransit.setType(Transit.TYPE_TO);
            toTransit.setStartStation(startStation.getId());
            toTransit.setEndStation(endStation.getId());
            boolean addTraRes = operationDao.addTransit(toTransit);
            if (!addTraRes) {
                System.out.println("添加transit失败");
                return false;
            }
            for (int i = 0;i < temp.size() / 2;i ++) {
                Rote rote = new Rote();
                Station station = operationDao.getStationByName(temp.get(i));
                if (station == null) {
                    System.out.println("station查询失败,stationName = " + temp.get(i));
                    return false;
                }
                rote.setStationId(station.getId());
                rote.setTransitId(toTransit.getId());
                rote.setPosition(i);
                boolean addRoteRes = operationDao.addRote(rote);
                if (!addRoteRes) {
                    System.out.println("添加rote失败");
                    return false;
                }
            }
        }
        return true;
    }

    private List<String> parseLines(String lineName) {
        List<String> temp = new ArrayList<>();
        String params = "k=pp&q=" + lineName;
        String url = Constants.MY_URL + "?" + params;
        String result = HttpUtil.sendGet(url);
        Document doc = Jsoup.parse(result);
        Elements element = doc.getElementsByClass("bus_site_layer");
        Elements e = element.select("a");
        for (Iterator<Element> it = e.iterator(); it.hasNext();) {
            Element elem = it.next();
            if (elem.text().equals("滨海新村红树林度假世界") ||
                    elem.text().equals("滨海新村(红树林度假世界)")) {
                temp.add("滨海新村红树林度假世界");
            } else if (elem.text().equals("科技街(颐高数码广场)")){
                temp.add("科技街");
            } else if (elem.text().equals("喜鹊山路(黄山路)")){
                temp.add("喜鹊山路");
            } else if (elem.text().equals("铁橛山路(铁山路)")){
                temp.add("铁橛山路");
            } else if (elem.text().equals("唐岛湾路(长城路)")){
                temp.add("唐岛湾路");
            } else if (elem.text().equals("东岳中路(泰山路)")){
                temp.add("东岳中路");
            } else if (elem.text().equals("朝阳山路(北京路)")){
                temp.add("朝阳山路");
            } else if (elem.text().equals("月亮湾路(大连路)")){
                temp.add("月亮湾路");
            } else if (elem.text().equals("公园路(枣园路)")){
                temp.add("公园路");
            } else if (elem.text().equals("张仓(铁山换乘站)") ||
                    elem.text().equals("铁山换乘站(张仓)") ||
                    elem.text().equals("(张仓)铁山换乘站")){
                temp.add("张仓");
            } else if (elem.text().equals("董大庄(麦墩)")){
                temp.add("董大庄");
            } else if (elem.text().equals("肖家贡(营上)")){
                temp.add("肖家贡");
            } else {
                temp.add(elem.text());
            }

        }
        System.out.println(temp);
        return temp;
    }

    @Override
    public List<Line> getLineByCategory(String category) throws Exception {
        return operationDao.getLineByCategory(category);
    }

    @Override
    public boolean addRote(Rote rote) throws Exception {
        return operationDao.addRote(rote);
    }

    @Override
    public Line getLineByName(String name) throws Exception {
        return operationDao.getLineByName(name);
    }

    @Override
    public Station getStationByName(String name) throws Exception {

        return operationDao.getStationByName(name);
    }

    @Override
    public List<String> getLines(String lineName) throws Exception {
        return operationDao.getLines(lineName);
    }

    @Override
    public List<String> getLinesByStation(String stationName) throws Exception {
        return operationDao.getLinesByStation(stationName);
    }
}
