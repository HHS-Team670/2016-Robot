package org.usfirst.frc.team670.robot.commands;
import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeLowBarPosition extends CommandGroup{
	
	public IntakeLowBarPosition() {
		// trying to get intake down, shooter down
		 addSequential(new MoveIntakeDown());
		 addSequential(new MoveShooterDown());
		 requires(Robot.intake);
		 requires(Robot.shooter);
	}


}