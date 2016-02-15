package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.commands.IntakeCommand;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	
	public static Talon soleTalon;
	
	public Intake() {
		soleTalon = new Talon(3);
		
	}
	
	public void placement (double talonY) {
		soleTalon.set(talonY);
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	setDefaultCommand(new IntakeCommand());
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

