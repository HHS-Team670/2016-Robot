package org.usfirst.frc.team670.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootingSequence extends CommandGroup {
	public ShootingSequence() {
		//so the robot can still drive
		addParallel(new DriveWithJoystick(), 3);
		//get ball unstuck if stuck
		//addSequential(new RunShooter(0.1),0.5);
		//addSequential(new RunShooter(0),0.5);
		addSequential(new Shoot(), 3);//spins shooter
		addSequential(new RunPusher());//pushes hinge plate out and in
		addSequential(new RunShooter(0), 0.1);//stops shooter
	}
}

