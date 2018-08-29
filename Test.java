import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.wsdl.BindingOperation;

import org.apache.xmlbeans.XmlException;

import com.eviware.soapui.impl.wsdl.WsdlInterface;
import com.eviware.soapui.impl.wsdl.WsdlOperation;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.support.wsdl.WsdlImporter;
import com.eviware.soapui.support.SoapUIException;

public class Test {

	public static void main(String[] args) throws XmlException, IOException, SoapUIException, Exception {
		
		
		 WsdlInterface[] wsdls = WsdlImporter.importWsdl(new WsdlProject(), "http://172.19.98.235:5003/BancsWS/SManageOrderDT?wsdl");
		    WsdlInterface wsdl = wsdls[0];
		 
				 
		   
		    
		    for (com.eviware.soapui.model.iface.Operation operation : wsdl.getOperationList()) {
		        WsdlOperation op = (WsdlOperation) operation;
		       
		   
		
			XMLParser parser = new XMLParser(op.createRequest(true));
			CustomNode node = parser.constructTree();
			countLeafNodes(node);
			countStartAndEndCol(node);
			createExcelSheet(node, op.getName() + "Req");

			parser = new XMLParser(op.createResponse(true));
			node = parser.constructTree();
			countLeafNodes(node);
			countStartAndEndCol(node);
			createExcelSheet(node, op.getName() + "Res");
	      
	    }
	}

	
	public static void createExcelSheet(CustomNode root,String operationName) throws FileNotFoundException, IOException
	{
		Workbook book =new Workbook("test.xlsx");
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
	
	public void configureTree(CustomNode root)
	{
		
		
	}
	
	public static void countLeafNodes(CustomNode root)
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
	private static void propagateCount(CustomNode node)
	{
		while(node.getParent()!=null)
		{
			CustomNode parent=node.getParent();
			parent.setGrandChildrenCount(parent.getGrandChildrenCount()+1);
			node=parent;
		}

	}
	private static void countStartAndEndCol(CustomNode root)
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
