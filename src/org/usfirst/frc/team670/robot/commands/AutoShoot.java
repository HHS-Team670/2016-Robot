package org.usfirst.frc.team670.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoShoot extends CommandGroup {
	public AutoShoot() {
		//so the robot can still drive
		addParallel(new DriveWithJoystick());
		
		//get ball unstuck if stuck
		addSequential(new ChangeShooterSpeed(0.1),0.5);
		addSequential(new ChangeShooterSpeed(0),0.5);
		//auto shoot
		
		//IDK IF THE PARALLEL COMMAND WILL RUN 
		//FOREVER UNTIL THE LAST SEQUENTIAL COMMAND :(
		addParallel(new Shoot(),3);
		addSequential(new SetPusherPosition(true),1);
		addSequential(new SetPusherPosition(false));
		addSequential(new ChangeShooterSpeed(0));
	}
		
	}

