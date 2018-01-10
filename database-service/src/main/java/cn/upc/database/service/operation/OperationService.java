package cn.upc.database.service.operation;

import java.util.List;

import cn.upc.database.model.operation.*;
import org.springframework.stereotype.Service;

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

	public List<String> getLines(String lineName) throws Exception;

	public List<String> getLinesByStation(String stationName) throws Exception;

	public List<InquiryT1M> getChangeLine(String startStation
			, String endStation) throws Exception;

}
