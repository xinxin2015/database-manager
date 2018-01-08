package cn.upc.database.dao.operation;

import java.util.List;

import cn.upc.database.model.operation.Line;
import cn.upc.database.model.operation.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.upc.database.model.operation.City;
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
}
