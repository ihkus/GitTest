import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		
		
		XMLParser parser=new XMLParser("<Company><!--optional--><TCS><Employee><Name>Sukhdeep</Name><!--optional--><ID>761309</ID></Employee></TCS></Company>");
		CustomNode node=parser.constructTree();
		
		countGrandChildren(node);
		createExcelSheet(node);
		
	}

	
	public static void createExcelSheet(CustomNode root)
	{
		Workbook book =new Workbook("test.xls");
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
	
	public static void countGrandChildren(CustomNode root)
	{
		for(int i=0;i<root.getChildren().size();i++)
		{
			countGrandChildren(root.getChildren().get(i));
						
		}
		if(root.getParent()!=null)
		root.getParent().setGrandChildrenCount(root.getParent().getGrandChildrenCount()+root.getGrandChildrenCount());

	}
	
	
}
