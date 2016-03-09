package org.usfirst.frc.team670.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoIntake extends CommandGroup {
	public AutoIntake() {	
		addParallel(new DriveWithJoystick(), 4);//to drive while intaking
		addParallel(new SpinIntake());//spin rollers
		addParallel(new RunShooter(-0.37),3);//spin shooter to finish intaking ball
	}
}