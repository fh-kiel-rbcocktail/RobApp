package application;




//import com.kuka.generated.ioAccess.FlexFellowIOGroup;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.lin;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.ptp;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.spl;

import java.io.IOException;

import com.kuka.common.ThreadUtil;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import com.kuka.roboticsAPI.conditionModel.ForceCondition;
import com.kuka.roboticsAPI.conditionModel.JointTorqueCondition;
import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.deviceModel.JointEnum;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.deviceModel.PositionInformation;
import com.kuka.roboticsAPI.executionModel.IFiredConditionInfo;
import com.kuka.roboticsAPI.geometricModel.Frame;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.roboticsAPI.geometricModel.math.CoordinateAxis;
import com.kuka.roboticsAPI.motionModel.IMotion;
import com.kuka.roboticsAPI.motionModel.IMotionContainer;
import com.kuka.roboticsAPI.motionModel.PositionHold;
import com.kuka.roboticsAPI.motionModel.Spline;
import com.kuka.roboticsAPI.motionModel.SplineOrientationType;
import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianImpedanceControlMode;
import com.kuka.roboticsAPI.persistenceModel.IPersistenceEngine;
import com.kuka.roboticsAPI.persistenceModel.XmlApplicationDataSource;
import com.kuka.roboticsAPI.uiModel.ApplicationDialogType;

import de.fh_kiel.cimtt.robotik.EGripper;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * Implementation of a robot application.
 * <p>
 * The application provides a {@link RoboticsAPITask#initialize()} and a 
 * {@link RoboticsAPITask#run()} method, which will be called successively in 
 * the application lifecycle. The application will terminate automatically after 
 * the {@link RoboticsAPITask#run()} method has finished or after stopping the 
 * task. The {@link RoboticsAPITask#dispose()} method will be called, even if an 
 * exception is thrown during initialization or run. 
 * <p>
 * <b>It is imperative to call <code>super.dispose()</code> when overriding the 
 * {@link RoboticsAPITask#dispose()} method.</b> 
 * 
 * @see #initialize()
 * @see #run()
 * @see #dispose()
 */
public class RobotApplication extends RoboticsAPIApplication {
	private Controller kuka_Sunrise_Cabinet_1;
	private LBR lbr_iiwa_7_R800_1;
	private Tool tool;
	private EGripper gripper;
	
	//public Cognex camera;
	private Frame stonePosition;
	//CartesianImpedanceControlMode softMode;
	
	//
	//ForceCondition cond_1;
	private static double maxCartVelocity = 300.0;
	private static double maxCartAcceleration = 500.0;
	private static double maxCartJerk = 5000.0;
	
	private static double maxJointVelocity = 0.15;
	private static double maxJointAcceleration = 0.2;
	private static double maxJointJerk = 0.02;
	
	private static double cartStiffness = 2000.0;
	private static double nullStiffness = 0.5;
	
	//private static int colorCount = 255;
	
	public void initialize() {
		kuka_Sunrise_Cabinet_1 = getController("KUKA_Sunrise_Cabinet_1");
		lbr_iiwa_7_R800_1 = (LBR) getDevice(kuka_Sunrise_Cabinet_1, "LBR_iiwa_7_R800_1");
		tool = getApplicationData().createFromTemplate("EGripper");
		tool.attachTo(lbr_iiwa_7_R800_1.getFlange());
		gripper = new EGripper(kuka_Sunrise_Cabinet_1, lbr_iiwa_7_R800_1, tool);
		//camera = new Cognex();
	}

	
	public void run() {
		// Zähler für Gut-, Schlecht- und Nachzuarbeitende Teile
		int gut = 0;
		int nach = 0;
		int schlecht = 0;
				
		Frame refFrame = null;
			
		// Greifer mit Roboterflansch verbinden
		gripper.attach(lbr_iiwa_7_R800_1.getFlange());
		
		
		// TODO Labor 1: Referenzmessung ( Vermessung des Gutmusters )
		//	Abholen bei RefPart
		//	Vermessen bei MessPos
		//	ablegen bei RefPart
		// TODO Laboratory 1: Reference measurement (measurement of the sample)
		// Pick up at RefPart
		// Measurement at measurement points
		// store at RefPart
		
				
		// TODO Labor 2: Vermessung der übrigen Teile
		//	Abholen bei DynPos
		//	Vermessen bei MessPos
		//	Ablegen bei Gut 1..6, Nacharbeit 1..6 oder Schlecht
		//	Reagieren auf Volle Palette
				
				
		// Abbruch, wenn a) keine Teile vorhanden oder b) Antwort: Palette voll
				
		
		// TODO Labor 3: Statusausgabe an logger()
		//	Ausgabe der Anzahl gesamt
		//	Ausgabe der Anzahl Gut-Teile
		//	Ausgabe der Anzahl Nacharbeit-Teile
		//	Ausgabe der Anzahl Schlecht-Teile	
		
	}

	/**
	 * Auto-generated method stub. Do not modify the contents of this method.
	 */
	public static void main(String[] args) {
		RobotApplication app = new RobotApplication();
		app.runApplication();
	}
}
