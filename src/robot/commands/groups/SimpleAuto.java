package robot.commands.groups;

import java.util.concurrent.TimeUnit;

import robot.commands.Grab;
import robot.commands.auto.DriveForward;
import robot.commands.auto.LiftFor;
import robot.commands.auto.RotateFrame;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SimpleAuto extends CommandGroup {
    
    public  SimpleAuto() throws InterruptedException {
    	addSequential(new Grab());
    	//TimeUnit.SECONDS.sleep(10);
    	addSequential(new LiftFor(-0.8, 1000));
    	addSequential(new DriveForward(0.5, 30));
    	addSequential(new LiftFor(0.8, 100));
    	//Positive = turns left, negative = turns right (EX: 0.5 = turns left at 0.5)
    	addSequential(new RotateFrame(0.5, 1000));
    	addSequential(new DriveForward(0.5, 72));
    	
    }
}
