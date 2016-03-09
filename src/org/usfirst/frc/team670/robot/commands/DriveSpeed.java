package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveSpeed extends Command {
	
	private double speed;
	private double time; //in seconds
	private Timer runTime;

    public DriveSpeed(double speed, double time) {
    	requires(Robot.driveBase);
    	this.speed = speed;
    	this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	runTime.reset();
    	runTime.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveBase.setSpeed(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(runTime.get() >= time){
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	runTime.stop();
    	Robot.driveBase.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	runTime.stop();
    	Robot.driveBase.drive(0, 0);
    }
}
