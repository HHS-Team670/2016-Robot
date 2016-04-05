package org.usfirst.frc.team670.robot;

import org.usfirst.frc.team670.robot.commands.IntakeSequence;
import org.usfirst.frc.team670.robot.commands.ShootingSequence;
import org.usfirst.frc.team670.robot.commands.CancelCommand;
import org.usfirst.frc.team670.robot.commands.CancelDrive;
import org.usfirst.frc.team670.robot.commands.DrivingPosition;
import org.usfirst.frc.team670.robot.commands.IntakeLowBarPosition;
import org.usfirst.frc.team670.robot.commands.ManualSpinIntake;
import org.usfirst.frc.team670.robot.commands.MoveIntake;
import org.usfirst.frc.team670.robot.commands.PositionDrive;
import org.usfirst.frc.team670.robot.commands.SpinIntake;
import org.usfirst.frc.team670.robot.commands.Shoot;
import org.usfirst.frc.team670.robot.commands.ShootingPosition;
import org.usfirst.frc.team670.robot.commands.PushBall;
import org.usfirst.frc.team670.robot.commands.ReleaseTriggers;
import org.usfirst.frc.team670.robot.commands.SwitchPusher;
import org.usfirst.frc.team670.robot.commands.SwitchShooter;
import org.usfirst.frc.team670.robot.commands.SwitchTriggers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private Joystick leftDriveStick = new Joystick(RobotMap.leftDriveStick);
	private Joystick rightDriveStick = new Joystick(RobotMap.rightDriveStick);
	private Joystick operatorStick = new Joystick(RobotMap.operatorStick);
	private Joystick arcButtons = new Joystick(RobotMap.arcButtons);
	
	private Button drivingPosition = new JoystickButton(arcButtons, 1);
	private Button drivingPosition2 = new JoystickButton(arcButtons, 2);
	private Button intakePosButt = new JoystickButton(arcButtons, 3);
	private Button intakeRollerButt = new JoystickButton(arcButtons, 4);
	private Button shooterPosButt = new JoystickButton(arcButtons, 5);
	private Button hingePlateButt = new JoystickButton(arcButtons, 6);
	private Button intakeLowBarPosition = new JoystickButton(arcButtons, 7);
	private Button autoIntake = new JoystickButton(arcButtons, 8);
	private Button shootingPosition = new JoystickButton(arcButtons, 9);
	private Button autoShoot = new JoystickButton(arcButtons, 10);
	
	private Button cancel = new JoystickButton(operatorStick, 1);
	
	private Button startPosDriveModeButt = new JoystickButton(rightDriveStick, 3);
	private Button endPosDriveModeButt = new JoystickButton(rightDriveStick, 2);
	private Button startPosDriveButt = new JoystickButton(rightDriveStick, 1);
	
	public OI(){
		
		startPosDriveModeButt.whenPressed(new PositionDrive(false));
  		endPosDriveModeButt.whenPressed(new CancelDrive());
		startPosDriveButt.whenPressed(new PositionDrive(true));//whileHeld??
		startPosDriveButt.whenReleased(new ReleaseTriggers());
		
		drivingPosition.whenPressed(new DrivingPosition());
		drivingPosition2.whenPressed(new DrivingPosition());
		intakePosButt.whenPressed(new MoveIntake());
		intakeRollerButt.whileHeld(new ManualSpinIntake());
		shooterPosButt.whenPressed(new SwitchShooter());
		hingePlateButt.whenPressed(new SwitchPusher());
		intakeLowBarPosition.whenPressed(new IntakeLowBarPosition());
		autoIntake.whenPressed(new IntakeSequence());
		shootingPosition.whenPressed(new ShootingPosition());
		autoShoot.whenPressed(new ShootingSequence());
		
		cancel.whenPressed(new CancelCommand());
		}
	
	public Joystick getleftStick(){
		return leftDriveStick;
	}
	
	public Joystick getrightStick(){
		return rightDriveStick;
	}
	
	public Joystick getOperatorStick(){
		return operatorStick;
	}
	
	public Joystick getArcButtons(){
		return arcButtons;
	}
}

