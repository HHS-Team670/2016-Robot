package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Turn extends Command {

	private double startAngle;
	double turnDegrees;
	
    public Turn(double turnDegrees) {
        requires(Robot.driveBase);
        this.turnDegrees = turnDegrees;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startAngle = Robot.driveBase.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(turnDegrees > 0)
    		Robot.driveBase.turnRight(turnDegrees, startAngle);
    	if(turnDegrees < 0)
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
