package cn.upc.database.dao.operation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.upc.database.model.operation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.upc.database.utils.MySqlSessionTemplate;

@Repository
public class OperationDao {

	@Autowired
	private MySqlSessionTemplate temp;
	
	public List<City> getAllCities() throws Exception {
		return temp.selectList("cn.upc.database.userdao.operation.getAllCities");
	}
	
	public boolean addCity(City city) throws Exception {
		int i = temp.insert("cn.upc.database.userdao.operation.addCity",city);
		return i > 0;
	}

	public boolean addStation(Station station) throws Exception {
		int i = temp.insert("cn.upc.database.userdao.operation.addStation",station);
		return i > 0;
	}

	public City getCityByCode(String code) throws Exception {
		return temp.selectOne("cn.upc.database.userdao.operation.getCityByCode",code);
	}

	public boolean addLine(Line line) throws Exception {
		int i = temp.insert("cn.upc.database.userdao.operation.addLine",line);
		return i > 0;
	}

	public List<Line> getLineByCategory(String category) throws Exception {
		return temp.selectList("cn.upc.database.userdao.operation.getLineByCategory",category);
	}

	public Station getStationByName(String name) throws Exception {
		return temp.selectOne("cn.upc.database.userdao.operation.getStationByName",name);
	}

	public boolean addRote(Rote rote) throws Exception {
	    int i = temp.insert("cn.upc.database.userdao.operation.addRote",rote);
	    return i > 0;
    }

    public Line getLineByName(String name) throws Exception {
	    return temp.selectOne("cn.upc.database.userdao.operation.getLineByName",name);
    }

    public boolean addTransit(Transit transit) throws Exception {
	    int i = temp.insert("cn.upc.database.userdao.operation.addTransit",transit);
	    return i > 0;
    }

    public List<String> getLines(String lineName) throws Exception {
		return temp.selectList("cn.upc.database.userdao.operation.getLines",lineName);
	}

	public List<String> getLinesByStation(String stationName) throws Exception {
	    return temp.selectList("cn.upc.database.userdao.operation.getLinesByStation",stationName);
    }

    public List<InquiryT1> getChangeLine(Integer startId,Integer endId) throws Exception {
		Map<String,Integer> map = new HashMap<>();
		map.put("startId",startId);
		map.put("endId",endId);
		return temp.selectList("cn.upc.database.userdao.operation.getChangeLine",map);
	}

	public Line getLineByTransitId(Integer transitId) throws Exception {
	    return temp.selectOne("cn.upc.database.userdao.operation.getLineByTransitId",
                transitId);
    }

    public Station getStationById(Integer id) throws Exception {
	    return temp.selectOne("cn.upc.database.userdao.operation.getStationById",id);
    }
}
