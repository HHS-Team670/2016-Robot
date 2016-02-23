package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutoIntake extends Command {
		public AutoIntake() {
			requires(Robot.shooter);
			requires(Robot.intake);
		}

		@Override
		protected void initialize() {
			// TODO Auto-generated method stub
			//if the pusher is out, then move it back in
			if(Robot.shooter.getPusherPosition() == true){
				Robot.shooter.setPusherIn();
			}
		}

		@Override
		protected void execute() {
			// TODO Auto-generated method stub
			
			//shooter wheels spins slowly backwards
			Robot.shooter.setShooter(-0.25);
			//intake wheels spin to suck in ball
			Robot.intake.spinIntake();
			
			//runs above code for 10 secs
			Timer.delay(5);
			Robot.shooter.setShooter(0);
			Robot.intake.stopIntake();
			
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
