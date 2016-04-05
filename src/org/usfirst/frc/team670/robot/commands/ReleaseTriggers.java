package org.usfirst.frc.team670.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ReleaseTriggers extends CommandGroup {
    
    public  ReleaseTriggers() {
    	addSequential(new CancelDrive());//drive through low bar
    	addSequential(new PositionDrive(false));//drive to be aligned with goal
    }
}
