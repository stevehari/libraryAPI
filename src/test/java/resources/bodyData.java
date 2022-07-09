package resources;

public class bodyData {

	public String getAddBookBody(String Name, String ISBN, String aisle, String Author)
	{
		String Body = "{\r\n"
				+ "\r\n"
				+ "\"name\":\""+Name+"\",\r\n"
				+ "\"isbn\":\""+ISBN+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\""+Author+"\"\r\n"
				+ "}\r\n"
				+ "";
		
		return Body;
	}
	
}
