import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		
		
		XMLParser parser=new XMLParser("<Company><!--optional--><TCS><Employee><Name>Sukhdeep</Name><!--optional--><ID>761309</ID></Employee><Asset>123</Asset></TCS></Company>");
		CustomNode node=parser.constructTree();
		
		countLeafNodes(node);
		createExcelSheet(node);
		
	}

	
	public static void createExcelSheet(CustomNode root)
	{
		Workbook book =new Workbook("test.xlsx");
		book.openSheet("operationName");
		
		List<CustomNode> bfs=new ArrayList<CustomNode>();
		bfs.add(root);
		
		for(int i=0;i<bfs.size();i++)
		{
			CustomNode temp=bfs.get(i);
			book.makeEntry(temp.getName(),temp.getLevel(),temp.getGrandChildrenCount());
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
}
