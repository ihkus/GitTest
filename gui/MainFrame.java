package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class MainFrame extends JFrame{

	
	private JMenuBar menuBar;
	public MainFrame()
	{
		setSize(300,300);
		
		addMenuBar();

		add(createProgressBar(0, 100,50, Color.red));
		setVisible(true);
		
		
	}
	
	private void addMenuBar()
	{
		
		menuBar=new JMenuBar();
		JMenu server=new JMenu("Server");
		
		JMenuItem addServer=new JMenuItem("Add Server");
		server.add(addServer);
		menuBar.add(server);
		
		this.setJMenuBar(menuBar);
		
		
	}
	
	
	public JProgressBar createProgressBar(int min,int max,int value,Color color)
	{
		
		JProgressBar bar=new JProgressBar(min, max);
		bar.setForeground(color);
		bar.setValue(value);
		
		return bar;
	}
	
	 private static class MyProgressUI extends BasicProgressBarUI {

	        private Rectangle r = new Rectangle();

	        @Override
	        protected void paintIndeterminate(Graphics g, JComponent c) {
	            Graphics2D g2d = (Graphics2D) g;
	            g2d.setRenderingHint(
	                RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);
	            r = getBox(r);
	            g.setColor(progressBar.getForeground());
	            g.fillOval(r.x, r.y, r.width, r.height);
	        }
	    }
}
