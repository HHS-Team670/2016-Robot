package org.usfirst.frc.team670.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarShootAuto extends CommandGroup {
    
    public  LowBarShootAuto() {
    	addSequential(new DriveSpeed(3,3));//drive through low bar
    	addSequential(new Turn(3,3));//turn towards goal
    	addSequential(new DriveDistance(3));
    	//addSequential(new autoShoot());//shoot ball
    }
}
