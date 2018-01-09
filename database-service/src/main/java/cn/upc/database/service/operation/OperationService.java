package cn.upc.database.service.operation;

import java.util.List;

import cn.upc.database.model.operation.Line;
import cn.upc.database.model.operation.Rote;
import cn.upc.database.model.operation.Station;
import org.springframework.stereotype.Service;

import cn.upc.database.model.operation.City;

@Service
public interface OperationService {
	
	
	public List<City> getAllCity() throws Exception;
	
	public boolean addCity() throws Exception;

	public boolean addStation() throws Exception;

	public String sendRedirect(String url) throws Exception;

	public boolean addLine() throws Exception;

	public boolean addTransitByCategory(String category) throws Exception;

	public List<Line> getLineByCategory(String category) throws Exception;

	public boolean addRote(Rote rote) throws Exception;

	public Line getLineByName(String name) throws Exception;

	public Station getStationByName(String name) throws Exception;

}
