package org.usfirst.frc.team670.robot.commands;
import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeLowBarPosition extends Command{
	
	public IntakeLowBarPosition() {
		 requires(Robot.intake);
		 requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// trying to get intake down, shooter down
		
		//if the intake down, shooter up
		if(Robot.intake.getIntakePosition() == true
				&& Robot.shooter.getShooterPosition() == true){
			Robot.shooter.setShooterPosition(false);
		}
		//if the intake up, shooter down
		if(Robot.intake.getIntakePosition() == false
				&& Robot.shooter.getShooterPosition() == false){
			Robot.intake.setIntakePosition(true);
		}
		//if the intake up, shooter up
		if(Robot.intake.getIntakePosition() == false
				&& Robot.shooter.getShooterPosition() == true){
			Robot.shooter.setShooterPosition(false);
			Robot.intake.setIntakePosition(true);
		}
			
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
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