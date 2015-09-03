 package robot;

import robot.commands.BottomGrab;
import robot.commands.BottomRelease;
import robot.commands.TopGrab;
import robot.commands.Lifter;
import robot.commands.TopRelease;
import robot.commands.auto.AutoLifter;
import robot.commands.groups.GrabAndLift;
import library.SmartJoystick;
import library.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI implements RobotMap {
	
	private static XboxController xbox;
	private boolean topClosed = false;
	private boolean bottomClosed = false;
	/**
	 * Assigns joysticks to a port
	 * Calls the tie() method
	 */
	public OI() {
		xbox = new XboxController(kDriveStick);
		check();
	}
	
	
	/**
	 * Listens to the joystick to determine if any buttons were pressed.
	 * If a button is pressed, call the respective command
	 * 
	 */
	private void check() {
		xbox.y.whenPressed(new Lifter(-0.8));
		xbox.y.whenReleased(new Lifter(0.0));
		xbox.a.whenPressed(new Lifter(0.8));
		xbox.a.whenPressed(new Lifter(0.0));
		
		if(xbox.leftTrigger.get() && topClosed == false) {
			new TopGrab();
		}else if (xbox.leftTrigger.get() && topClosed == true){
			new TopRelease();
		}else if (xbox.rightTrigger.get() && bottomClosed == false) {
			new BottomGrab();
		}else if (xbox.rightTrigger.get() && bottomClosed == true) {
			new BottomRelease();
		}
		/*grab.whenPressed(new TopGrab());
		leggo.whenPressed(new TopRelease());
		up1.whenPressed(new Lifter(-0.8));
		up2.whenPressed(new Lifter(-0.8));
		down1.whenPressed(new Lifter(0.8));
		down2.whenPressed(new Lifter(0.8));
		up1.whenReleased(new Lifter(0.8));
		up2.whenReleased(new Lifter(0.8));
		down1.whenReleased(new Lifter(0.8));
		down2.whenReleased(new Lifter(0.8));
		auto1.whenPressed(new AutoLifter(-0.5, 1000));
		gl.whenPressed(new GrabAndLift()); */
	}
	
	public static XboxController getDriveStick() {
		return xbox;
	}
}


