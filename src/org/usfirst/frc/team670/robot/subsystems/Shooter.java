package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.commands.SpinWithJoystick;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class Shooter extends Subsystem {
    
	private Talon shooterTalon;
	private Timer accelTimer;
	private Solenoid pushSole;
	private Solenoid shooterSole;
	
	public Shooter(){
		shooterTalon = new Talon(1);
		accelTimer = new Timer();
		pushSole = new Solenoid(2);
	}

	public void spin(double operator){
		shooterTalon.set(operator);
	}
	
	public void shoot(){
		accelTimer.start();
		
		if(accelTimer.get() <= .5)
			shooterTalon.set(0.25);
		if(accelTimer.get() <= 1)
			shooterTalon.set(0.5);
		if(accelTimer.get() <= 1.5)
			shooterTalon.set(0.75);
		shooterTalon.set(1);		
	}
	
	public void switchPusherPosition(){
		if(pushSole.get() == true)
			pushSole.set(false);
		if(pushSole.get() == false)
			pushSole.set(true);
	}
	
	public void switchShooterPosition(){
		if(shooterSole.get() == true)
			shooterSole.set(false);
		if(shooterSole.get() == false)
			shooterSole.set(true);
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new SpinWithJoystick());
    }
}

