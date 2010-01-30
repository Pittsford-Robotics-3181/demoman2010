package demoman;
import edu.wpi.first.wpilibj.*;

/**
*	Team 3181 Robotics
*		Project:	Breakaway
*		Codename:	Demoman
*						(The Chargin Scottsman)
*		Filename:	Demoman.java
*		Authors:	@eric
*					
*		This file is the main robot controller.  It runs all the loops, and
*		generally makes things happen.
*		
*		--------------------------------------------------------------------
*		--------------------------------------------------------------------
*		Mode reference:
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
*		--------------------------------------------------------------------
*		--------------------------------------------------------------------
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
	
	// Maintain autonomous state
	int autonomousMode = 0;
	
	// Autonomous timer
	static Timer autonomousTimer = new Timer();
	
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
		// Reset speeds, so you don't confuse the ramping
		lastLeftSpeed = 0.0;
		lastRightSpeed = 0.0;
		Hardware.robotDrive.stop();
		
		// Find out which autnomous mode we want
		// Leftmost worth 4
		autonomousMode += Hardware.autonomousSwitches[0].get() ? 4 : 0;
		// Middle worth 2
		autonomousMode += Hardware.autonomousSwitches[1].get() ? 2 : 0;
		// Rightmost worth 1
		autonomousMode += Hardware.autonomousSwitches[2].get() ? 1 : 0;
		
		// Start the autonomous timer, referenced by all autonomous modes
		this.autonomousTimer.start();
	}
	public void teleopInit() {
		System.out.println("Robot has been put into teleoperator mode");
		lastLeftSpeed = 0.0;
		lastRightSpeed = 0.0;
	}
	
	
	/*** Periodic Routines ***/
		// Where stuff happens
	public void disabledPeriodic() {
		Watchdog.getInstance().feed();
	}
	
	public void autonomousePeriodic() {
		Watchdog.getInstance().feed();
		switch (autonomousMode) {
			case 0:
				AutonomousDoNothing.run();
				break;
			case 1:
				AutonomousZone1.run();
				break;
		}
	}
	
	public void teleopPeriodic() {
		Watchdog.getInstance().feed();
		
		// Respond to drivers
		/** !D It seems to me that from our testing, the drivers may not push the joystick
			forward all the way when they really want full speed.  Should we put some code
			in to artifically alter the input speeds?
			@eric **/
		double goLeft = Hardware.leftJoystick.getY();
		double goRight = Hardware.rightJoystick.getY();
		Hardware.robotDrive(goLeft, goRight);
		
		// See if we're supposed to be kicking the ball.
		if (Hardware.rightJoystick.getTrigger()) {
			kickBall();
		}
		
	}

}