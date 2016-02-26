package org.usfirst.frc.team670.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoShoot extends CommandGroup {
	public AutoShoot() {
		//get ball unstuck if stuck
		addSequential(new SlowestShooterSpeed(), 0.5);
		addSequential(new StopShooter(),0.5);
		addSequential(new SlowShooterSpeed());
		//auto shoot
		
		//IDK IF THE PARALLEL COMMAND WILL RUN 
		//FOREVER UNTIL THE LAST SEQUENTIAL COMMAND :(
		addParallel(new Shoot());
		addSequential(new MovePusherOut(),1);
		addSequential(new MovePusherIn());
		addSequential(new StopShooter());
	}
		
	}

