import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.XmlException;

import com.eviware.soapui.impl.wsdl.WsdlInterface;
import com.eviware.soapui.impl.wsdl.WsdlOperation;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.support.wsdl.WsdlImporter;
import com.eviware.soapui.support.SoapUIException;


public class Automation {

	
	
	public Automation()
	{
		
	}
	
	
	
	
	private WSDL loadAndParseWSDL(String url) throws XmlException, IOException, SoapUIException, Exception
	{
		WSDL customWsdl = new WSDL(url);
		WsdlInterface[] wsdls = WsdlImporter.importWsdl(new WsdlProject(),
				url);
		WsdlInterface wsdl = wsdls[0];

		customWsdl.setName(wsdl.getName());
		for (com.eviware.soapui.model.iface.Operation operation : wsdl
				.getOperationList()) {

			Operation customOp = new Operation();
			WsdlOperation op = (WsdlOperation) operation;
			XMLParser parser = new XMLParser(op.createRequest(true));
			CustomNode node = parser.constructTree();
			countLeafNodes(node);
			countStartAndEndCol(node);
			customOp.setName(op.getName());
			customOp.setRequestNode(node);
			parser = new XMLParser(op.createResponse(true));
			node = parser.constructTree();
			countLeafNodes(node);
			countStartAndEndCol(node);
			customOp.setResponseNode(node);
			customWsdl.getOperationList().add(customOp);
		}
		return customWsdl;
		
	}
	
	private WSDL[] loadAndParseWSDLs() throws XmlException, IOException, SoapUIException, Exception
	{
		String[] wsdls=WSDLProperties.getWSDLURLS();
		WSDL[] customWSDLs=new WSDL[wsdls.length];
		for(int i=0;i<wsdls.length;i++)
		{
			customWSDLs[i]=loadAndParseWSDL(wsdls[i]);
			
		}
		return customWSDLs;
		
	}
	
	
	public void createExcels() throws XmlException, IOException, SoapUIException, Exception
	{
		WSDL[] wsdls=loadAndParseWSDLs();
		
		for(WSDL wsdl:wsdls)
		{
			for(Operation operation:wsdl.getOperationList())
			{
			createExcelSheet(wsdl.getName()+".xlsx", operation.getRequestNode(), operation.getName()+"Req");
			createExcelSheet(wsdl.getName()+".xlsx", operation.getResponseNode(), operation.getName()+"Res");
			}
			
		}
		
		
	}
	private void createExcelSheet(String workbookName,CustomNode root,String operationName) throws FileNotFoundException, IOException
	{
		Workbook book =new Workbook(workbookName);
		book.openSheet(operationName);
		
		List<CustomNode> bfs=new ArrayList<CustomNode>();
		bfs.add(root);
		
		for(int i=0;i<bfs.size();i++)
		{
			CustomNode temp=bfs.get(i);
			book.makeEntry(temp);
			bfs.addAll(temp.getChildren());
		}
		book.save();
	}
	
	public void triggerTestCases()
	{
		
		
	}
	public  void countLeafNodes(CustomNode root)
	{
		
		for(int i=0;i<root.getChildren().size();i++)
		{
			CustomNode temp=root.getChildren().get(i);
			if(temp.getChildCount()==0)
			{
				propagateCount(temp);
			}
			countLeafNodes(temp);
		}
		
		
	}
	private  void propagateCount(CustomNode node)
	{
		while(node.getParent()!=null)
		{
			CustomNode parent=node.getParent();
			parent.setGrandChildrenCount(parent.getGrandChildrenCount()+1);
			node=parent;
		}

	}
	private  void countStartAndEndCol(CustomNode root)
	{
		
		List<CustomNode> bfs=new ArrayList<CustomNode>();
		bfs.add(root);
		
		
		for(int i=0;i<bfs.size();i++)
		{
			CustomNode temp=bfs.get(i);
			
			if(temp.getParent()==null)
			{
				temp.setStartCol(0);
				temp.setEndCol(temp.getGrandChildrenCount());
				
			}
			
			int start=temp.getStartCol();
			for(int  j=0;j<temp.getChildren().size();j++)
			{
				
				CustomNode child=temp.getChildren().get(j);
				child.setStartCol(start);
				child.setEndCol(start+(child.getGrandChildrenCount()==0?1:child.getGrandChildrenCount()));
				start=child.getEndCol();
			}
			
			
			
			bfs.addAll(temp.getChildren());

			
		}
		
		
	}

}
