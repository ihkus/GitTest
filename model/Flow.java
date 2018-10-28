package model;

import java.util.ArrayList;
import java.util.List;

public class Flow {

	private String flowId;
	private String flowName;
	private List<Step> steps;
	public Flow(String flowName)
	{
		this.flowName=flowName;
		steps=new ArrayList<Step>();
	}
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	public String getFlowName() {
		return flowName;
	}
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}
	public List<Step> getSteps() {
		return steps;
	}
	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}
	public void addStep(Step step)
	{
		steps.add(step);
		
	}
	public void removeStep(Step step)
	{
		steps.remove(step);
	}
	
}
