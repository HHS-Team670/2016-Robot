package org.usfirst.frc.team670.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SpyAuto extends CommandGroup {
    
    public  SpyAuto() {
    	addSequential(new DriveDistance(3));//drive to shooting location
    	addSequential(new Pivot(45));//pivot to face goal
    	addSequential(new AutoShoot());//shoot ball
    	addSequential(new DriveDistance(-3));//turn backwards to be aligned with low bar
    	addSequential(new Pivot(-45)); //pivot to align with the  lowbar
    	addSequential(new DriveDistance(-3));//drive backwards under low bar
    	addSequential(new DriveDistance(3));//drive forwards through low bar 
    }
}
