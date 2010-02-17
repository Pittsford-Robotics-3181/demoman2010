package demoman;
import edu.wpi.first.wpilibj.*;

/*
*	Team 3181 Robotics
*		Project:	Breakaway
*		Codename:	Demoman
*						(The Chargin Scottsman)
*		Filename:	DriveHub.java
*/
/**		
*		This class is for the driver station.  It includes digital and analog inputs,
*		and should be updated next year to use the Cyprus module or whatnot.  But, because
*		Reese is stupid and blew up our Cyrpus, we're using a hacked joystick.
*	
*		@author eric                                                  
*
*/

public class DriveHub extends Joystick {
	
	/**
	*	Constructor: Currently, what port the not-joystick is on.
	*/
	public void DriveHub(int port) {
		super(port);
	}
	
	public boolean getDigitalInput(int button) {
		return getRawButton(button);
	}
	
	/**
	*	Return the value of a joystick's stick thingy.
	*/
	public double getAnalogInput(String axis) {
		switch (axis) {
			case "y":
			case "Y":
				return getY();
				break;
			case "x":
			case "X":
				return getX();
				break;
			case "z":
			case "Z":
				return getZ();
				break;
			}
		}
	}


}