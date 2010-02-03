package demoman;
import edu.wpi.first.wpilibj.*;

/*
*	Team 3181 Robotics
*		Project:	Breakaway
*		Codename:	Demoman
*						(The Chargin Scottsman)
*		Filename:	Demoman.java
*/
/**		
*		This class represents the robot, nicknamed Demoman by a very TF2 obsessed
*		programmer.  Most methods are simple enough; just remeber that "teleop"
*		means "operator controlled".
*		
*		@author eric
*		@author ben                                                      
*
*/

public class Demoman extends IterativeRobot {

	/*--- Instance Variables ---*/
	
	// Maintain autonomous state
	int autonomousMode = 0;
	
	// Autonomous timer
	static Timer autonomousTimer = new Timer();
	
	
	/*--- Initialization Routines ---*/
	/**
	*	Procedures for when the robot has just been booted, but has NOT been put into any mode.
	*	<i>Does nothing.</i>
	*
	*/
	public void robotInit() {
		System.out.println("Robot has been initialized");
	}
	
	/**
	*	Procedures for when the robot has just been put into disabled mode.
	*	<i>Does nothing.</i>
	*
	*/
	public void disabledInit() {
		System.out.println("Robot has been disabled");
	}
	
	/**
	*	Procedures for when the robot has just been put into autonomous mode.
	*	The robot is stopped and the drive system is reset.
	*	Switches are read to determine which autonomous program to run.
	*	A timer is started to help autonomous programs use dead-reckoning.
	*
	*/
	public void autonomousInit() {
		System.out.println("Robot has been put into autonomous mode");
		// Stop everything.
		Hardware.robotDrive.stop();
		
		// Find out which autnomous mode we want
		// Leftmost worth 4
		autonomousMode += Hardware.autonomousSwitches[0].get() ? 4 : 0;
		// Middle worth 2
		autonomousMode += Hardware.autonomousSwitches[1].get() ? 2 : 0;
		// Rightmost worth 1
		autonomousMode += Hardware.autonomousSwitches[2].get() ? 1 : 0;
		
		// Start the autonomous timer, referenced by all autonomous modes
		autonomousTimer.start();
	}
	
	/**
	*	Procedures for when the robot has just been put into teleoperator mode.
	*	The robot is stopped and the drive system reset.  Just in case you know.
	*
	*/
	public void teleopInit() {
		System.out.println("Robot has been put into teleoperator mode");
        Hardware.robotDrive.stop();
	}
	
	
	/*--- Periodic Routines ---*/
		// Where stuff happens
		
	/**
	*	What the robot should do while disabled, over and over again.
	*	<i>Does nothing.</i>
	*
	*/
	public void disabledPeriodic() {
		Watchdog.getInstance().feed();
	}
	
	/**
	*	What the robot should do while in autonomous mode, over and over again.
	*	It will call the static run() method of the corrent autonomous class.
	*	Authors of autonomous run() methods should take care that their functions
	*	terminate in a timely fashion, and use the timer for timed functions.
	*
	*/
	public void autonomousPeriodic() {
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
	
	/**
	*	What the robot should do while in teleoperated mode, over and over again.
	*	Currently, this function sets the motors, using ramping, to the joysticks values.
	*	It also checks the trigger state, and attempts to kick the ball if the trigger is pressed.
	*
	*	@see DriveSystem#driveAtSpeed
	*	@see Kicking#kickBall
	*
	*/
	public void teleopPeriodic() {
		Watchdog.getInstance().feed();
		
		// Respond to drivers
		double goLeft = Hardware.leftJoystick.getY();
		double goRight = Hardware.rightJoystick.getY();
		Hardware.robotDrive.driveAtSpeed(goLeft, goRight);
		
		// See if we're supposed to be kicking the ball.
		if (Hardware.rightJoystick.getTrigger()) {
			Kicking.kickBall();
		}
		
	}

}