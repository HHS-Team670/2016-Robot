package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the drivebase containing things like the Talons
 */
public class DriveBase extends Subsystem {
	public static final double diameterInInches = 9.75;
	public static final double circumferenceInInches = diameterInInches
			* Math.PI;
	public static final double inchesPerTick = circumferenceInInches / 360;
	public static final double pivotRadius = 16;

	public CANTalon leftTalon1;
	public CANTalon leftTalon2;
	public CANTalon rightTalon1;
	public CANTalon rightTalon2;

	public DriveBase() {
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

	public void resetRightEncoder() {
		rightTalon1.setEncPosition(0);
	}

	public void resetLeftEncoder() {
		leftTalon1.setEncPosition(0);
	}

	public void posDriveLeft(double left) {// Separate change
															// control
															// mode method??
		leftTalon1.changeControlMode(CANTalon.TalonControlMode.Position);
		leftTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		leftTalon1.reverseSensor(true);

		double p = .8;
		double i = .001;// .0001
		double d = 0;// .8

		leftTalon1.setPID(p, i, d);

		leftTalon1.set(2520 * left * 0.5);
	}

	public void posDriveRight(double right) {// Separate change
										
		rightTalon1.changeControlMode(CANTalon.TalonControlMode.Position);
		rightTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightTalon1.reverseSensor(true);

		double p = .8;
		double i = .001;// .0001
		double d = 0;// .8

		rightTalon1.setPID(p, i, d);
		
		rightTalon1.set(2520 * right * 0.5);
	}

	public void drive(double left, double right) {
		leftTalon1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		rightTalon1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);

		leftTalon1.set(left);
		rightTalon1.set(right);
	}

	public void driveDistanceInches(double inches) {

		double numTicks = ((inches / inchesPerTick) / 360) * 2520;

		leftTalon1.changeControlMode(CANTalon.TalonControlMode.Position);
		System.out.println("Left Talon mode: " + leftTalon1.getControlMode());
		leftTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		leftTalon1.setEncPosition(0);
		leftTalon1.reverseSensor(true);
		// leftTalon1.setAllowableClosedLoopErr(0);

		rightTalon1.changeControlMode(CANTalon.TalonControlMode.Position);
		System.out.println("Right Talon mode: " + rightTalon1.getControlMode());
		rightTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightTalon1.setEncPosition(0);
		rightTalon1.reverseSensor(true);
		// rightTalon1.setAllowableClosedLoopErr(0);

		double p = .8;// .527
		double i = .001;// .04
		double d = 0;// 1

		// leftTalon1.setCloseLoopRampRate(1);
		leftTalon1.setPID(p, i, d);
		// rightTalon1.setCloseLoopRampRate(1);
		rightTalon1.setPID(p, i, d);
		// 2520
		leftTalon1.set(2520);
		rightTalon1.set(2520);
	}

	public void pivot(double degrees) {
		double pivotCircumference = 2 * Math.PI * pivotRadius;
		double pivotArcLength = (degrees / 360) * pivotCircumference;
		double numTicks = ((pivotArcLength / inchesPerTick) / 360) * 2520;

		leftTalon1.changeControlMode(CANTalon.TalonControlMode.Position);
		leftTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		leftTalon1.setEncPosition(0);
		// leftTalon1.reverseSensor(true);
		// leftTalon1.setAllowableClosedLoopErr(0);

		rightTalon1.changeControlMode(CANTalon.TalonControlMode.Position);
		rightTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightTalon1.setEncPosition(0);
		// rightTalon1.reverseSensor(true);
		// rightTalon1.setAllowableClosedLoopErr(0);

		double p = .8;
		double i = .0025;
		double d = .8;

		// leftTalon1.setCloseLoopRampRate(1);
		leftTalon1.setPID(p, i, d);
		// rightTalon1.setCloseLoopRampRate(1);
		rightTalon1.setPID(p, i, d);

		leftTalon1.set(numTicks);
		rightTalon1.set(-numTicks);
	}
}
