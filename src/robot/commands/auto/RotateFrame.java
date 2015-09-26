package robot.commands.auto;

import robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateFrame extends Command {

	double angle;
	double time;
	
    public RotateFrame(double d,double t) {
    	requires(Robot.dt);
    	this.angle = d;
    	this.time = t;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.dt.rotateFrame(angle, time);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
