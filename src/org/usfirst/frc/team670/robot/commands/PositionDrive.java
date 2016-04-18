package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PositionDrive extends Command {

	// private boolean triggers;
	private boolean lastLeft, lastRight;

	public PositionDrive() {
		requires(Robot.driveBase);
		// this.triggers = triggers;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// Robot.driveBase.resetEncoders();
		// Robot.driveBase.noDrive(true);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double leftStickY = Robot.oi.getleftStick().getY();
		double rightStickY = Robot.oi.getrightStick().getY();
		
		if (lastRight == false
				&& Robot.oi.getrightStick().getRawButton(1) == true)
			Robot.driveBase.resetRightEncoder();
		if (lastLeft == false
				&& Robot.oi.getleftStick().getRawButton(1) == true)
			Robot.driveBase.resetLeftEncoder();

		if (Robot.oi.getleftStick().getRawButton(1)
				&& Robot.oi.getrightStick().getRawButton(1)) {
			Robot.driveBase.posDriveLeft(Robot.oi.getleftStick().getY());
			Robot.driveBase.posDriveRight(-Robot.oi.getrightStick().getY());
		}

		else if (Robot.oi.getleftStick().getRawButton(1)) {
			Robot.driveBase.posDriveLeft(Robot.oi.getleftStick().getY());
		}

		else if (Robot.oi.getrightStick().getRawButton(1)) {
			Robot.driveBase.posDriveRight(-Robot.oi.getrightStick().getY());
		}

		lastRight = Robot.oi.getrightStick().getRawButton(1);
		lastLeft = Robot.oi.getleftStick().getRawButton(1);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		/*
		 * if (lastRight != Robot.oi.getrightStick().getRawButton(1))
		 * Robot.driveBase.resetRightEncoder(); if (lastLeft !=
		 * Robot.oi.getleftStick().getRawButton(1))
		 * Robot.driveBase.resetLeftEncoder();
		 * 
		 * lastRight = Robot.oi.getrightStick().getRawButton(1); lastLeft =
		 * Robot.oi.getleftStick().getRawButton(1);
		 */

		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		/*
		 * if(triggers) new PositionDrive(false);
		 */
	}
}
