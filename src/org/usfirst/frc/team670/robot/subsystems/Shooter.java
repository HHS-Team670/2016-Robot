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
	private int pusherChecker;
	
	public Shooter(){
		shooterTalon = new Talon(1);
		accelTimer = new Timer();
		pushSole = new Solenoid(1);
		shooterSole = new Solenoid(0);
	}
	
	public double getAccelTimer() {
		return accelTimer.get();
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
	
	public void setShooter(double speed) {
		shooterTalon.set(speed);
	}
	
	public void setShooterPosition(boolean boo){
		shooterSole.set(boo);
	}
	
	public void setPusherOut() {
		pushSole.set(true);
	}
	
	public void setPusherIn() {
		pushSole.set(false);
	}
	
	public void switchPusherPosition(){
		pushSole.set(!pushSole.get());
		Timer.delay(0.5);
		pushSole.set(!pushSole.get());
	}
	
	public boolean getPusherPosition() {
		return pushSole.get();
	}
	
	public void switchShooterPosition(){
		shooterSole.set(!shooterSole.get());
	}
	
	public int getPusherChecker(){
		return pusherChecker;
	}
	
	public void setPusherChecker(int newValue){
		pusherChecker = newValue;
	}
	
	public boolean getShooterPosition() {
		return shooterSole.get();
	}
	
	
    public void initDefaultCommand() {
        setDefaultCommand(new SpinWithJoystick());
    }

}

