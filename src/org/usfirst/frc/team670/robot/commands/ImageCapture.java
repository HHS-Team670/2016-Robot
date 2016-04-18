package org.usfirst.frc.team670.robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.GetImageSizeResult;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.RawData;
import edu.wpi.first.wpilibj.command.Command;

public class ImageCapture extends Command {

	private Image frame;
	private RawData colorTable;
	private GetImageSizeResult ImageQuality;
	private int x, y;
	
    public ImageCapture(Image frame) {
    	this.frame = frame;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	ImageQuality = NIVision.imaqGetImageSize(frame);
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
    	colorTable = new RawData();
    	x = ImageQuality.width;
    	y = ImageQuality.height;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		NIVision.imaqWriteJPEGFile(frame, "/images/ImageOfMadeShot.jpg", 1000, colorTable );
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
