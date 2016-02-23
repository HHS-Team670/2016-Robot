package org.usfirst.frc.team670.robot.commands;
import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShootingPosition extends Command {
	public ShootingPosition() {
		 requires(Robot.intake);
		 requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// trying intake down, shooter up
		
		//intake down, shooter down
		if(Robot.intake.getIntakePosition() == true
				&& Robot.shooter.getShooterPosition() == false){
			Robot.shooter.setShooterPosition(true);
		}
		//intake up, shooter down
		if(Robot.intake.getIntakePosition() == false
				&& Robot.shooter.getShooterPosition() == false){
			Robot.intake.setIntakePosition(true);
			Robot.shooter.setShooterPosition(true);
		}
		//intake up, shooter up
		if(Robot.intake.getIntakePosition() == false
				&& Robot.shooter.getShooterPosition() == true){
			Robot.intake.setIntakePosition(true);
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
