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
	DriveHub(int port) {
		super(port);
	}

        /**
	*	Return the value of a button. This uses the
        *       Joystick.getRawButton() method.
	*/
	public boolean getDigitalInput(int button) {
		return getRawButton(button);
	}
	
	/**
	*	Return the value of a joystick's stick thingy.
	*/
	public double getAnalogInput(String axis) {
		if (axis.equals("y") || axis.equals("Y")) {
                    double tmpY = getY();
                    /* -------------------------------- */
                    /* If between -.05 and +.05, make 0 */
                    /* This allows a wider zero range   */
                    /* -------------------------------- */
                    if ((tmpY > -.05) && (tmpY < .05))
                    {
                        tmpY = 0.0;
                    }
                    return tmpY;
                }
		if (axis.equals("x") || axis.equals("X")) {
                    double tmpX = getX();
                    if ((tmpX > -.05) && (tmpX < .05))
                    {
                        tmpX = 0.0;
                    }
                    return tmpX;
                }
                if (axis.equals("z") || axis.equals("Z")) {
                      double tmpZ = getZ();
                    if ((tmpZ > -.05) && (tmpZ < .05))
                    {
                        tmpZ = 0.0;
                    }
                    return tmpZ;
                }
                return 0.0;
	}
	
	/**
	*	Provide digital output to the driver
	*/
	/*public void giveOutput() {
		// Is the kicker loaded?
		if (Hardware.kickerLatchSwitch.get()) {
                        Hardware.kickerReady[0].set(true);
                        Hardware.kickerReady[1].set(true);
		}
		
		// Is the ball ready to be kicked?
		if (Hardware.ballSensor.get()) {
                        Hardware.ballReady[0].set(true);
                        Hardware.ballReady[1].set(true);
		}
		
		// Is the winch locked?
		if (Winch.isLocked()) {
                        Hardware.winchLocked[0].set(true);
                        Hardware.winchLocked[1].set(true);
		}
	
	}*/
	
}