package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Turn extends Command {

	private double startAngle;
	private double turnDegrees;
	private boolean direction;
	
	
    public Turn(double turnDegrees) {
        requires(Robot.driveBase);
        this.turnDegrees = turnDegrees;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startAngle = Robot.driveBase.getAngle();
    	if(turnDegrees > 0)
    		direction = true;
    	else if(turnDegrees < 0)
    		direction = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(direction)
    		Robot.driveBase.turnRight(turnDegrees, startAngle);
    	else if(!direction)
    		Robot.driveBase.turnLeft(turnDegrees, startAngle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
