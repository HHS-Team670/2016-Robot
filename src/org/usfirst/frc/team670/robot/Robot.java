package org.usfirst.frc.team670.robot;

import java.util.Comparator;
import java.util.Vector;

import org.usfirst.frc.team670.robot.Robot.ParticleReport;
import org.usfirst.frc.team670.robot.Robot.Scores;
import org.usfirst.frc.team670.robot.commands.CancelCommand;
import org.usfirst.frc.team670.robot.commands.DriveDistance;
import org.usfirst.frc.team670.robot.commands.LowBarShootAuto;
import org.usfirst.frc.team670.robot.commands.OtherAuto;
import org.usfirst.frc.team670.robot.commands.LowBarAuto;
import org.usfirst.frc.team670.robot.commands.SpyAuto;
import org.usfirst.frc.team670.robot.subsystems.DriveBase;
import org.usfirst.frc.team670.robot.subsystems.Intake;
import org.usfirst.frc.team670.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;
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

	int width = 30 * 2;
	int height = 50 * 2;
	int x = 186 * 2 + 19;
	int y = 129;
	double RectangleLeftCoordinate;
	double RectangleTopCoordinate;
	double RectangleRightCoordinate;
	double RectangleBottomCoordinate;
	int session;
	Image frame;
	boolean targetDetected;
	double targetDistance, towerDistance;
	double targetVerticalAngle, targetHorizontalAngle;
	double crosshairHorizontalAngle; // angle from target to crosshair
	Image binaryFrame;
	Image testFrame;
	int imaqError;

	// Ideal values
	double Distance;
	double IDEAL_DISTANCE[] = { 4, 4, 4 }; // 86
	double IDEAL_HORIZONTAL_ANGLE[] = { 0, 0, 0 };
	double IDEAL_VERTICAL_ANGLE[] = { 0, 0, 0 };

	// Constants
	NIVision.Range TARGET_SAT_RANGE = new NIVision.Range(0, 16);
	NIVision.Range TARGET_HUE_RANGE = new NIVision.Range(0, 180);
	NIVision.Range TARGET_VAL_RANGE = new NIVision.Range(227, 255);

	double AREA_MINIMUM = 0.1; // Default Area minimum for particle as a
								// percentage of total image area
	double SCORE_MIN = 50.0; // Minimum score to be considered a target
	double VIEW_ANGLE = 60; // View angle for camera, set to 49.4 for Axis m1011
							// by default, 64 for m1013, 51.7 for 206, 52 for
							// HD3000 square, 60 for HD3000 640x480
	NIVision.ParticleFilterCriteria2 criteria[] = new NIVision.ParticleFilterCriteria2[1];
	NIVision.ParticleFilterOptions2 filterOptions = new NIVision.ParticleFilterOptions2(0, 0, 1, 1);
	Scores scores = new Scores();

	// Robot preferences
	Preferences prefs;

	// SendableChooser
	SendableChooser chooser;

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
		autoChooser.addObject("Other Obstacle", new OtherAuto(.78, 2));
		autoChooser.addObject("HIGHER POWER IF DELAY", new OtherAuto(.85, 2));
		autoChooser.addObject("Do Nothing", new CancelCommand());
		autoChooser.addObject("PID Test", new DriveDistance(3));
		/*
		 * autoChooser.addObject("Spy Bot", new SpyAuto());
		 * autoChooser.addObject("Low Bar and Shoot", new LowBarShootAuto());
		 * autoChooser.addObject("Rough Terrain", new DriveSpeed(3, 3));
		 * autoChooser.addObject("Ramparts", new DriveSpeed(3, 3));
		 * autoChooser.addObject("Rock Wall", new DriveSpeed(3, 3));
		 * autoChooser.addObject("Moat", new DriveSpeed(3, 3));
		 * autoChooser.addObject("Low Bar", new DriveSpeed(3, 3));
		 * autoChooser.addObject("No PID", new NoPIDDrive(.75, 6));
		 */
		SmartDashboard.putData("Autonomous Command Chooser", autoChooser);

		frame = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
		binaryFrame = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);
		testFrame = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);
		criteria[0] = new NIVision.ParticleFilterCriteria2(NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA, AREA_MINIMUM, 100.0, 0, 0);

		//The camera name (ex "cam0") can be found through the roborio web interface
		session = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
	
        // the camera name (ex "cam0") can be found through the roborio web interface
         NIVision.IMAQdxConfigureGrab(session);

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


	}

	/**00000000000000000
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		CaptureImage();
	}

	public void CaptureImage(){
		NIVision.IMAQdxStartAcquisition(session);

		NIVision.Rect rect = new NIVision.Rect(y, x, height, width);
		
		NIVision.IMAQdxGrab(session, frame, 1);
	
	    //processImage();
        
       //DrawGoal();
		
		NIVision.imaqDrawShapeOnImage(frame, frame, rect, DrawMode.PAINT_INVERT, ShapeMode.SHAPE_RECT, 0.0f);
		
		CameraServer.getInstance().setImage(frame);
		
		Timer.delay(0.005);
	}
	
	public void testPeriodic() {
		LiveWindow.run();
	}

	public void CheckIfTargeted(){
		String Shot = "No shot valid";
    	int xGoal = (int)RectangleTopCoordinate;
		int yGoal = (int)RectangleLeftCoordinate;
		if(xGoal >= x-5 && xGoal <= x+5){
			if(yGoal >= y-5 && yGoal <= y+5){
				Shot = "TAKE THE SHOT!";
			}
			else{
				Shot = "Move Sideways";
			}
		}
		else{
			Shot = "Move up or down";
		}
		SmartDashboard.putString("Status", Shot);
    }

    
    //Returns the boolean that checks if a target of any resemblance is detected
    public boolean getTargetFound(){
		return targetDetected;
	}
    //Gets the distance
	public double getDistanceToTarget(){
		return targetDistance;
	}

	public double getHorizontalAngle(){
		return targetHorizontalAngle;
	}

	public void DrawGoal(){
		int top = (int)RectangleTopCoordinate;
		int left = (int)RectangleLeftCoordinate;
		int width = (int)(RectangleRightCoordinate - RectangleLeftCoordinate);
		int height = (int)(RectangleBottomCoordinate - RectangleTopCoordinate);
		System.out.println(top + ", " + left + ", " + height + ", " + width);
		NIVision.Rect rect = new NIVision.Rect(top, left, height, width);
		NIVision.imaqDrawShapeOnImage(frame, frame, rect, DrawMode.PAINT_VALUE, ShapeMode.SHAPE_RECT, 100.0f);
	
	}
	
	

	public void processImage(){
		//Threshold the image looking for green (retroreflective target color)
		NIVision.imaqColorThreshold(binaryFrame, frame, 255, NIVision.ColorMode.HSV, TARGET_HUE_RANGE, TARGET_SAT_RANGE, TARGET_VAL_RANGE);
		int numParticles = NIVision.imaqCountParticles(binaryFrame, 1);
		float areaMin = (float)AREA_MINIMUM;
		criteria[0].lower = areaMin;
		imaqError = NIVision.imaqParticleFilter4(binaryFrame, binaryFrame, criteria, filterOptions, null);
		numParticles = NIVision.imaqCountParticles(binaryFrame, 1);
		if(numParticles > 0)
		{
			//Measure particles and sort by particle size
			Vector<ParticleReport> particles = new Vector<ParticleReport>();
			int topWidthIndex = 0;
			for(int particleIndex = 0; particleIndex < numParticles; particleIndex++)
			{
				ParticleReport par = new ParticleReport();
				par.PercentAreaToImageArea = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA);
				par.Area = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_AREA);
				par.BoundingRectTop = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_TOP);
				par.BoundingRectLeft = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_LEFT);
				par.BoundingRectBottom = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_BOTTOM);
				par.BoundingRectRight = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_RIGHT);
				
				if(AreaScore(par) > 50 && (par.BoundingRectRight-par.BoundingRectLeft) > 50) {
					particles.add(par); //if(par.BoundingRectTop>480)
					if((par.BoundingRectRight - par.BoundingRectLeft) > (particles.elementAt(topWidthIndex).BoundingRectRight - particles.elementAt(topWidthIndex).BoundingRectLeft)) {
						topWidthIndex = particles.size()-1;
					}
				}
				
				RectangleTopCoordinate = par.BoundingRectTop;
				RectangleLeftCoordinate = par.BoundingRectLeft;
				RectangleBottomCoordinate = par.BoundingRectBottom;
				RectangleRightCoordinate = par.BoundingRectRight;
			}
				if(particles.size()>0){
				particles.set(0, particles.elementAt(topWidthIndex));
				scores.Aspect = AspectScore(particles.elementAt(0));
				//SmartDashboard.putNumber("Aspect", scores.Aspect);
				scores.Area = AreaScore(particles.elementAt(0));
				//SmartDashboard.putNumber("Area", scores.Area);

				//Set values
				targetDetected = scores.Aspect > SCORE_MIN && scores.Area > SCORE_MIN;
				targetDistance = computeTargetDistance(binaryFrame, particles.elementAt(0));
				targetHorizontalAngle = computeHorizontalAngle(binaryFrame, particles.elementAt(0));
				targetVerticalAngle = computeVerticalAngle(binaryFrame, particles.elementAt(0));
				towerDistance = computeTowerDistance();
	}

			//Send distance and target status to dashboard. The bounding rect, particularly the horizontal center (left - right) may be useful for rotating/driving towards a target
			SmartDashboard.putNumber("Distance to Target", targetDistance);
			SmartDashboard.putNumber("Horizontal Angle to Target", targetHorizontalAngle);
		}
	}
	/**
	//Comparator function for sorting particles. Returns true if particle 1 is larger
	static boolean CompareParticleSizes(ParticleReport particle1, ParticleReport particle2)
	{
		//we want descending sort order
		return particle1.PercentAreaToImageArea > particle2.PercentAreaToImageArea;
	}

	/**
	 * Converts a ratio with ideal value of 1 to a score. The resulting function is piecewise
	 * linear going from (0,0) to (1,100) to (2,0) and is 0 for all inputs outside the range 0-2
	 */
	double ratioToScore(double ratio)
	{
		return (Math.max(0, Math.min(100*(1-Math.abs(1-ratio)), 100)));
	}

	double AreaScore(ParticleReport report)
	{
		double boundingArea = (report.BoundingRectBottom - report.BoundingRectTop) * (report.BoundingRectRight - report.BoundingRectLeft);
		//Tape is 12" by 20" edge so 240" bounding rect. With 2" wide tape it covers 80" of the rect.
		return ratioToScore((240/80)*report.Area/boundingArea);
	}


	double AspectScore(ParticleReport report)
	{
		return ratioToScore((12/20)*(report.BoundingRectRight-report.BoundingRectLeft)/(report.BoundingRectBottom-report.BoundingRectTop));
	}

	private double computeTargetDistance (Image image, ParticleReport report) {
		double normalizedWidth, targetWidth;
		NIVision.GetImageSizeResult size;

		size = NIVision.imaqGetImageSize(image);
		normalizedWidth = 2*(report.BoundingRectRight - report.BoundingRectLeft)/size.width;
		targetWidth = 20;

		//targetWidth*size.width/(2*(report.BoundingRectRight - report.BoundingRectLeft)*tan(viewangle))
		return targetWidth/(normalizedWidth*12*Math.tan(VIEW_ANGLE*Math.PI/(180*2))) * 0.3048;
	}

	private double computeHorizontalAngle (Image image, ParticleReport report) {
		NIVision.GetImageSizeResult size;
		size = NIVision.imaqGetImageSize(image);

		//(int) (((((2 * rec1.tl().x + rec1.width)) / original.width()) - 1) * (_fieldOfViewH.getValue()/2));
		return (report.BoundingRectLeft + (report.BoundingRectRight - report.BoundingRectLeft)/2)/size.width * VIEW_ANGLE - 30;
	}

	private double computeVerticalAngle (Image image, ParticleReport report) {
		NIVision.GetImageSizeResult size;
		size = NIVision.imaqGetImageSize(image);

		//until vertical view angle is determined, using 2/3 horizontal view angle
		//(int) (((((2 * rec1.tl().x + rec1.width)) / original.width()) - 1) * (_fieldOfViewH.getValue()/2));
		return (report.BoundingRectBottom - (report.BoundingRectBottom - report.BoundingRectTop)/2)/size.height * (VIEW_ANGLE * 2/3) - 22.5;
	}

	private double computeTowerDistance () {
		return targetDistance * Math.cos(targetVerticalAngle);
	}
	
	
	public class ParticleReport implements Comparator<ParticleReport>, Comparable<ParticleReport> {
		double PercentAreaToImageArea;
		double Area;
		double BoundingRectLeft;
		double BoundingRectTop;
		double BoundingRectRight;
		double BoundingRectBottom;

		@Override
		public int compareTo(ParticleReport r) {
			return (int) (r.Area - this.Area);
		}

		@Override
		public int compare(ParticleReport r1, ParticleReport r2) {
			return (int) (r1.Area - r2.Area);
		}
	};

	// Structure to represent the scores for the various tests used for target
	// identification
	public class Scores {
		double Area;
		double Aspect;
	}
	
}
