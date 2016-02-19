package org.usfirst.frc.team670.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	private Solenoid intakeSole;
	private Talon intakeTalon;

	public Intake() {
		intakeTalon = new Talon(0);	
		intakeSole = new Solenoid(2);
	}
	
	public void spinIntake () {
		intakeTalon.set(0.75);
	}
	
	public void stopIntake(){
		intakeTalon.set(0);
	}

	public void switchPosition() {
		if(intakeSole.get()  == true)
			intakeSole.set(false);
		if(intakeSole.get() == false)
			intakeSole.set(true);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

