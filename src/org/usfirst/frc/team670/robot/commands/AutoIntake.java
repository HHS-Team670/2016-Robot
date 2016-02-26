package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoIntake extends CommandGroup {
	public AutoIntake() {
		addParallel(new SpinIntake());
		addParallel(new BackwardsShooter(),3);
		
		
		requires(Robot.intake);
		requires(Robot.shooter);
	}
}