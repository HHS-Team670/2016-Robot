package org.usfirst.frc.team670.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarShootAuto extends CommandGroup {
    
    public  LowBarShootAuto() {
    	addSequential(new DriveDistance(3));//drive through low bar
    	addSequential(new DriveDistance(3));//drive to be aligned with goal
    	addSequential(new Pivot(45));//pivot to face goal
    	addSequential(new DriveDistance(3));//drive to shooting distance
    	addSequential(new ShootingSequence());//shoot ball
    }
}
