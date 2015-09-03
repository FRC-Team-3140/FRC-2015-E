package robot.subsystems;

import robot.OI;
import robot.RobotMap;
import robot.commands.ArcadeDrive;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem implements RobotMap {

	public static Drivetrain instance;
	protected Talon left, right;
	protected Encoder lEncoder, rEncoder;
	private final PowerDistributionPanel pdp;
	private double maxV, lT, rT;
	public PID lP, rP;
	private Timer timer;

	private Drivetrain() {
		pdp = new PowerDistributionPanel();
		left = new Talon(kLeftDriveMotorPWM);
		right = new Talon(kRightDriveMotorPWM);
		lEncoder = new Encoder(kLeftDriveEncoderA, kLeftDriveEncoderB, false, EncodingType.k1X);
		lEncoder.setDistancePerPulse(kDistancePerPulse);
		lEncoder.reset();
		rEncoder = new Encoder(kRightDriveEncoderA, kRightDriveEncoderB, false, EncodingType.k1X);
		rEncoder.setDistancePerPulse(kDistancePerPulse);
		rEncoder.reset();
		lP = new PID("left", left, rEncoder);
		rP = new PID("Right", right, rEncoder);
		lP.setAbsoluteTolerance(1.0);
		rP.setAbsoluteTolerance(1.0);
	}

	/**
	 * Singleton for Drivetrain constructor. Prevents the constructor from
	 * running more than once. If the constructor runs more than once, then
	 * ports are assigned that already have a spot, which results in an error
	 * upon building.
	 * 
	 * @return instance
	 */
	public static Drivetrain getInstance() {
		if (instance == null) {
			instance = new Drivetrain();
		}
		return instance;
	}

	public void arcadeDrive() {
		lT = OI.getDriveStick().getLeftX() - OI.getDriveStick().getLeftY();
		rT = OI.getDriveStick().getLeftX() + OI.getDriveStick().getLeftY();

		//maxV = Math.max(Math.abs(lT), Math.abs(rT));

		if (maxV > 1) {
			lT = lT / maxV;
			rT = rT / maxV;
		}

		left.set(lT);
		right.set(rT);
		SmartDashboard.putNumber("left distance while driving", lEncoder.getDistance());
		SmartDashboard.putNumber("right distance while driving", rEncoder.getDistance());
	}
	
	public void DriveForward(double s, double distance) {
		reset();
		left.set(s);
		right.set(-1*s);
		do {
			SmartDashboard.putNumber("left distance", lEncoder.getDistance());
			SmartDashboard.putNumber("right distance", rEncoder.getDistance());
		}while (rEncoder.getDistance() <= distance);
		left.set(0);
		right.set(0);
		reset();
	}
	
	public void RotateFrame(double s, double distance, double dir) {
		reset();
		left.set(dir * s);
		right.set(dir * s);
		do {
			SmartDashboard.putNumber("left distance", lEncoder.getDistance());
			SmartDashboard.putNumber("right distance", rEncoder.getDistance());
		}while (rEncoder.getDistance() <= distance);
		left.set(0);
		right.set(0);
	}

	public void logPower() {
		SmartDashboard.putNumber("Left Power #1: ",
				pdp.getCurrent(kLeftDriveMotorPDP1));
		SmartDashboard.putNumber("Left Power #2: ",
				pdp.getCurrent(kLeftDriveMotorPDP2));
		SmartDashboard.putNumber("Right Power #1: ",
				pdp.getCurrent(kRightDriveMotorPDP1));
		SmartDashboard.putNumber("Right Power #2: ",
				pdp.getCurrent(kLeftDriveMotorPDP2));
	}

	/******************************************************************
	 * Used with drive commands When all encoders have reached the set distance,
	 * it returns true Important for the isFinished() method in commands
	 ******************************************************************/
	public boolean itDone() {
		return (rP.finished() && lP.finished());
	}

	/******************************************************************
     * Enables the PID Subsystem again
     * -When enabled, the system moves the wheels to desired setpoint
     * -If always on, the system constantly tries to move to desired
     *  spot, leading to jittering of the wheels
     *  @throws NullPointerException
     ******************************************************************/
	public void reset() {
		lP.reInit();
		rP.reInit();
	}

	public void enable() {
		lP.enable();
		rP.enable();
	}

	public void startTime() {
		System.out.println("timer started");
		timer.start();
		timer.reset();
	}

	public boolean waitForComplete(double milli) {
		System.out.println("time currently: " + timer.get()
				+ " - waiting for: " + milli);
		if (timer.get() <= milli)
			return false;
		else {
			return true;
		}
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ArcadeDrive());
	}

	/******************************************************************
	 * The following methods are used in movement commands.
	 * -setSetpoint(distance) informs the PIDSubystem how far it needs to move
	 * -negated values are to change direction of motor spin
	 ******************************************************************/
	public void drive(double distance) {
		lP.setSetpoint(distance);
		rP.setSetpoint(-1 * distance);
	}

	public void rotate(boolean half, int direction) {
		if (half) {
			lP.setSetpoint(kLeft180 * direction);
			rP.setSetpoint(kRight180 * direction);

		} else {
			lP.setSetpoint((kLeft180 / 2) * direction);
			rP.setSetpoint((kRight180 / 2) * direction);
		}
	}
}
