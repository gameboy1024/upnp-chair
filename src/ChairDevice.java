import java.io.*;
import java.awt.*;

import org.cybergarage.upnp.*;
import org.cybergarage.upnp.device.*;
import org.cybergarage.upnp.control.*;


public class ChairDevice extends Device implements ActionListener,
		QueryListener {
	

	private final static String DESCRIPTION_FILE_NAME = "description/description.xml";

	private StateVariable stateVar;

	public ChairDevice() throws InvalidDescriptionException
	{
		super(new File(DESCRIPTION_FILE_NAME));

		Action getPowerAction = getAction("GetState");
		getPowerAction.setActionListener(this);
		
		ServiceList serviceList = getServiceList();
		Service service = serviceList.getService(0);
		service.setQueryListener(this);

		stateVar = getStateVariable("State");

		Argument stateArg = getPowerAction.getArgument("State");
		StateVariable stateState = stateArg.getRelatedStateVariable();
		AllowedValueList allowList = stateState.getAllowedValueList();
		for (int n=0; n<allowList.size(); n++) 
			System.out.println("[" + n + "] = " + allowList.getAllowedValue(n));
			
		AllowedValueRange allowRange = stateState.getAllowedValueRange();
		System.out.println("maximum = " + allowRange.getMaximum());
		System.out.println("minimum = " + allowRange.getMinimum());
		System.out.println("step = " + allowRange.getStep());
	}

	////////////////////////////////////////////////
	//	Component
	////////////////////////////////////////////////

	private Component comp;
	
	public void setComponent(Component comp)
	{
		this.comp = comp;	
	}
	
	public Component getComponent()
	{
		return comp;
	}
	
	////////////////////////////////////////////////
	//	on/off
	////////////////////////////////////////////////

	private boolean onFlag = false;
	
	public void on()
	{
		onFlag = true;
		stateVar.setValue("on");
	}

	public void off()
	{
		onFlag = false;
		stateVar.setValue("off");
	}

	public boolean isOn()
	{
		return onFlag;
	}
	
	public void setPowerState(String state)
	{
		if (state == null) {
			off();
			return;
		}
		if (state.compareTo("1") == 0) {
			on();
			return;
		}
		if (state.compareTo("0") == 0) {
			off();
			return;
		}
	}
	
	public String getPowerState()
	{
		if (onFlag == true)
			return "1";
		return "0";
	}

	////////////////////////////////////////////////
	// ActionListener
	////////////////////////////////////////////////

	public boolean actionControlReceived(Action action)
	{
		String actionName = action.getName();

		boolean ret = false;
		
		if (actionName.equals("GetState") == true) {
			String state = getPowerState();
			Argument powerArg = action.getArgument("State");
			powerArg.setValue(state);
			ret = true;
		}

		comp.repaint();

		return ret;
	}

	////////////////////////////////////////////////
	// QueryListener
	////////////////////////////////////////////////

	public boolean queryControlReceived(StateVariable stateVar)
	{
		stateVar.setValue(getPowerState());
		return true;
	}

	////////////////////////////////////////////////
	// update
	////////////////////////////////////////////////

	public void update()
	{
	}		

}
