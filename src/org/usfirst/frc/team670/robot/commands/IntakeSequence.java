package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeSequence extends CommandGroup {
	public IntakeSequence() {	
		addParallel(new DriveWithJoystick());//to drive while intaking
		addParallel(new RunShooter(-0.55));//spin shooter to finish intaking ball -0.37
		addSequential(new SpinIntake(), .1);//spin rollers
		addParallel(new CancelAutoIntake(this));//cancels intake when limit switch is pressed
	}
	//tells command to stop when limit switch press interrupts
	protected void interrupted(){
		super.interrupted();
		Robot.intake.stopIntake();
	}
}