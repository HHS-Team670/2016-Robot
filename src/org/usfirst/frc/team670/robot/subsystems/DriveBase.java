package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.commands.DriveWithJoystick;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the drivebase containing things like the Talons
 */
public class DriveBase extends Subsystem {
	public static final double gearRatio = 10.71;
	public static final double diameterInInches = 6;
	public static final double circumferenceInInches = diameterInInches * Math.PI;
	public static final double inchesPerTick = circumferenceInInches/360;
    public CANTalon leftTalon1;
    public CANTalon leftTalon2;
    public CANTalon rightTalon1;
    public CANTalon rightTalon2;
    
    
    public DriveBase(){
    	leftTalon1 = new CANTalon(RobotMap.leftMotor1);
    	leftTalon2 = new CANTalon(RobotMap.leftMotor2);
    	rightTalon1 = new CANTalon(RobotMap.rightMotor1);
    	rightTalon2 = new CANTalon(RobotMap.rightMotor2);
    	
    	leftTalon2.changeControlMode(CANTalon.TalonControlMode.Follower);
    	leftTalon2.set(RobotMap.leftMotor1);
    	rightTalon2.changeControlMode(CANTalon.TalonControlMode.Follower);
    	rightTalon2.set(RobotMap.rightMotor1);
    }

    public void initDefaultCommand() {
    	setDefaultCommand(new DriveWithJoystick());
    }
    
    public void drive(double left, double right){
    	leftTalon1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	rightTalon1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	
    	leftTalon1.set(left);
    	rightTalon1.set(right);
    }
    
    public void driveDistanceInches(double inches){
    	double numTicks = inches/inchesPerTick;
		
		leftTalon1.setEncPosition(0);
		leftTalon1.changeControlMode(CANTalon.TalonControlMode.Position);
		leftTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		leftTalon1.reverseSensor(true);
		leftTalon1.setF(0.25);
		leftTalon1.setPID(0.3, 0, 0);
		leftTalon1.setCloseLoopRampRate(0);
		leftTalon1.setIZone(0);
		
		rightTalon1.setEncPosition(0);
		rightTalon1.changeControlMode(CANTalon.TalonControlMode.Position);
		rightTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightTalon1.reverseSensor(true);
		rightTalon1.setF(0.25);
		rightTalon1.setPID(0.3, 0, 0);
		rightTalon1.setCloseLoopRampRate(0);
		rightTalon1.setIZone(0);
		
		leftTalon1.set(1440);
		rightTalon1.set(1440);
    }
    
    public void setSpeed(double speed){
    	leftTalon1.changeControlMode(CANTalon.TalonControlMode.Speed);
    	leftTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	leftTalon1.setF(0.25);
		leftTalon1.setPID(0.3, 0, 0);
		leftTalon1.set(48);
    	
    	rightTalon1.changeControlMode(CANTalon.TalonControlMode.Speed);
    	rightTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	rightTalon1.setF(0.25);
		rightTalon1.setPID(0.3, 0, 0);
		rightTalon1.set(48);
    }
}

