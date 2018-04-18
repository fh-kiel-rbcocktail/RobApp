package de.fh_kiel.cimtt.robotik;




import java.io.IOException;
import java.util.Properties;

import com.kuka.common.ThreadUtil;
import com.kuka.generated.ioAccess.*;
import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.Tool;

public class EGripper extends Gripper{
	FlexFellowIOGroup digitalOut;
	
	/**
	 * 
	 * @param controller Verwendeter Controller
	 * @param robot	Verwendetet Roboter
	 * @param tool Verwendeter Greifer
	 */
	public EGripper(Controller controller, LBR robot, Tool tool){
		super(controller, robot, tool);

	    	

	    // Hier aufbauen der GUI mit den Einstellungen 
	    // in der Property-Datei
		
		digitalOut = new FlexFellowIOGroup(controller);
	}
	
	/**
	 * @return true wenn Greifer vollständig geöffnet
	 * @return false wenn nicht
	 */
	public boolean close(){
		digitalOut.setCloseGripper(true);
		digitalOut.setOpenGripper(false);
		for(int i = 0; i < 10; i++){
			if(digitalOut.getGripperIsClose()){
				return true;
			}
			ThreadUtil.milliSleep(100);
		}
		return false;
	}
	
	/**
	 * @return true wenn Greifer vollständig geschlossen
	 * @return false wenn nicht
	 */
	public boolean open(){
		digitalOut.setCloseGripper(false);
		digitalOut.setOpenGripper(true);
		for(int i = 0; i < 10; i++){
			if(digitalOut.getGripperIsOpen()){
				digitalOut.setOpenGripper(false);
				return true;
			}
			ThreadUtil.milliSleep(100);
		}
		digitalOut.setOpenGripper(false);
		return false;
	}
}
