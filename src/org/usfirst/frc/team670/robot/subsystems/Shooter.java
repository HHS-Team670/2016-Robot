package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.commands.DriveWithJoystick;
import org.usfirst.frc.team670.robot.commands.SpinWithJoystick;

import edu.wpi.first.wpilibj.DigitalInput;
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
	private DigitalInput limit;
	
	public Shooter(){
		shooterTalon = new Talon(1);
		accelTimer = new Timer();
		pushSole = new Solenoid(1);
		shooterSole = new Solenoid(0);
		limit = new DigitalInput(0);
	}
	
	public boolean getLimitPosition() {
		return limit.get();
	}
	
	public void shoot(){
		shooterTalon.set(1);
	}
	
	public void setShooter(double speed) {
		shooterTalon.set(speed);
	}
	
	public void setShooterPosition(boolean pos){
		shooterSole.set(pos);
	}
	
	public void setPusherPosition(boolean pos) {
		pushSole.set(pos);
	}
	
	public void pushBall(){
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
	
	public void switchPusherPosition(){
		pushSole.set(!pushSole.get());
	}
	
	public boolean getShooterPosition() {
		return shooterSole.get();
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new SpinWithJoystick());
    }

}

