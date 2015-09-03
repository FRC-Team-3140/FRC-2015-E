package robot.commands.auto;

import robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDrive extends Command {

	double distance;
	
    public AutoDrive(double distance) {
    	requires(Robot.dt);
    	this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.dt.reset();
    	Robot.dt.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.dt.drive(distance);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.dt.itDone();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.dt.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.dt.lP.disable();
    	Robot.dt.rP.disable();
    }
}
