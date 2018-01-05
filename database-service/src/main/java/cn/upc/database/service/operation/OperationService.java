package cn.upc.database.service.operation;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.upc.database.model.operation.City;

@Service
public interface OperationService {
	
	
	public List<City> getAllCity() throws Exception;
	
	public boolean addCity() throws Exception;
}
