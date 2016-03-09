package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutoShoot extends Command {
	public AutoShoot() {
		requires(Robot.shooter);
		}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		if(Robot.shooter.getPusherPosition() == true){
			new SwitchPusher();
		}
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
		//wheels automatically accel then stays at max speed, waits 5 secs
		Robot.shooter.shoot();
		Timer.delay(3);
		//pusher comes out, ball (hopefully) shoots, wait 4 secs for ball to successfully shoot
		Robot.shooter.setPusherOut();
		Timer.delay(1);
		//pusher comes back in
		Robot.shooter.setPusherIn();
		//shooter wheels stops
		Robot.shooter.setShooter(0);
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

