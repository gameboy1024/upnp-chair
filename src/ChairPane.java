/******************************************************************
 *
 *	CyberUPnP for Java
 *
 *	Copyright (C) Satoshi Konno 2002
 *
 *	File : SampleClockPane.java
 *
 ******************************************************************/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ChairPane extends JPanel {
	
	private ChairDevice chairDev;
	
	public ChairPane(final ChairDevice chairDev){
		this.chairDev = chairDev;
		JButton button = new JButton("Switch");
		final JTextArea text = new JTextArea();
		text.setText(chairDev.isOn() ? "Press": "No Press");
		this.add(button);
		this.add(text);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(chairDev.isOn()){
					chairDev.off();
					text.setText("No Press");
				}else{
					chairDev.on();
					text.setText("Press");
				}
			}
		});
	}

	public ChairDevice getDevice() {
		return chairDev;
	}

	public void setDevice(ChairDevice chairDev) {
		this.chairDev = chairDev;
	}
}
