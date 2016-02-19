package org.usfirst.frc.team670.robot;

import org.usfirst.frc.team670.robot.commands.MoveIntake;
import org.usfirst.frc.team670.robot.commands.SpinIntake;
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
	
	private Button spinButt = new JoystickButton(operatorStick, 2);
	private Button intakePosButt = new JoystickButton(operatorStick, 2);
	
	public OI(){
		spinButt.whileHeld(new SpinIntake());
		intakePosButt.whenPressed(new MoveIntake())
;	}
	
	public Joystick getleftStick(){
		return leftDriveStick;
	}
	
	public Joystick getrightStick(){
		return rightDriveStick;
	}
	
	public Joystick getOperatorStick(){
		return operatorStick;
	}
	
}

