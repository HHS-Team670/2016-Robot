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
		pushSole = new Solenoid(1);
		shooterSole = new Solenoid(0);
		
	}

	public void spin(double operator){
		shooterTalon.set(operator);
	}
	
	
	
	public void shoot(double time){
		
		accelTimer.start();
		
		while(accelTimer.get() < time){
			if(accelTimer.get() <= .5)
				shooterTalon.set(0.25);
			else if(accelTimer.get() <= 1)
				shooterTalon.set(0.5);
			else if(accelTimer.get() <= 1.5)
				shooterTalon.set(0.75);
			else
				shooterTalon.set(1);
		}
		shooterTalon.set(0);
	}
	
	public void switchPusherPosition(){
		pushSole.set(!pushSole.get());
	}
	
	public void switchShooterPosition(){
		
		shooterSole.set(!shooterSole.get());
	}
	
	public boolean isDown(){
		return !shooterSole.get();
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new SpinWithJoystick());
    }
}

