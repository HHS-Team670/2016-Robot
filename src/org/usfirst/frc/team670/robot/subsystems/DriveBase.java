package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.commands.DriveWithJoystick;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the drivebase containing things like the Talons
 */
public class DriveBase extends Subsystem {
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
}

