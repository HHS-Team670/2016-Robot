package org.usfirst.frc.team670.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	private Solenoid intakeSole;
	private Talon intakeTalon;

	public Intake() {
		intakeTalon = new Talon(0);
		intakeSole = new Solenoid(2);
	}

	public void setIntakePosition(boolean pos) {
		intakeSole.set(pos);
	}

	public boolean getIntakePosition() {
		return intakeSole.get();
	}

	public double getIntakeSpeed() {
		return intakeTalon.get();
	}

	public void spinIntake() {
		intakeTalon.set(0.75);
	}
	
	public void spinIntakeReverse() {
		intakeTalon.set(-0.75);
	}

	public void stopIntake() {
		intakeTalon.set(0);
	}

	public void switchPosition() {
		intakeSole.set(!intakeSole.get());
	}

	public void initDefaultCommand() {
	}
}
