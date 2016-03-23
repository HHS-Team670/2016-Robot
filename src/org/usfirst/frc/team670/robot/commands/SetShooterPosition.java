package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetShooterPosition extends Command{
	private boolean pos;
	public SetShooterPosition(boolean pos) {
		requires(Robot.shooter);
		this.pos = pos;
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		//Robot.shooter.setPusherPosition(false);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Robot.shooter.setShooterPosition(pos);
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
