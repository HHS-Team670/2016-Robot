package org.usfirst.frc.team670.robot.commands;
import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootingPosition extends CommandGroup {
	public ShootingPosition() {
		// trying intake down, shooter up
		addSequential(new SetIntakePosition(true));
		addSequential(new SetShooterPosition(true));
		 requires(Robot.intake);
		 requires(Robot.shooter);
	}	
}
