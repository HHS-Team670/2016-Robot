package org.usfirst.frc.team670.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeLowBarPosition extends CommandGroup{
	
	public IntakeLowBarPosition() {
		//robot can still drive while changing positions
		addParallel(new DriveWithJoystick(), 3);		
		// trying to get intake down, shooter down
		addSequential(new SetIntakePosition(true));
		addSequential(new SetShooterPosition(false));
	}


}