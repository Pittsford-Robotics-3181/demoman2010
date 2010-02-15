package demoman;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.NIVisionException;

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

	// Camera
	AxisCamera camera;
	
	
	/*--- Initialization Routines ---*/
	/**
	*	Procedures for when the robot has just been booted, but has NOT been put into any mode.
	*	<i>Does nothing.</i>
	*
	*/
	public void robotInit() {
		System.out.println("Robot has been initialized!");
		/*camera = AxisCamera.getInstance();
		camera.writeCompression(0);
		camera.writeBrightness(10);
		camera.writeResolution(AxisCamera.ResolutionT.k160x120);*/
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

		// Make sure the compressor is on
		Hardware.compressor.start();
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
		// Make sure the compressor is on
		Hardware.compressor.start();
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
		updateDashboard();
				switch (autonomousMode) {
			case 0:
				AutonomousDoNothing.run();
				break;
			case 1:
				AutonomousZone1.run();
				break;
			case 2:
				AutonomousZone2.run();
				break;
		}
		Kicking.pressureMaintenance();
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
		
		// Are we in "stopped" mode?  This is a mode where we disabled ourselves WITHOUT the use
		// of the e-stop button.  Useful for demonstrations.
			// commented out until i can get a verification on the hardware
		/*if(Hardware.stoppedModeSwitch.get())
		{
			Hardware.robotDrive.stop();
			return;
		}*/
						// Respond to drivers
		updateDashboard();
		double goLeft = Hardware.leftJoystick.getY();
		double goRight = Hardware.rightJoystick.getY();
		Hardware.robotDrive.driveAtSpeed(goLeft, goRight);
		
		// See if we're supposed to be kicking the ball.
		if (Hardware.rightJoystick.getTrigger() || Hardware.leftJoystick.getTrigger() || DS.getEnhancedIO.getDigital(5)) {
			Kicking.kickBall();
		}
		Kicking.pressureMaintenance()
		
		
		// (UN)Locking the winch?
		if (Hardware.DS.getEnhancedIO().getDigital(2)) {
			Winch.changeLockState();
		}
		Winch.actOnLockState();
		
		// Lifting us up?
		if (Hardware.DS.getEnhancedIO().getDigital(1)) {
			Winch.lift();
		} else {
			Winch.stop();
		}
		
		// Control the ball roller
		if (Hardware.DS.getEnhancedIO().getDigital(3)) {
			// If 3 is true, we're rolling forward
			Hardware.ballRoller.set(1);
		} else if (Hardware.DS.getEnhanced().getDigital(4)) {
			// If 3 is false, it's possible 4 is true, in which case we want to go backward
			// Remember, if 3 is true there is no possible way 4 is also true, so an else if works
			Hardware.ballRoller.set(-1);
		} else {
			// Neither are true, so let's turn it off (Flipper is in the middle)
			Hardware.ballRoller.set(0);
		}
		
	}
	
	/**
	*	Update the dashboard with cool information.  Talk to Reese if you want to know specifics
	*	about this function, he got it from teh internetz and put it in here, but didn't both
	*	to write documentation for it.
	*
	*/
	void updateDashboard() {
		Dashboard lowDashData = DriverStation.getInstance().getDashboardPackerLow();
		lowDashData.addCluster();
		{
			lowDashData.addCluster();
			{	 //analog modules
				lowDashData.addCluster();
				{
					for (int i = 1; i <= 8; i++) {
						lowDashData.addFloat((float) AnalogModule.getInstance(1).getAverageVoltage(i));
					}
				}
				lowDashData.finalizeCluster();
				lowDashData.addCluster();
				{
					for (int i = 1; i <= 8; i++) {
						lowDashData.addFloat((float) AnalogModule.getInstance(2).getAverageVoltage(i));
					}
				}
				lowDashData.finalizeCluster();
			}
			lowDashData.finalizeCluster();

			lowDashData.addCluster();
			{ //digital modules
				lowDashData.addCluster();
				{
					lowDashData.addCluster();
					{
						int module = 4;
						lowDashData.addByte(DigitalModule.getInstance(module).getRelayForward());
						lowDashData.addByte(DigitalModule.getInstance(module).getRelayForward());
						lowDashData.addShort(DigitalModule.getInstance(module).getAllDIO());
						lowDashData.addShort(DigitalModule.getInstance(module).getDIODirection());
						lowDashData.addCluster();
						{
							for (int i = 1; i <= 10; i++) {
								lowDashData.addByte((byte) DigitalModule.getInstance(module).getPWM(i));
							}
						}
						lowDashData.finalizeCluster();
					}
					lowDashData.finalizeCluster();
				}
				lowDashData.finalizeCluster();

				lowDashData.addCluster();
				{
					lowDashData.addCluster();
					{
						int module = 6;
						lowDashData.addByte(DigitalModule.getInstance(module).getRelayForward());
						lowDashData.addByte(DigitalModule.getInstance(module).getRelayReverse());
						lowDashData.addShort(DigitalModule.getInstance(module).getAllDIO());
						lowDashData.addShort(DigitalModule.getInstance(module).getDIODirection());
						lowDashData.addCluster();
						{
							for (int i = 1; i <= 10; i++) {
								lowDashData.addByte((byte) DigitalModule.getInstance(module).getPWM(i));
							}
						}
						lowDashData.finalizeCluster();
					}
					lowDashData.finalizeCluster();
				}
				lowDashData.finalizeCluster();

			}
			lowDashData.finalizeCluster();

			lowDashData.addByte(Solenoid.getAll());
		}
		lowDashData.finalizeCluster();
		lowDashData.commit();

	}
}
