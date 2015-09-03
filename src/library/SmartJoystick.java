package library;

import edu.wpi.first.wpilibj.Joystick;

public class SmartJoystick extends Joystick {

	/**
	 * Constructor for SmartJoystick.
	 * Sets the port of the superclass to that of the subclass.
	 * @param port of the joystick
	 */
	public SmartJoystick(int port) {
		super(port);
	}

	/**
	 * Gets the X-axis value for any particular joystick.
	 * @return the value-cubed for smoother-driving.
	 */
	public double getSmartX() {
		return Math.pow(super.getX(), 3) * -1;
	}

	/**
	 * Gets the Y-axis value for any particular joystick.
	 * @return the value-cubed for smoother-driving.
	 */
	public double getSmartY() {
		return Math.pow(super.getY(), 3);
	}

	/**
	 * Gets the value of the Hat-switch on any particular joystick.
	 * These values range from -1, 0 to 360 degrees.
	 * Then for our case the corresponding values are converted into a
	 * signal to drive the winch-lifter's motor.
	 * @return 1.0, 0, or -1.0
	 */
	public double getSmartPOV() {
		return super.getPOV();
	}
}
