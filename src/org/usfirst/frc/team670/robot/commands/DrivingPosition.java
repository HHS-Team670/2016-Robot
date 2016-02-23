package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DrivingPosition extends Command {

	public DrivingPosition() {
		requires(Robot.intake);
		requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		//trying to get intake up, shooter up
		
		//if the intake is up, and shooter down
		if (Robot.intake.getIntakePosition() == false
				&& Robot.shooter.getShooterPosition() == false) {
			Robot.intake.setIntakePosition(true);
			Robot.shooter.setShooterPosition(true);
			Timer.delay(1);
			Robot.intake.setIntakePosition(false);
		}
		//if the intake is down, and shooter down
		if(Robot.intake.getIntakePosition() == true 
				&& Robot.shooter.getShooterPosition() == false){
			Robot.shooter.setShooterPosition(true);
			Robot.intake.setIntakePosition(false);
		}
		//if the intake is down, and shooter down
		if(Robot.intake.getIntakePosition() == true
				&& Robot.shooter.getShooterPosition() == false){
			Robot.shooter.setShooterPosition(true);
			Robot.intake.setIntakePosition(false);
		}	
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
