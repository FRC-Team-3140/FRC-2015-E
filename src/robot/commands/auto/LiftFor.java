package robot.commands.auto;

import robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftFor extends Command {

	double speed;
	double time;
	
    public LiftFor(double speed, double time) {
    	requires(Robot.lift);
    	this.speed = speed;
    	this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lift.moveTo(speed, time);
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
