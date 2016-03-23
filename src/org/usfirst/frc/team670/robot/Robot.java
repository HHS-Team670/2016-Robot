package org.usfirst.frc.team670.robot;

import org.usfirst.frc.team670.robot.commands.CancelCommand;
import org.usfirst.frc.team670.robot.commands.DriveDistance;
import org.usfirst.frc.team670.robot.commands.LowBarShootAuto;
import org.usfirst.frc.team670.robot.commands.OtherAuto;
import org.usfirst.frc.team670.robot.commands.LowBarAuto;
import org.usfirst.frc.team670.robot.commands.DriveSpeed;
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

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.RGBValue;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

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

	int session;
	Image frame;
	NIVision.RawData colorTable;
	NetworkTable netTable;

	Command autoCommand;
	SendableChooser autoChooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		SmartDashboard.putData(Scheduler.getInstance());

		driveBase = new DriveBase();
		intake = new Intake();
		shooter = new Shooter();
		oi = new OI();

		autoChooser = new SendableChooser();
		autoChooser.addDefault("Low Bar", new LowBarAuto(.75, 1.75));
		autoChooser.addObject("Other Obstacle", new OtherAuto(.75, 2));
		autoChooser.addObject("Do Nothing", new CancelCommand());
		autoChooser.addObject("PID Test", new DriveDistance(3));
		/*
		autoChooser.addObject("Spy Bot", new SpyAuto());
		autoChooser.addObject("Low Bar and Shoot", new LowBarShootAuto());
		autoChooser.addObject("Rough Terrain", new DriveSpeed(3, 3));
		autoChooser.addObject("Ramparts", new DriveSpeed(3, 3));
		autoChooser.addObject("Rock Wall", new DriveSpeed(3, 3));
		autoChooser.addObject("Moat", new DriveSpeed(3, 3));
		autoChooser.addObject("Low Bar", new DriveSpeed(3, 3));
		autoChooser.addObject("No PID", new NoPIDDrive(.75, 6));
		*/
		SmartDashboard.putData("Autonomous Command Chooser", autoChooser);

		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		netTable = NetworkTable.getTable("camera");
		// the camera name (ex "cam0") can be found through the roborio web interface
		session = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		NIVision.IMAQdxConfigureGrab(session);
		colorTable = new NIVision.RawData();
		System.out.println((Command) autoChooser.getSelected());
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
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

		if (autoCommand != null)
			autoCommand.cancel();
		/*
		NIVision.IMAQdxStartAcquisition(session);
		/*
		 * grab an image, draw the circle, and provide it for the camera server
		 * which will in turn send it to the dashboard.
		 

		while (isOperatorControl() && isEnabled()) {

			int width = (int) netTable.getNumber("width", 10);
			int height = (int) netTable.getNumber("height", 10);
			int x = (int) netTable.getNumber("x", 10);
			int y = (int) netTable.getNumber("y", 10);

			System.out.println("Width: " + width + " Height: " + height
					+ " X: " + x + " Y: " + y);
			NIVision.Rect rect = new NIVision.Rect(x, y, width, height);

			NIVision.IMAQdxGrab(session, frame, 1);

			//NIVision.imaqWriteJPEGFile(frame, "/images/vision.jpg", 100,
				//	colorTable);
			NIVision.imaqDrawShapeOnImage(frame, frame, rect,
					DrawMode.PAINT_VALUE, ShapeMode.SHAPE_RECT, 0.0f);

			CameraServer.getInstance().setImage(frame);
			Timer.delay(0.005); // wait for a motor update time
		}
		NIVision.IMAQdxStopAcquisition(session);*/
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		
		Scheduler.getInstance().run();
		CaptureImage();
		//NIVision.imaqWriteJPEGFile(frame, "/images/vision.jpg", 100, colorTable);
		
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void CaptureImage(){
		NIVision.IMAQdxStartAcquisition(session);
		int width = (int) netTable.getNumber("width", 30*2);
		int height = (int) netTable.getNumber("height", 50*2);
		int x = (int) netTable.getNumber("x", 186*2+19);
		int y = (int) netTable.getNumber("y", 40*1.5);

//		System.out.println("Width: " + width + " Height: " + height
			//	+ " X: " + x + " Y: " + y);
		NIVision.Rect rect = new NIVision.Rect(y, x, height, width);
		
		NIVision.IMAQdxGrab(session, frame, 1);
	
		NIVision.imaqDrawShapeOnImage(frame, frame, rect,
		DrawMode.PAINT_INVERT, ShapeMode.SHAPE_RECT, 0.0f);
		CameraServer.getInstance().setImage(frame);
		
		Timer.delay(0.005); // wait for a motor update time	
		//NIVision.imaqWriteJPEGFile(frame, "/images/vision.jpg", 100, colorTable);

	}
	
	public void testPeriodic() {
		LiveWindow.run();
	}
}
