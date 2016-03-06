
package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ChangeShooterSpeed extends Command{
	private double speed;
	
	public ChangeShooterSpeed (double speed){
		requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Robot.shooter.setShooter(speed);
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
