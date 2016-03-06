package org.usfirst.frc.team670.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootingPosition extends CommandGroup {
	public ShootingPosition() {
		addParallel(new DriveWithJoystick(), 3);//for driving while changing position
		// trying intake down, shooter up
		addSequential(new SetIntakePosition(false));
		addSequential(new SetShooterPosition(true));
	}	
}
