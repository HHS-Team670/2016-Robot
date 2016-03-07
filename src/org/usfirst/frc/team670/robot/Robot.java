
package org.usfirst.frc.team670.robot;

import org.usfirst.frc.team670.robot.commands.DriveDistance;
import org.usfirst.frc.team670.robot.commands.LowBarShootAuto;
import org.usfirst.frc.team670.robot.commands.MoveSpeed;
import org.usfirst.frc.team670.robot.commands.SpyAuto;
import org.usfirst.frc.team670.robot.subsystems.DriveBase;
import org.usfirst.frc.team670.robot.subsystems.Intake;
import org.usfirst.frc.team670.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static OI oi;
	public static DriveBase driveBase;
	public static Intake intake;
	public static Shooter shooter;

    Command autoCommand;
    SendableChooser autoChooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		driveBase = new DriveBase();

		SmartDashboard.putData(Scheduler.getInstance());
		
	    autoChooser = new SendableChooser();//SENDABLE CHOOSER WRONG?
	    autoChooser.addDefault("Drive Forwards", new DriveDistance(12));
	    autoChooser.addObject("Spy Bot", new SpyAuto());
	    autoChooser.addObject("Low Bar and Shoot", new LowBarShootAuto());
	    autoChooser.addObject("Rough Terrain", new MoveSpeed(3, 3));
	    autoChooser.addObject("Ramparts", new MoveSpeed(3, 3));
	    autoChooser.addObject("Rock Wall", new MoveSpeed(3, 3));
	    autoChooser.addObject("Moat", new MoveSpeed(3, 3));
	    autoChooser.addObject("Low Bar", new MoveSpeed(3, 3));
	    autoChooser.addObject("Spy Bot", new SpyAuto());
	    autoChooser.addObject("Low Bar and Shoot", new LowBarShootAuto());
	    SmartDashboard.putData("Autonomous Command Chooser", autoChooser);
		intake = new Intake();
		shooter = new Shooter();
		oi = new OI();
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
    	autoCommand = (Command) autoChooser.getSelected();
        autoCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	if (autoCommand != null) 
        	autoCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
