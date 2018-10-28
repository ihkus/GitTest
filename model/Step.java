package model;

public class Step {

	private String stepId;
	private String stepName;
	private StepType type;
	private String componentName;
	private String fileId;
	private String sql;
	private String sqlResult;
	

	
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	public StepType getType() {
		return type;
	}
	public void setType(StepType type) {
		this.type = type;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getSqlResult() {
		return sqlResult;
	}
	public void setSqlResult(String sqlResult) {
		this.sqlResult = sqlResult;
	}
	
	
	public void saveStep()
	{
		
		
		
	}
	
	public void deleteStep()
	{
		
	}
	
	
}
