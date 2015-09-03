package robot.commands;

import robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Lifter extends Command {

	double speed;
	
    public Lifter(double d) {
    	requires(Robot.lift);
    	this.speed = d;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lift.winchLifter(speed);
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
