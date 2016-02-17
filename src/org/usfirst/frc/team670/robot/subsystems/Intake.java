package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.IntakeCommand;
import org.usfirst.frc.team670.robot.commands.SolenoidMovementCommand;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	private DoubleSolenoid intakeSole;
	private int positionIdentifier;
	private Talon intakeTalon;
	private Value solePosition;

	public Intake() {
		intakeTalon = new Talon(0);	
		intakeSole = new DoubleSolenoid(0, 1);
		positionSwitch = 0;	
		//Robot.oi.getOperatorButton().whenPressed(new SolenoidMovementCommand());
	}
	
	public void spinIntake (double talonY) {
		intakeTalon.set(talonY);
	}

	public void switchPosition() {
		if(positionIdentifier == 1){
			solePosition = DoubleSolenoid.Value.kForward;
			}
    	else if(positionIdentifier == 2){
    		solePosition = DoubleSolenoid.Value.kReverse;
    		}
    	else if(positionIdentifier == 3){
    		solePosition = DoubleSolenoid.Value.kOff;
    		}
	}
	
	public void switchIdentifier(){
		
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

