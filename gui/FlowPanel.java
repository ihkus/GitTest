package gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class FlowPanel extends JPanel{

	private JLabel flowName;
	private JPanel stepsPanel;
	private JButton addStep;
	public FlowPanel(String flowName)
	{
		setLayout(new MigLayout());
		this.flowName=new JLabel(flowName);
		stepsPanel=new JPanel(new MigLayout());
		addStep=new JButton("Add Step");
		this.add(this.flowName,"wrap");
		this.add(stepsPanel,"grow,push,wrap");	
		this.add(addStep,"wrap");
		
		
	}
	
	
	
}
