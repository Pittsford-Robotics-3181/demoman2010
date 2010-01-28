package demoman;
import edu.wpi.first.wpilibj.*;

/**
*	Team 3181 Robotics
*		Project:	Breakaway
*		Codename:	Demoman
*						(The Chargin Scottsman)
*		Filename:	Demoman.java
*		Authors:	@eric
*					@ben
*					
*		This file is the main robot controller.  It runs all the loops, and
*		generally makes things happen.
*		
*		Disabled Mode:
*			-> Disabled by the system (e.g., eStop button)
*			-> Does nothing
*
*		Stopped Mode:
*			-> Disabled by our own switch
*			-> Same as disabled, but with a catch:
*				This still checks the stopped switch, so we can reenable easily.
*				(Useful for demonstrations, etc.)
*			-> This is -technically- part of the teleop mode; a check is made in
*				the teleop loop.
*			-> Does nothing
*
*		Autonomous Mode:
*			-> Running autonomously
*			-> Will pick a program based on digital inputs
*			-> Currently does nothing
*
*		Teleop Mode:
*			-> Responds to tank drive: Left joystick controls left throttle, right
*				joystick controls right
*			-> Checks to see if the operator wants to kick
*
*		!! TODO:
*			-> Fix rampTo()
*			-> Fix kickBall()
*			-> Improve the inputs of kickBall() so that the robot can kick autonomously
*				-> Discussion notes in the function
*			-> Rework imports to only pull what we need
*
*/

public class Demoman extends IterativeRobot {

	/*** Instance Variables ***/
	// Maintain ramping state:
	double lastLeftSpeed = 0.0;
	double lastRightSpeed = 0.0;
	double rampingConstant = 0.01;
	
	// Kicking data
	Timer kickerTimer = new Timer();
	boolean kickerTimerIsStale = false; // Used to only reset the timer if it's hit 1.5 seconds
	
	
	/*** Initialization Routines ***/
		// We have to have ALL of these here.
		// These are called when switching modes, so it's useful for resetting things.
	public void robotInit() {
		System.out.println("Robot has been initialized");
	}
	public void disabledInit() {
		System.out.println("Robot has been disabled");
	}
	public void autonomousInit() {
		System.out.println("Robot has been put into autonomous mode");
		lastLeftSpeed = 0.0;
		lastRightSpeed = 0.0;
	}
	public void teleopInit() {
		System.out.println("Robot has been put into teleoperator mode");
		lastLeftSpeed = 0.0;
		lastRightSpeed = 0.0;
	}
	
	
	/*** Periodic Routines ***/
		// Where stuff happens
	public void disabledPeriodic() {
		Watchdog.getInstance.feed();
	}
	
	public void autonomousePeriodic() {
		Watchdog.getInstance().feed();
	}
	
	public void teleopPeriodic() {
		Watchdog.getInstance.feed();
		
		// Respond to drivers
		/** !D It seems to me that from our testing, the drivers may not push the joystick
			forward all the way when they really want full speed.  Should we put some code
			in to artifically alter the input speeds?
			@eric **/
		double goLeft = Hardware.leftJoytick.getY();
		double goRight = Hardware.rightJoystick.getY();
		driveTo(goLeft, goRight);
		
		// See if we're supposed to be kicking the ball.
		kickBall();
		
	}
	
	
	/*** Drive Functions ***/
	
	// Ramp to a given speed
	public void rampTo(double leftTarget, double rightTarget) {
	
		// Left
		double leftDelta = leftTarget - lastLeftSpeed;
		if (Math.abs(leftDelta) > rampingConstant) {
			if (leftDelta < 0) {
				leftDelta = -1 * rampingConstant;
			} else {
				leftDelta = rampingConstant;
			}
		}
		lastLeftSpeed += leftDelta;
		
		// Right
		double rightDelta = rightTarget - lastRightSpeed;
		if (Math.abs(rightDelta) > rampingConstant) {
			if (rightDelta < 0) {
				rightDelta = -1 * rampingConstant;
			} else {
				rightDelta = rampingConstant;
			}
		}
		lastRightSpeed += rightDelta;
	
		Hardware.RobotDrive.setLeftRightMotorSpeeds(lastLeftSpeed, lastRigthSpeed);
	}
	
	// Kick the ball if the button is pressed
		/** !D Instead of using checking to see if the driver wants to kick here,
			why not check the button in teleopPeriodic, and ONLY call this function
			if we're SURE we want to kick.  That way, autonomousPeriodic can implement
			its own kicking conditionals and utilize this function.
			@eric **/
	public void kickBall() {
		/*	Joystick Trigger:	Fire S5
		*	Joystick Button 2:	Fire S3, S4
		*	S1, S2 fire 1.5 seconds after S5
		*/
		
		// Fire S5
		Hardware.solenoids[4].set(Hardware.rightJoystick.getTrigger());
		
		// Check to see if this is the first loop the latch has been open for
		if (!Hardware.kickerLatchSwitch.get() && !kickerTimerIsStale) {
			kickerTimer.reset();
			kickerTimer.start();
			kickerTimerIsStale = true;
		}
		
		// If the latch is closed, shut off S1 and S2, because they've done their job
		if (Hardware.kickerLatchSwitch.get()) {
			Hardware.solenoids[0].set(false);
			Hardware.solenoids[1].set(false);
			// Reset the timer, or else it'll think it needs to retract the arm
			kickerTimer.stop();
			kickerTimer.reset();
		}
		
		// Is button two pressed?
		if (Hardware.rightJoystick.getRawButton(2)) {
			Hardware.solenoids[2].set(true);
			Hardware.solenoids[3].set(true);
		}
		
		if (kickerTimer.get() >= 1500000) {
			Hardware.solenoids[0].set(true);
			Hardware.solenoids[1].set(true);
			Hardware.solenoids[2].set(false);
			Hardware.solenoids[3].set(false);
		}
		
		// DIAGNOSTIC
		System.out.println(kickerLatchSwitch.get());
		
	}

}