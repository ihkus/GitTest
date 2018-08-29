import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class WSDLProperties {

	private static String[] WSDLUrls;
	private static String IP;
	private static String port;
	
	private static Properties prop;
	
	static {
		
		prop=new Properties();
		try {
			prop.load(new FileReader("WSDL.properties"));
		} catch (FileNotFoundException e) {
			System.out.println("WSDL.properties file not found");
			
		} catch (IOException e) {
			System.out.println("Problem while reading WSDL.properties");
			
		}
		
		WSDLUrls=((String)prop.get("WSDLUrls")).split(";");
		IP=(String)prop.get("IP");
		port=(String)prop.get("PORT");
		
		for(int i=0;i<WSDLUrls.length;i++)
		{
			WSDLUrls[i]="http://"+IP+":"+port+"/"+WSDLUrls[i];
			
		}
	}	
	
	public static String[] getWSDLURLS()
	{
		
		return WSDLUrls;
	}
	
}
