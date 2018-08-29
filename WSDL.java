import java.util.ArrayList;
import java.util.List;


public class WSDL {

	
	
	private List<Operation> operationList;
	private String url;
	private String name;
	public WSDL(String url)
	{
		this.url=url;
		operationList=new ArrayList<Operation>();
	}
	
	
	
	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public List<Operation> getOperationList() {
		return operationList;
	}

	public void setOperationList(List<Operation> operationList) {
		this.operationList = operationList;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}
	
	
	
}

