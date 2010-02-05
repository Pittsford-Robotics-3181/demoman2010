package demoman;
import edu.wpi.first.wpilibj.*;

/*
*	Team 3181 Robotics
*		Project:	Breakaway
*		Codename:	Demoman
*						(The Chargin Scottsman)
*		Filename:	DriveSystem.java
*/
/**		
*		This class defines the drive system.  Functions for driving and the like.
*		
*		@author eric
*		@author ben                                                      
*
*/

public class DriveSystem extends RobotDrive {

	// Maintain ramping state:
	double lastLeftSpeed = 0.0;
	double lastRightSpeed = 0.0;
	double RAMPING_CONSTANT_1 = 0.01;
	double RAMPING_CONSTANT_2 = 0.4;
	double MAX_INCREASE = 0.1;

	/**
	*	Constructor for the DriveSystem, does nothing but call the RobotDrive constructor.
	*
	*/
	DriveSystem (int frontLeftMotor, int rearLeftMotor, int frontRightMotor, int rearRightMotor) {
		super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
	}

	// --- EXPONENTIAL RAMPING
	// Exponential ramping
	// Ramp to a given speed
	// Ignore the next line
	// Approximate the equation ds/dt = ln 2 * 2^t
	// @benhazawesome

	public void driveAtSpeed(double leftTarget, double rightTarget)	{

		double leftDelta = leftTarget - lastLeftSpeed;
		if (Math.abs(leftDelta)>MAX_INCREASE) {
			Timer leftTimer = new Timer();
			leftDelta = ((leftDelta < 0) ? -1 : 1) * RAMPING_CONSTANT_1 * Math.pow(2, RAMPING_CONSTANT_2 * leftTimer.get())
		}
		lastLeftSpeed += leftDelta;
		
		double rightDelta = rightTarget - lastRightSpeed;
		if (Math.abs(rightDelta)>MAX_INCREASE) {
			Timer rightTimer = new Timer();
			rightDelta = ((rightDelta < 0) ? -1 : 1) * RAMPING_CONSTANT_1 * Math.pow(2, RAMPING_CONSTANT_2 * rightTimer.get())
		}
		lastRightSpeed += rightDelta;

	}

	// --- END EXPONENTIAL RAMPING ---

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