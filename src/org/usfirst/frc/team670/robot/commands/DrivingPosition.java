package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DrivingPosition extends CommandGroup {
    
    public  DrivingPosition() {
       
    	//if intake is already up, move intake down before moving shooter up
    	if(!Robot.intake.isDown())
    		addSequential(new MoveIntake());
    	
    	if(Robot.shooter.isDown())
    		addSequential(new SwitchShooter());
    	
    	if(Robot.intake.isDown())
    		addSequential(new MoveIntake());
    	
    	requires(Robot.intake);
		requires(Robot.shooter);
    }
}
