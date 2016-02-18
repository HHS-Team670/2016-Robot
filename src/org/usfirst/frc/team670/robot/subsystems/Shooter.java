package org.usfirst.frc.team670.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    
	private Talon shooterTalon;
	
	public Shooter(){
		shooterTalon = new Talon(1);
	}

	public void shoot(){
		shooterTalon.set(1);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

