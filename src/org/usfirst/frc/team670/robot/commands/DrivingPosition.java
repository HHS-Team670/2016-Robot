package org.usfirst.frc.team670.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DrivingPosition extends CommandGroup {
	public DrivingPosition() {
		//robot can still drive while changing positions
		addParallel(new DriveWithJoystick(), 3);
		//make sure pusher is false
		//addSequential(new SetPusherPosition(false));
		//trying to get intake up, shooter up
		addSequential(new SetIntakePosition(true));
		addSequential(new SetShooterPosition(true));
		addSequential(new SetIntakePosition(false));
	}
}
	
