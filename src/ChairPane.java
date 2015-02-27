/******************************************************************
 *
 *	CyberUPnP for Java
 *
 *	Copyright (C) Satoshi Konno 2002
 *
 *	File : SampleClockPane.java
 *
 ******************************************************************/

import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ChairPane extends JPanel {
	
	private ChairDevice chairDev;
	private final static String CHAIR_ON_PANEL_IMAGE = "images/chair-on.jpg";
	private final static String CHAIR_OFF_PANEL_IMAGE = "images/chair-off.jpg";
	private Image onChair;
	private Image offChair;
	
	public ChairPane(final ChairDevice chairDev){
		this.chairDev = chairDev;
		final ImageIcon chairIcon = new ImageIcon();
		try {
			onChair = ImageIO.read(new File(CHAIR_ON_PANEL_IMAGE));
			offChair = ImageIO.read(new File(CHAIR_OFF_PANEL_IMAGE));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		chairIcon.setImage(offChair);
		JButton chairButton = new JButton(chairIcon);
		this.add(chairButton);
		KeyboardFocusManager.getCurrentKeyboardFocusManager()
		  .addKeyEventDispatcher(new KeyEventDispatcher() {
		      @Override
		      public boolean dispatchKeyEvent(KeyEvent e) {
		    	  if(chairDev.isOn()){
						chairDev.off();
						chairIcon.setImage(offChair);
					}else{
						chairDev.on();
						chairIcon.setImage(onChair);
					}
		    	  return false;
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
