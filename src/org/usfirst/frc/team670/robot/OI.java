package org.usfirst.frc.team670.robot;

import org.usfirst.frc.team670.robot.commands.MoveIntake;
import org.usfirst.frc.team670.robot.commands.SpinIntake;
import org.usfirst.frc.team670.robot.commands.Shoot;
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
	
	public OI(){
		intakeRollerButt.whileHeld(new SpinIntake());
		intakePosButt.whenPressed(new MoveIntake());
		hingePlateButt.whenPressed(new SwitchPusher());
		shooterPosButt.whenPressed(new SwitchShooter());
		testShootButt.whenPressed(new Shoot());

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

