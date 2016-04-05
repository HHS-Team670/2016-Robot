package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PositionDrive extends Command {

	private boolean triggers;
	
    public PositionDrive(boolean triggers) {
    	requires(Robot.driveBase);
    	this.triggers = triggers;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveBase.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(triggers){
    		Robot.driveBase.posDrive(Robot.oi.getleftStick().getY(), -Robot.oi.getrightStick().getY());//unreverse right stick??
    		System.out.println("calling posdrive method");
    	}
    	System.out.println("posdrive command execute");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(triggers)
    		new PositionDrive(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	/*
    	 if(triggers)
    		new PositionDrive(false);
    	 */
    }
}
