package robot.commands.groups;

import robot.commands.*;
import robot.commands.auto.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SimpleAuto extends CommandGroup {
    
    public  SimpleAuto() {
    	addSequential(new Grab());
    	addSequential(new LiftFor(1.0, 10));
    	addSequential(new DriveForward(0.5, 2));
    	addSequential(new RotateFrame(-90, true));
    	
    }
}
