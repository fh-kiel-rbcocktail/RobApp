package application;


import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.deviceModel.JointPosition;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.Frame;
import com.kuka.roboticsAPI.motionModel.PTP;
import com.kuka.roboticsAPI.uiModel.ApplicationDialogType;

public class TransportPosition extends RoboticsAPIApplication {
	private LBR lbr;
	private final static String informationText=
         "This application is intended for floor mounted robots!"+ "\n" +
			"\n" +
			"The robot moves to the transportation position.";
	private JointPosition positionAxis;
	private Frame cartposition;
	
	public void initialize() {
		lbr = getContext().getDeviceFromType(LBR.class);
	}

	public void run() {
		getLogger().info("Show modal dialog and wait for user to confirm");
        int isCancel = getApplicationUI().displayModalDialog(ApplicationDialogType.QUESTION, informationText, "OK", "Cancel");
        if (isCancel == 1)
        {
            return;
        }
        getLogger().info("Move to the 'Ref position'");
		PTP ptpToTransportPosition = ptp(0, 0, 0, 0, 0, 0, 0);
		ptpToTransportPosition.setJointVelocityRel(0.25);
		lbr.move(ptpToTransportPosition);
		
		getLogger().info("Move to the transport position");
		ptpToTransportPosition = ptp(0, Math.toRadians(25), 0, Math.toRadians(90), 0, 0, 0);
		ptpToTransportPosition.setJointVelocityRel(0.25);
		lbr.move(ptpToTransportPosition);
		getLogger().info("Read current position of each Axis");
		positionAxis=lbr.getCurrentJointPosition();
		getApplicationUI().displayModalDialog(ApplicationDialogType.INFORMATION, 
				"Achse 1: "+Math.toDegrees(positionAxis.get(0)) + "\n" +
				"Achse 2: "+Math.toDegrees(positionAxis.get(1)) + "\n" +
				"Achse 3: "+Math.toDegrees(positionAxis.get(2)) + "\n" +
				"Achse 4: "+Math.toDegrees(positionAxis.get(3)) + "\n" +
				"Achse 5: "+Math.toDegrees(positionAxis.get(4)) + "\n" +
				"Achse 6: "+Math.toDegrees(positionAxis.get(5)) + "\n" +
				"Achse 7: "+Math.toDegrees(positionAxis.get(6)) + "\n", "OK");
		getLogger().info("Read current cartesian position");
		cartposition=lbr.getCurrentCartesianPosition(lbr.getFlange());
		getApplicationUI().displayModalDialog(ApplicationDialogType.INFORMATION, 
				"X: "+ cartposition.getX() + "\n" +
				"Y: "+ cartposition.getY() + "\n" +
				"Z: "+ cartposition.getZ() + "\n" , "OK");
		
	}

}
