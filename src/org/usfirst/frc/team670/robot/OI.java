package org.usfirst.frc.team670.robot;

import org.usfirst.frc.team670.robot.commands.AutoIntake;
import org.usfirst.frc.team670.robot.commands.AutoShoot;
import org.usfirst.frc.team670.robot.commands.DrivingPosition;
import org.usfirst.frc.team670.robot.commands.IntakeLowBarPosition;
import org.usfirst.frc.team670.robot.commands.MoveIntake;
import org.usfirst.frc.team670.robot.commands.SpinIntake;
import org.usfirst.frc.team670.robot.commands.Shoot;
import org.usfirst.frc.team670.robot.commands.ShootingPosition;
import org.usfirst.frc.team670.robot.commands.SwitchPusher;
import org.usfirst.frc.team670.robot.commands.SwitchShooter;
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
	
	private Button intakePosButt = new JoystickButton(arcButtons, 1);
	private Button intakeRollerButt = new JoystickButton(arcButtons, 2);
	private Button hingePlateButt = new JoystickButton(arcButtons, 3);
	private Button shooterPosButt = new JoystickButton(arcButtons, 4);
	private Button testShootButt = new JoystickButton(arcButtons, 5);
	private Button shootingPosition = new JoystickButton(arcButtons, 6);
	private Button intakeLowBarPosition = new JoystickButton(arcButtons, 7);
	private Button drivingPosition = new JoystickButton(arcButtons, 8);
	private Button autoIntake = new JoystickButton(arcButtons, 9);
	private Button autoShoot = new JoystickButton(arcButtons, 10);
	
	public OI(){
		intakeRollerButt.whileHeld(new SpinIntake());
		intakePosButt.whenPressed(new MoveIntake());
		hingePlateButt.whenPressed(new SwitchPusher());
		shooterPosButt.whenPressed(new SwitchShooter());
		testShootButt.whenPressed(new Shoot());
		shootingPosition.whenPressed(new ShootingPosition());
		intakeLowBarPosition.whenPressed(new IntakeLowBarPosition());
		drivingPosition.whenPressed(new DrivingPosition());
		autoIntake.whenPressed(new AutoIntake());
		autoShoot.whenPressed(new AutoShoot());
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

