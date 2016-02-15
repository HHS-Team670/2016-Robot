package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.OI;
import org.usfirst.frc.team670.robot.commands.SolenoidMovementCommand;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SolenoidMovement extends Subsystem  {
	public Command soleCommand;
	public static DoubleSolenoid sole;

	public SolenoidMovement() {
		sole = new DoubleSolenoid(1,2);
		SolenoidMovementCommand.onOff = false;
		OI.operatorButton.whenPressed(new SolenoidMovementCommand());
    	
	}
	
	
	public void initDefaultCommand() {
    	setDefaultCommand(new SolenoidMovementCommand());
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
