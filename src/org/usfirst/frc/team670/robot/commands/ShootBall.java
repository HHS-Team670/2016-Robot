package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootBall extends CommandGroup {
    
    public  ShootBall() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	if(!Robot.intake.isDown())
    		addSequential(new MoveIntake());
    	if(Robot.shooter.isDown())
    		addSequential(new SwitchShooter());
    	
    	addParallel(new Shoot(5));
    	//time for shooter's motor to accelerate before pushing the ball
    	Timer.delay(2);
    	addSequential(new FirePusher());
    	
    	requires(Robot.intake);
		requires(Robot.shooter);
    }
}
