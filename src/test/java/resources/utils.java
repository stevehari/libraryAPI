package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class utils {
	public static RequestSpecification rs ;

	public RequestSpecification requestSpec() throws IOException
	{
		if (rs==null)
		{
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			
			RestAssured.useRelaxedHTTPSValidation();
			
			rs = new RequestSpecBuilder().setBaseUri(getGlobalValue()).setContentType(ContentType.JSON)
					
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					
					.addFilter(ResponseLoggingFilter.logResponseTo(log ))
					
					.build();
			
			return rs;
		}
		
		return rs;
		
	}
	
	
	public static String getGlobalValue() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("I:\\Users\\hari4\\eclipse-workspace\\LibraryDDintegration\\src\\test\\java\\resources\\global.properties");
		
		prop.load(fis);
		
		String BaseURL = prop.getProperty("baseURL");
		
		return BaseURL;
		
		
	}
	
	public static  String getJsonPath(Response Resp, String Key)
	{
		String Response = Resp.asString();
		
		JsonPath js = new JsonPath(Response);
		
		String Actualvalue= js.get(Key);
		return Actualvalue;
	}
	
	public ArrayList<String> getData(String SheetName, String DataNeeded ) throws IOException
	{		// TODO Auto-generated method stub
		
		//Identify Testcases coloum by scanning the entire 1st row
		//once coloumn is identified then scan entire testcase coloum to identify purcjhase testcase row
		//after you grab purchase testcase row = pull all the data of that row and feed into test
		
		
		ArrayList<String> a = new ArrayList<String>();
		
		FileInputStream Fis = new FileInputStream("I:\\Users\\hari4\\OneDrive\\Documents\\Demo\\DemoData.xlsx");
		
		XSSFWorkbook book = new XSSFWorkbook(Fis);
		
		int sheets = book.getNumberOfSheets();
		
		System.out.println(sheets);
		
		
		for(int i= 0;i<sheets;i++)
		{
			if(book.getSheetName(i).equalsIgnoreCase(SheetName))
			{
				XSSFSheet sheet= book.getSheetAt(i);               //sheet is collection of rows
				//getting the access of all the rows
				Iterator<Row> Rows = sheet.iterator();             // row is a  collection of cells
				
				//getting the first row
				
				Row firstRow = Rows.next();
				
				Iterator<Cell> cell = firstRow.cellIterator();          // grab  all a the data in first row
				
				int k=0;
				int coloumn = 0;
				
				while(cell.hasNext())
				{
					Cell c= cell.next();
					
					String CellValue = c.getStringCellValue();  
					
					if(CellValue.equalsIgnoreCase("type"))
					{
					coloumn = k;
					}
					k++;
					
				}	
				
				while(Rows.hasNext())
				{
					Row r = Rows.next();
				
					if(r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(DataNeeded))
					{
						Iterator<Cell> cv = r.cellIterator();
						
						while (cv.hasNext())
						{
							Cell value = cv.next();
							
							if (value.getCellType()==CellType.STRING)
							{
								a.add(value.getStringCellValue());
							}
							else if(value.getCellType()==CellType.NUMERIC)
							{
								a.add(NumberToTextConverter.toText(value.getNumericCellValue()));
							}
							
							
						}
						
					}
				}
					
			}
		}
		return a;
		
	}
}
