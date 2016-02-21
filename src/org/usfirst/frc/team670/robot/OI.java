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
	
	private Button intakePosButt = new JoystickButton(operatorStick, 5);
	private Button intakeRollerButt = new JoystickButton(operatorStick, 2);
	private Button hingePlateButt = new JoystickButton(operatorStick, 3);
	private Button shootPosButt = new JoystickButton(operatorStick, 4);
	private Button testShootButt = new JoystickButton(operatorStick, 1);
	
	public OI(){
		intakeRollerButt.whileHeld(new SpinIntake());
		intakePosButt.whenPressed(new MoveIntake());
		hingePlateButt.whenPressed(new SwitchPusher());
		shootPosButt.whenPressed(new SwitchShooter());
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

