import java.util.ArrayList;
import java.util.List;

public class CustomNode {

	private int level;
	private List<CustomNode> children;
	private CustomNode parent;
	private String name;
	private boolean optional;
	private boolean supportMultipleValues;
	private String value;
	private int grandChildrenCount;
	public CustomNode(CustomNode parent,String name,int level, boolean optional, boolean supportMultipleValues)
	{
		this.parent=parent;
		this.name=name;
		this.level=level;
		this.optional=optional;
		this.supportMultipleValues=supportMultipleValues;
		
		children=new ArrayList<CustomNode>();
		if(parent!=null)
		parent.produceChild(this);
		
		
	}

	private void produceChild(CustomNode child) {
		children.add(child);
		
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<CustomNode> getChildren() {
		return children;
	}

	public void setChildren(List<CustomNode> children) {
		this.children = children;
	}

	public CustomNode getParent() {
		return parent;
	}

	public void setParent(CustomNode parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOptional() {
		return optional;
	}

	public void setOptional(boolean optional) {
		this.optional = optional;
	}

	public boolean isSupportMultipleValues() {
		return supportMultipleValues;
	}

	public void setSupportMultipleValues(boolean supportMultipleValues) {
		this.supportMultipleValues = supportMultipleValues;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getGrandChildrenCount() {
		return grandChildrenCount;
	}

	public void setGrandChildrenCount(int grandChildrenCount) {
		this.grandChildrenCount = grandChildrenCount;
	}

	public int getChildCount()
	{
		return children.size();
	}
	
	@Override
	public String toString() {
		return name+" "+optional+" "+supportMultipleValues;
	}
	
	
	
}
