package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class DrivingPosition extends CommandGroup {

	public DrivingPosition() {
		// if intake is already up, move intake down before moving shooter up
		if (Robot.intake.getIntakePosition() == false
				&& Robot.shooter.getShooterPosition() == false) {
			addSequential(new MoveIntake());
			// shooter up
			addSequential(new SwitchShooter(), 1);

			// intake up

			addSequential(new MoveIntake());
		}

		requires(Robot.intake);
		requires(Robot.shooter);
	}

}
