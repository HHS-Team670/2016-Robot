package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoIntake extends CommandGroup {
		public AutoIntake() {
			//Robot into intake position
			addSequential(new IntakeLowBarPosition(), 1);
			
			//pusher back
			
			//spins intake for 2 seconds
			addSequential(new SpinIntake(), 10);
			
			if(Robot.intake.getIntakeSpeed() > 0){
				Robot.shooter.setShooter(-0.25);
			}
			
			requires(Robot.shooter);
			requires(Robot.intake);
		}
}
