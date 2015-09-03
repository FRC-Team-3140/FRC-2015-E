package robot.subsystems;

import robot.RobotMap;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PID extends PIDSubsystem implements RobotMap {

	Preferences pref;
    private Talon motor;
    private Encoder enc;
    
    /******************************************************************
     * Constructor for the PID Subsystem
     * super() is required to initialize the subsystem with the given
     * values
     * this.variable = variable; allows the entire class to use the
     * variables passed into PID() as arguments
     ******************************************************************/
    public PID(String name, Talon motor, Encoder enc) {
    	super(name, kP, kI, kD);
    	this.motor = motor;
    	this.enc = enc;
    	enc.setDistancePerPulse(kDistancePerPulse);
    	this.getPIDController().setContinuous();
    	this.getPIDController().setAbsoluteTolerance(kAbsTol);
    	pref = Preferences.getInstance();
    	
    }
    
    /******************************************************************
     * @see DriveTrain#reset() 
     * for information
     ******************************************************************/
    public void reInit() {
    	getPIDController().reset();
    	getPIDController().setPID(0.4, 0.0, 0.0);
    	this.enc.reset();
    }
    
    
    public void initDefaultCommand() {}
    
    /******************************************************************
     * returnPIDInput() is a method that is required by PIDSubsystem.
     * Allows monitoring of the PID values in SmartDashboard.
     * enc.pidGet() passes the value of the encoder into the
     * PIDSubystem, allowing for motor feedback.
     ******************************************************************/
    protected double returnPIDInput() {
    	return enc.pidGet();
    }
    
    protected void logEncoder(){
    	SmartDashboard.putNumber(this.getName() + ": PID: ", enc.pidGet());
    	System.out.println(this.getName() + ": PID: " + enc.pidGet());
    }
    
    public boolean finished() {
    	SmartDashboard.putNumber(this.getName() + ": error: ", this.getPIDController().getError());
    	return this.getPIDController().onTarget();
    }
    
    protected void usePIDOutput(double output) {
    	SmartDashboard.putNumber(this.getName() + ": MOTOR: ", output*(.75));
    	this.logEncoder();
    	if(this.getName().equalsIgnoreCase("FrontRight"));
    	motor.pidWrite(output*(.6));
    }
    
}
