package robot.commands;

import robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TopRelease extends Command {

    public TopRelease() {
    	requires(Robot.air);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.air.topOpen();
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
