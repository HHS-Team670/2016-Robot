package org.usfirst.frc.team670.robot;

import org.usfirst.frc.team670.robot.commands.MoveIntake;
import org.usfirst.frc.team670.robot.commands.SpinIntake;


import org.usfirst.frc.team670.robot.commands.FirePusher;
import org.usfirst.frc.team670.robot.commands.SwitchShooter;
import org.usfirst.frc.team670.robot.commands.Shoot;


import org.usfirst.frc.team670.robot.commands.DrivingPosition;
import org.usfirst.frc.team670.robot.commands.LowBarPosition;
import org.usfirst.frc.team670.robot.commands.GrabBall;
import org.usfirst.frc.team670.robot.commands.ShootBall;

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
	
	private Button intakePosButt = new JoystickButton(operatorStick, 5);
	//private Button intakeRollerButt = new JoystickButton(operatorStick, 2);
	
	//private Button hingePlateButt = new JoystickButton(operatorStick, 3);
	private Button shootPosButt = new JoystickButton(operatorStick, 4);
	
	//may want to use arcade buttons
	
	private Button drivePosButt = new JoystickButton(operatorStick, 6);
	private Button lowBarPosButt = new JoystickButton(operatorStick, 3);
	private Button grabBallButt = new JoystickButton(operatorStick, 2);
	private Button testShootButt = new JoystickButton(operatorStick, 1);
	
	public OI(){
		
		intakePosButt.whenPressed(new MoveIntake());
		//intakeRollerButt.whileHeld(new SpinIntake());
		//hingePlateButt.whenPressed(new FirePusher());
		shootPosButt.whenPressed(new SwitchShooter());
		
		grabBallButt.whenPressed(new GrabBall());
		drivePosButt.whenPressed(new DrivingPosition());
		lowBarPosButt.whenPressed(new LowBarPosition());
		testShootButt.whenPressed(new ShootBall());

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

