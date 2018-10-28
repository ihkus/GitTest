package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.ServerDetails;
import net.miginfocom.swing.MigLayout;

public class ServerDetailsPanel extends JPanel{
	
	private JTextField serverName;
	private JTextField ip;
	private JTextField userName;
	private JPasswordField password;
	private JButton save;
	
	public ServerDetailsPanel()
	{
		
		setLayout(new MigLayout());
		
		serverName=new JTextField();
		ip=new JTextField();
		userName=new JTextField();
		password=new JPasswordField();
		save=new JButton("Save");
		
		add(new JLabel("Server Name:"),"right");
		add(serverName,"wrap");
		
		add(new JLabel("IP:"),"right");
		add(ip,"wrap");
		
		add(new JLabel("UserName:"),"right");
		add(userName,"wrap");
		
		add(new JLabel("Password:"),"right");
		add(password,"wrap");
		
		add(save,"right");
		addActions();
	}

	private void addActions()
	{
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				ServerDetails details=new ServerDetails(serverName.getText(),ip.getText(),userName.getText(),new String(password.getPassword()));
				details.saveServerDetails();
			}
		});
		
	}
	
	
}
