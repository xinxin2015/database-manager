package cn.upc.database.controller.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Test extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<>();
		map.put("one", "1");
		map.put("two", "2");
		
		Gson json = new GsonBuilder().enableComplexMapKeySerialization().create();
		json.toJson(map);
		PrintWriter pr = new PrintWriter(resp.getOutputStream());
		System.out.println(json);
		pr.println(json.toString());
		pr.flush();
		pr.close();
	}
}
