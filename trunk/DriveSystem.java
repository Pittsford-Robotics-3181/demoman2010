package demoman;
import edu.wpi.first.wpilibj.*;

/**
*	Team 3181 Robotics
*		Project:	Breakaway
*		Codename:	Demoman
*						(The Chargin Scottsman)
*		Filename:	DriveSystem.java
*		Authors:	@eric
*					
*		This file defines the drive system.  It includes functions for driving.
*
*		--------------------------------------------------------------------
*		--------------------------------------------------------------------
*		Function reference:
*
*		void driveAtSpeed(double leftTarget, double rightTarget)
*			-> Ramp the motors to those respective values
*
*		void stop()
*			-> Stop all motors
*			-> !! Update this when you add motors
*
*              void resetDefaults()
*                      -> !!
*
*		--------------------------------------------------------------------
*		--------------------------------------------------------------------
*
*/

public class DriveSystem extends RobotDrive {

	// Maintain ramping state:
	double lastLeftSpeed = 0.0;
	double lastRightSpeed = 0.0;
	double RAMPING_CONSTANT = 0.01;
	
	DriveSystem (int frontLeftMotor, int rearLeftMotor, int frontRightMotor, int rearRightMotor) {
		super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
	}
	
/*
//  --- LINEAR RAMPING ---

	
	// Ramp to a given speed
	public void driveAtSpeed(double leftTarget, double rightTarget) {
	
		// Left
		double leftDelta = leftTarget - lastLeftSpeed;
		if (Math.abs(leftDelta) > RAMPING_CONSTANT) {
			leftDelta = ((leftDelta < 0) ? -1 : 1) * RAMPING_CONSTANT;
		}
		lastLeftSpeed += leftDelta;
		
		// Right
		double rightDelta = rightTarget - lastRightSpeed;
		if (Math.abs(rightDelta) > RAMPING_CONSTANT) {
			rightDelta = ((rightDelta < 0) ? -1 : 1) * RAMPING_CONSTANT;
		}
		lastRightSpeed += rightDelta;
	
		setLeftRightMotorSpeeds(lastLeftSpeed, lastRightSpeed);
	}
//	--- END LINEAR RAMPING ---
*/
	
	// --- EXPONENTIAL RAMPING
	// Exponential ramping
	// Approximate the equation ds/dt = ln 2 * 2^t
	// @ben
	double RAMPING_CONSTANT = 0.6931;
	
	
	
	// Stop EVERYTHING.
	public void stop() {
		setLeftRightMotorSpeeds(0.0, 0.0);
                resetDefaults();
	}

        public void resetDefaults() {
          // It might get confused when we restart
          lastLeftSpeed = 0.0;
          lastRightSpeed = 0.0;
        }

}