package robot.subsystems;

import robot.OI;
import robot.RobotMap;
import robot.commands.Lifter;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class WinchLifter extends Subsystem implements RobotMap {
    
	public static WinchLifter instance;
	protected Talon lift;
	private final PowerDistributionPanel pdp;
	private double lT;
	private Timer timer;
	
	private WinchLifter() {
		pdp = new PowerDistributionPanel();
		lift = new Talon(kWinchMotorPWM);
	}
	
	public static WinchLifter getInstance() {
		if (instance == null) {
			instance = new WinchLifter();
		}
		return instance;
	}
	
	public void winchLifter(double speed) {
		lift.set(speed);
	}
	
	public void moveTo(double speed, double time) {
		double cTime;
		double iTime = System.currentTimeMillis();
		this.winchLifter(speed);
		do {
			cTime = System.currentTimeMillis();
		} while (cTime - iTime <= time);
	}

	
	public void startTime(){
    	System.out.println("timer started");
    	timer.start();
    	timer.reset();
    }
    
    public boolean waitForComplete(double milli){
    	System.out.println("time currently: " + timer.get() + " - waiting for: " + milli);
    	if(timer.get() <= milli)
    		return false;
    	else{
    		return true;
    	}
    }

    public void initDefaultCommand() {
    	setDefaultCommand(null);
    }
}

