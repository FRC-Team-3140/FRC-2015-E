package robot.subsystems;

import library.AnalogGyro;
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
	protected AnalogGyro gyro;
	private final PowerDistributionPanel pdp;
	private double maxV, lT, rT;
	public PID lP, rP;
	private Timer timer;

	private Drivetrain() {
		pdp = new PowerDistributionPanel();
		left = new Talon(kLeftDriveMotorPWM);
		right = new Talon(kRightDriveMotorPWM);
		gyro = new AnalogGyro(1);
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
		reset();
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

		left.set(lT * kThrottle);
		right.set(rT * kThrottle);
		SmartDashboard.putNumber("angle", gyro.getAngle());
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
	
	public void rotateFrame(double speed, double time) {
		double cTime;
		double iTime = System.currentTimeMillis();
		left.set(speed);
		right.set(speed);
		do {
			cTime = System.currentTimeMillis();
		} while (cTime - iTime <= time);
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
	/**
	 * Methods that handle the sensors, such as initialization,
	 * reseting, etc...
	 */
	public void reset( ){
		gyro.reset();
		lEncoder.reset();
		rEncoder.reset();
	}

	@Override
	protected void initDefaultCommand() {
	      setDefaultCommand(new ArcadeDrive());
		
	}
}
