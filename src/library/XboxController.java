package library;

import library.HatSwitch.POVtrigger;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class XboxController extends Joystick{

	public Button a;
	public Button b;
	public Button x;
	public Button y;
	public Button leftBumper;
	public Button rightBumper;
	public Button select;
	public Button start;
	public Button leftJoystickButton;
	public Button rightJoystickButton;
	public Button leftTrigger;
	public Button rightTrigger;
	//public Timer rumbleTimer; TODO
	public HatSwitch dPad;
	
	public POVtrigger dUp;
	public POVtrigger dUpRight;
	public POVtrigger dRight;
	public POVtrigger dDownRight;
	public POVtrigger dDown;
	public POVtrigger dDownLeft;
	public POVtrigger dLeft;
	public POVtrigger dUpLeft;
	
	/**
	 * Constructor for the XboxController.
	 * @param the port of the controller.
	 */
	public XboxController(int port) {
		super(port);
		a = new JoystickButton(this, 1);
		b = new JoystickButton(this, 2);
		x = new JoystickButton(this, 3);
		y = new JoystickButton(this, 4);
		leftBumper = new JoystickButton(this, 5);
		rightBumper = new JoystickButton(this, 6);
		select = new JoystickButton(this, 7);
		start = new JoystickButton(this, 8);
		leftJoystickButton = new JoystickButton(this, 9);
		rightJoystickButton = new JoystickButton(this, 10);
		leftTrigger = new JoystickAnalogButton(this, 2, 0.5);
		rightTrigger = new JoystickAnalogButton(this, 3, 0.5);
		
		dPad = new HatSwitch(this);
		dUp = dPad.UP;
		dUpRight = dPad.UP_RIGHT;
		dRight = dPad.RIGHT;
		dDownRight = dPad.DOWN_RIGHT;
		dDown = dPad.DOWN;
		dDownLeft = dPad.DOWN_LEFT;
		dLeft = dPad.LEFT;
		dUpLeft = dPad.UP_LEFT;
	}
	
	/**
	 * Gets the X-axis of the left-thumbstick, and smoothens it.
	 * @return The smoothen value of the left-thumbstick's X-axis.
	 */
	public double getLeftX() {
		return Math.pow(super.getRawAxis(0), 3) * -1;
	}
	
	/**
	 * Gets the Y-axis of the left-thumbstick, and smoothens it.
	 * @return The smoothen value of the left-thumbstick's x-axis.
	 */
	public double getLeftY() {
		return Math.pow(super.getRawAxis(1), 3);
	}
	
	/**
	 * Gets the X-axis of the right thumbstick, and smoothens it.
	 * @return The smoothen value of the right-thumbstick's X-axis
	 */
	public double getRightX() {
		return Math.pow(super.getRawAxis(3), 3) * -1;
	}
	
	/**
	 * Gets the Y-axis of the right thumbstick, and smoothens it.
	 * @return The smoothen value of the right-thumbstick's Y-axis
	 */
	public double getRightY() {
		return Math.pow(super.getRawAxis(4), 3);
	}
	

}
