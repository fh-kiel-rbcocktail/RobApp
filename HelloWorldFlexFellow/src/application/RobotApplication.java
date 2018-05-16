package application;




//import com.kuka.generated.ioAccess.FlexFellowIOGroup;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import application.object.Ingredient;

import application.object.*;

import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import com.kuka.roboticsAPI.applicationModel.tasks.RoboticsAPITask;
import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.Frame;
import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.roboticsAPI.uiModel.ApplicationDialogType;

import de.fh_kiel.cimtt.robotik.EGripper;

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
	private final static String informationText=
			"Which coctail do you want";

	public void initialize() {
		kuka_Sunrise_Cabinet_1 = getController("KUKA_Sunrise_Cabinet_1");
		lbr_iiwa_7_R800_1 = (LBR) getDevice(kuka_Sunrise_Cabinet_1, "LBR_iiwa_7_R800_1");
		tool = getApplicationData().createFromTemplate("EGripper");
		tool.attachTo(lbr_iiwa_7_R800_1.getFlange());
		gripper = new EGripper(kuka_Sunrise_Cabinet_1, lbr_iiwa_7_R800_1, tool);
		//camera = new Cognex();
	}

	public int getPositionOfBottle (String[]positionBottle, String ingredientName){
		int index = Arrays.binarySearch(positionBottle, ingredientName);
		if(index >= 0){
			return index + 1;
		}
		return 0;
	}
	
	public void run() {
		// Zähler für Gut-, Schlecht- und Nachzuarbeitende Teile
		int gut = 0;
		int nach = 0;
		int schlecht = 0;
				
		Frame refFrame = null;
			
		// Greifer mit Roboterflansch verbinden
		gripper.attach(lbr_iiwa_7_R800_1.getFlange());
		
		/*
		 * 19.04.2018 - Test sequence 1
		 * 
		 * - Cup must be placed higher than the table level, otherwise --> collision
		 * - moveNear-function will move straight into bottle holder 
		 * - moving the gripper to grab the glass needs a more detailed path --> gripper tips over the cup
		 * 
		 * TODO: 
		 * - design cup-holder to place it higher than table
		 * - configure moveNear-function Z-levels
		 * - define path for gripper to move to horizontal level before moving near the cup
		 * 
		 * */
		//Initialize the position of bottle 
        String[] positionBottle = {"milk", "cafe", "orange", "tea"};
        
		// Initialize menu
        IRecipeScript menu = new RecipeScript();
        String[] mS = new String[5];
		/*
		 *  For user panel
		 */
		String menuText = "Please order the drink!";
		String errorText = "Sorry, invalid drink. Please choose again!";
		for(int i = 0; i < Math.max(menu.menuSize(),5); i += 1) {
			mS[i] = menu.getNextRecipe(i).getName();
        }
		int orderNbr = getApplicationUI().displayModalDialog(ApplicationDialogType.QUESTION, menuText,mS[0],mS[1],mS[2],mS[3],mS[4]);
//        while(orderNbr >= menu.menuSize()) {
//        	getApplicationUI().displayModalDialog(ApplicationDialogType.ERROR, errorText, "Ok");
//        	orderNbr = getApplicationUI().displayModalDialog(ApplicationDialogType.QUESTION, menuText,mS[0],mS[1],mS[2],mS[3],mS[4]);
//        }
		Recipe recipe = menu.generateRecipe(mS[orderNbr]);

		gripper.movePTP(getApplicationData().getFrame("/Start"));
		// Pick up at RefPart
		gripper.getPart(getApplicationData().getFrame("/CupS"));
		
		Ingredient in1= new Cafe(2, 2);
		Ingredient in2= new Milk(2, 2);
		Ingredient in3= new Orange(2, 2);
		
       // Map<String, Ingredient>ingredients = recipe.getIngredients();
		Map<String, Ingredient> ingredients = new HashMap<String, Ingredient>();
		ingredients.put("Cafe", in1);
		
        for(Map.Entry<String, Ingredient> ingre : ingredients.entrySet()){
        	int currPosition = getPositionOfBottle(positionBottle, ingre.getKey());
        	String nameCurrFrame = "Bottle" + currPosition;
        	gripper.movePTP(getApplicationData().getFrame(nameCurrFrame));
        }
        gripper.putPart(getApplicationData().getFrame("/CupE"));
        gripper.movePTP(getApplicationData().getFrame("/Start"));
		gripper.close();
		
		/*End of test sequence 1 19.04.2018*/
		
		// Measurement at measurement points
		//Frame ref=new Frame(gripper.myfindZ(100));
		//Frame ref = gripper.myfindZ(100);
		//double distance=gripper.getDistance(ref,getApplicationData().getFrame("/MessPos"));
				
		//gripper.getDistance(ref,getApplicationData().getFrame("/MessPos"));
		//getLogger().info("height of object:" + distance);
		//gripper.movePTP(getApplicationData().getFrame("/Start"));
		//gripper.getPart(getApplicationData().getFrame("/MessPos"));
				
		// store at RefPart
		//gripper.putPart(getApplicationData().getFrame("/RefPart"));
		
	}

	/**
	 * Auto-generated method stub. Do not modify the contents of this method.
	 */
	public static void main(String[] args) {
		RobotApplication app = new RobotApplication();
		app.runApplication();
	}
}
