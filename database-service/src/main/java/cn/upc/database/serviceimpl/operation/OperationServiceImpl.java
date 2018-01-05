package cn.upc.database.serviceimpl.operation;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.upc.database.dao.operation.OperationDao;
import cn.upc.database.model.operation.City;
import cn.upc.database.service.operation.OperationService;

@Service
public class OperationServiceImpl implements OperationService {

	private OperationDao operationDao;
	
	
	@Override
	public List<City> getAllCity() throws Exception {
		return operationDao.getAllCities();
	}


	@Override
	public boolean addCity() throws Exception {
		
		return false;
	}

}
