package org.usfirst.frc.team670.robot.commands;
import org.usfirst.frc.team670.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeLowBarPosition extends CommandGroup{
	
	public IntakeLowBarPosition() {
		//intake down
		if(Robot.intake.getIntakePosition() == true){
			addParallel(new MoveIntake());}
		
		//shooter down
		if(Robot.shooter.getShooterPosition() == true){
			addParallel(new SwitchShooter());}
		
		 requires(Robot.intake);
		 requires(Robot.shooter);
	}
}