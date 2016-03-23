package org.usfirst.frc.team670.robot.commands;
import org.usfirst.frc.team670.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ManualSpinIntake extends Command {
	
    public ManualSpinIntake() {
        requires(Robot.intake);
    		
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.intake.spinIntake();
    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {	
    	Robot.intake.spinIntake();
    }

    // Make this return true when this Command no longer needs to run execute()
    
    protected boolean isFinished() {
        return false;
    }
   

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.stopIntake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intake.stopIntake();
    }
}

