/******************************************************************
*
*	CyberUPnP for Java
*
*	Copyright (C) Satoshi Konno 2002-2003
*
*	File : SampleClock.java
*
******************************************************************/

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.cybergarage.util.*;

import org.cybergarage.upnp.device.*;

public class ChairFrame extends JFrame implements WindowListener 
{
	private final static String TITLE = "Intelligent Chair";
	private ChairDevice chairDev;
	private ChairPane chairPane;
	
	public ChairFrame()
	{
		super(TITLE);

		try {
			chairDev = new ChairDevice();
		}
		catch (InvalidDescriptionException e) {
			Debug.warning(e);
		}
				
		getContentPane().setLayout(new BorderLayout());

		chairPane = new ChairPane(chairDev);
		chairDev.setComponent(chairPane);
		getContentPane().add(chairPane, BorderLayout.CENTER);

		addWindowListener(this);
		this.setPreferredSize(new Dimension(180	, 190));
		this.setBackground(Color.WHITE);
		
		pack();
		setVisible(true);
		
		chairDev.start();
	}

	public ChairPane getClockPanel()
	{
		return chairPane;
	}

	public ChairDevice getClockDevice()
	{
		return chairDev;
	}
		
	////////////////////////////////////////////////
	//	main
	////////////////////////////////////////////////
	
	public void windowActivated(WindowEvent e) 
	{
	}
	
	public void windowClosed(WindowEvent e) 
	{
	}
	
	public void windowClosing(WindowEvent e) 
	{
		chairDev.stop();
		System.exit(0);
	}
	
	public void windowDeactivated(WindowEvent e) 
	{
	}
	
	public void windowDeiconified(WindowEvent e) 
	{
	}
	
	public void windowIconified(WindowEvent e) 
	{
	}
	
	public void windowOpened(WindowEvent e)
	{
	}
	

	////////////////////////////////////////////////
	//	main
	////////////////////////////////////////////////

	public static void main(String args[]) 
	{
		//Debug.on();
		ChairFrame sampClock = new ChairFrame();
	}

}

