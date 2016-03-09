package org.usfirst.frc.team670.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoShoot extends CommandGroup {
	public AutoShoot() {
		//so the robot can still drive
		addParallel(new DriveWithJoystick(), 10);
		//get ball unstuck if stuck
		addSequential(new RunShooter(0.1),0.5);
		addSequential(new RunShooter(0),0.5);
		//auto shoot
		addParallel(new Shoot());
		addSequential(new SetPusherPosition(true),1);
		addSequential(new SetPusherPosition(false));
		addSequential(new RunShooter(0));
	}
}

