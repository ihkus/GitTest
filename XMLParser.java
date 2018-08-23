import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class XMLParser {

	
	private DocumentBuilder parser;
	private Document doc;
	
	private static String optionalComment="optional";
	private static String multipleValueComment="multiple";
	public XMLParser(String data)
	{
		try {
			
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			factory.setIgnoringComments(false);
			parser=factory.newDocumentBuilder();
			
			
		} catch (ParserConfigurationException e) {
			System.out.println("Unable to initialize parser.");
		}
		try {
			doc = parser.parse(new ByteArrayInputStream(data.getBytes()));
			
		} catch (SAXException e) {
			System.out.println("Unable to parser XML data.");
		} catch (IOException e) {
			System.out.println("Unable to parser XML data.");
		}
	}
	
	public CustomNode constructTree()
	{
		Node root=doc.getDocumentElement();
		CustomNode parent=new CustomNode(null,root.getNodeName(),0,false,false);
		constructTree(parent,root);
		return parent;
	}
	
	private void constructTree(CustomNode customNode,Node node)
	{
		boolean optional=false;
		boolean supportMultipleValues=false;
		for(int i=0;i<node.getChildNodes().getLength();i++)
		{
			
			Node temp=node.getChildNodes().item(i);
			if(temp.getNodeType()==Node.COMMENT_NODE)
			{
				optional=temp.getNodeValue().equals(optionalComment);
				supportMultipleValues=temp.getNodeValue().equals(multipleValueComment);
				
				
			}
			else if(temp.getNodeType()==Node.TEXT_NODE)
			{
				customNode.setValue(temp.getNodeValue());
			}
			else
			{
			CustomNode child=new CustomNode(customNode,temp.getNodeName(),customNode.getLevel()+1,optional,supportMultipleValues);
			optional=false;
			supportMultipleValues=false;
			constructTree(child,temp);
			}
		}
		
		
	}
}
