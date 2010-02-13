package demoman;
import edu.wpi.first.wpilibj.*;

/*
*	Team 3181 Robotics
*		Project:	Breakaway
*		Codename:	Demoman
*						(The Chargin Scottsman)
*		Filename:	Winch.java
*/
/**		
*	Control the winch.
*	
*	@author eric                                            
*
*/

public class Winch {
	
	/**
	*	Move up move up move up!  Still need to figure out when to stop though....
	*
	*/
	public static void lift() {
		// 0 - 1023
		// need verification on the channel number
		int intSpeed = DriverStation.getInstance().getAnalogIn(2);
		double dSpeed = (intSpeed / 1025);
		
		Hardware.winchMotor.set(dSpeed);
	
	}
}