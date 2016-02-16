package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.IntakeCommand;
import org.usfirst.frc.team670.robot.commands.SolenoidMovementCommand;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	private DoubleSolenoid sole;
	private boolean onOff;
	private Talon soleTalon;
	private Value command;

	
	public Intake() {
		soleTalon = new Talon(3);	
		sole = new DoubleSolenoid(1,2);
		onOff = false;	
		//Robot.oi.getOperatorButton().whenPressed(new SolenoidMovementCommand());
	}
	
	public void placeSolenoid (double talonY) {
		soleTalon.set(talonY);
	}
	
	public void solenoidCommand() {
		if(onOff == false){
			this.command = DoubleSolenoid.Value.kForward;
			}
    	else if(onOff == true){
    		this.command = DoubleSolenoid.Value.kReverse;
			onOff = false;
    		}
    	else{
    		command = DoubleSolenoid.Value.kOff;
    		}
	}
	
	public void moveSolenoid() {
		Robot.oi.getOperatorButton().whenPressed(sole.set(this.command));
    	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	setDefaultCommand(new IntakeCommand());
    	setDefaultCommand(new SolenoidMovementCommand());
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

