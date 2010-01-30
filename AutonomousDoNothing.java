/**
*	Team 3181 Robotics
*		Project:	Breakaway
*		Codename:	Demoman
*						(The Chargin Scottsman)
*		Filename:	AutonomousDoNothing.java
*		Authors:	@eric
*					
*		This file contains the autonomous program "DoNothing"
*		
*/

public class AutonomousDoNothing {
	static function run() {
		// Just in case, stop all moving parts
		Hardware.robotDrive.stop();
	}
}