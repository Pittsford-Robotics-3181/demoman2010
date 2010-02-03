package demoman;

/*
*	Team 3181 Robotics
*		Project:	Breakaway
*		Codename:	Demoman
*						(The Chargin Scottsman)
*		Filename:	AutonomousDoNothing.java
*/
/**		
*	This class contains the autonomous program "DoNothing"
*	
*	@author eric                                                   
*
*/

public class AutonomousDoNothing {

	/**
	*	Execute the program "AutonomousDoNothing".
	*	Just stop the robot.
	*/
	static void run() {
		// Just in case, stop all moving parts
		Hardware.robotDrive.stop();
	}
}