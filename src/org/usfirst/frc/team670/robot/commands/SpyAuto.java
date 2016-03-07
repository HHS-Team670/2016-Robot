package org.usfirst.frc.team670.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SpyAuto extends CommandGroup {
    
    public  SpyAuto() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new DriveDistance(3));//drive to shooting location
    	addSequential(new Pivot(3));//pivot to face goal
    	//addSequential(new autoShoot());//shoot ball
    	addSequential(new Turn(-3,-3));//turn backwards towards low bar
    	addSequential(new DriveDistance(-3));//drive backwards under low bar
    	addSequential(new DriveDistance(3));//drive forwards through low bar 
    }
}
