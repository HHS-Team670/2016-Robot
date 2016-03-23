package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OtherAuto extends Command {

	private double v;
	private double time;
	//private Timer runTime;
	
    public OtherAuto(double v, double time) {
        requires(Robot.driveBase);
        this.v = v;
        this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intake.setIntakePosition(true);
    	Timer.delay(1);
    	Robot.shooter.setShooterPosition(true);
    	Robot.intake.setIntakePosition(false);
    	Timer.delay(4);
    	//Robot.shooter.switchShooterPosition();
    	//runTime.reset();
    	//runTime.start();
    	System.out.println("rock wall");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveBase.drive(-v, v);
    	Timer.delay(time);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        /*if(runTime.get() >= time){
    		return true;
    	}
        return false;*/
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveBase.drive(0, 0);
    	Robot.intake.setIntakePosition(true);
    	//runTime.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//runTime.stop();
    	Robot.driveBase.drive(0, 0);
    }
}
