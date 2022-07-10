package resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class bodyData extends utils {

	public HashMap<String, Object> getAddBookBody() throws IOException
	{
		
		ArrayList<String> a= getData("Library","1");
	
		HashMap<String, Object>  map = new HashMap<>();
		map.put("name", a.get(1));
		map.put("isbn", a.get(2));
		map.put("aisle", a.get(3));
		map.put("author", a.get(4));
		
		return map;
	}
	
	public String getDeleteBookBody(String ID)
	{
		
		String body = "{\r\n"
				+ " \r\n"
				+ "\"ID\" : \""+ID+"\"\r\n"
				+ " \r\n"
				+ "} \r\n"
				+ "";
		
		return body;
		
	}
	
}
