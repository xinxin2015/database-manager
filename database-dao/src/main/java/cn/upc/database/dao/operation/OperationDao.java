package cn.upc.database.dao.operation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.upc.database.model.operation.City;
import cn.upc.database.utils.MySqlSessionTemplate;

@Repository
public class OperationDao {

	@Autowired
	private MySqlSessionTemplate temp;
	
	public List<City> getAllCities() {
		return temp.selectList("cn.upc.database.userdao.operation.getAllCities");
	}
	
	public boolean addCity(List<City> cities) {
		int i = temp.insert("cn.upc.database.userdao.operation.addCity",cities);
		return i > 0;
	}
}
