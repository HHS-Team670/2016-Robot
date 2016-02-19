package org.usfirst.frc.team670.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	private DoubleSolenoid intakeSole;
	private Talon intakeTalon;

	public Intake() {
		intakeTalon = new Talon(0);	
		intakeSole = new DoubleSolenoid(0, 1);
	}
	
	public void spinIntake () {
		intakeTalon.set(0.75);
	}
	
	public void stopIntake(){
		intakeTalon.set(0);
	}

	public void switchPosition() {
	
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

