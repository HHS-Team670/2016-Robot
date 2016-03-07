package org.usfirst.frc.team670.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SpyAuto extends CommandGroup {
    
    public  SpyAuto() {
    	addSequential(new DriveDistance(3));//drive to shooting location
    	addSequential(new Pivot(3));//pivot to face goal
    	//addSequential(new autoShoot());//shoot ball
    	addSequential(new Turn(-3,-3));//turn backwards towards low bar
    	addSequential(new DriveDistance(-3));//drive backwards under low bar
    	addSequential(new DriveDistance(3));//drive forwards through low bar 
    }
}
