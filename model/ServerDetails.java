package model;

public class ServerDetails {

	private String name;
	private String username;
	private String password;
	private String ip;
	public ServerDetails(String name,String ip, String username, String password) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.ip = ip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public void saveServerDetails()
	{
		
		
	}
	
	public void deleteServerDetails()
	{
		
	}
	
	
}
