package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DrivingPosition extends CommandGroup {
	public DrivingPosition() {
		//trying to get intake up, shooter up
		addSequential(new MoveIntakeDown());
		addSequential(new MoveShooterUp());
		addSequential(new MoveIntakeUp());
		
		requires(Robot.intake);
		requires(Robot.shooter);
	}
}
	
