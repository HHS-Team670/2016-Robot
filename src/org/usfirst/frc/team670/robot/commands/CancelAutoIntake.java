package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CancelAutoIntake extends Command {

	private CommandGroup group;

	public CancelAutoIntake(CommandGroup g) {
		requires(Robot.intake);
		requires(Robot.shooter);
		group = g;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (!Robot.shooter.getLimitPosition()) {
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		group.cancel();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
