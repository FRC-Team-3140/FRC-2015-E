package robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * RobotMap is the mapping of essential constants that can be store in one
 * easily located place, so that these values can be changed in RobotMap
 * without affecting the programming elsewhere.
 */
public interface RobotMap {

	/**
	 * Joystick port(s)
	 */
	public static int kDriveStick = 0;
	
	/**
	 * Joystick buttons
	 */
	//DriveStick's buttons
	public static int kGrab = 1;
	public static int kLeggo = 2;
	public static int kUp1 = 5;
	public static int kUp2 = 6;
	public static int kDown1 = 3;
	public static int kDown2 = 4;
	/*
	 *  END OF EDITABLE CONSTANTS, DENOTED BY (!!!)
	 */
	
	/**
	 * Motors (!!!)
	 */
	public static int kLeftDriveMotorPWM = 1;
	public static int kLeftDriveMotorPDP1 = 14;
	public static int kLeftDriveMotorPDP2 = 15;
	
	public static int kRightDriveMotorPWM = 0;
	public static int kRightDriveMotorPDP1 = 0;
	public static int kRightDriveMotorPDP2 = 1;
	
	public static int kWinchMotorPWM = 9;
	public static int kWinchMotorPDP = 4;
	
	/**
	 * Digital I/O (!!!)
	 */
	public static int kLeftDriveEncoderA = 2;
	public static int kLeftDriveEncoderB = 3;
	
	public static int kRightDriveEncoderA = 1;
	public static int kRightDriveEncoderB = 0;
	
	/**
	 * Pneumatics (!!!)
	 */
	public static int kTopGrabberA = 6;
	public static int kTopGrabberB = 7;
	
	public static int kBottomGrabberA = 2;
	public static int kBottomGrabberB = 3;
	
	public static int kShifterA = 4;
	public static int kShifterB = 5;
	
	public static Value EXT = DoubleSolenoid.Value.kForward;
	public static Value RET = DoubleSolenoid.Value.kReverse;
	
	/**
	 * PID Controller
	 */
	public static double kAbsTol = 0.1;
	public static double kDistancePerPulse = 0.0098;
	public static double kP = 0.4;
	public static double kI = 0;
	public static double kD = 0;
	public static double kOutRangeL = -0.8;
	public static double kOutRangeH = 0.8;
	
	/**
	 * Autonomous-Distances in Feet
	 */
	public static double kLeft180= 6;
	public static double kRight180 = 6;
	
}
