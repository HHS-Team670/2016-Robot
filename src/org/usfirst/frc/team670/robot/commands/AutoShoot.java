package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoShoot extends CommandGroup {
	public AutoShoot() {
		//Robot goes into shooting position
		addSequential(new ShootingPosition(), 1);
		
		if(Robot.shooter.getPusherPosition() == true){
			addSequential(new SwitchPusher());}
		
		addSequential(new Shoot(),5);
		
		addSequential(new SwitchPusher(),4);
		
		addSequential(new SwitchPusher());
		
		if(Robot.shooter.getPusherPosition() == false) {
			Robot.shooter.setShooter(0);}
		
		requires(Robot.shooter);
		}
		
	}

