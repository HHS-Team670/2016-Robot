package org.usfirst.frc.team670.robot.commands;
import org.usfirst.frc.team670.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootingPosition extends CommandGroup {
	public ShootingPosition() {
		
		//intake down
		if(Robot.intake.getIntakePosition() == true){
			addSequential(new MoveIntake(), 1);
		}
		//shooter up
		if(Robot.shooter.getShooterPosition() == false ){
			addSequential(new SwitchShooter());
		}
		 requires(Robot.intake);
		 requires(Robot.shooter);
	}
}
