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
	
	boolean locked = false;
	boolean stateLocked = false;
	Timer llt = new Timer();
	
	
	/**
	*	Move up move up move up!  Still need to figure out when to stop though....
	*
	*/
	public static void lift() {
		// 0 - 1023
		// need channel verification
		int intSpeed = Hardware.DS.getEnhancedIO().getAnalogIn(2);
		System.out.println("Lifting the winch.  The pure value from the dial is: " + intSpeed);
		double dSpeed = (intSpeed / 1025);
		
		Hardware.winchMotor.set(dSpeed);
	}
	
	/**
	*	Stop the winch.
	*
	*/
	public static void stop() {
		Hardware.winchMotor.set(0);
	}
	
	/** Is the winch locked? */
	public static boolean isLocked() {
		return locked;
	}
	
	/** Change the lock state, and wait half a second before allowing the state to change. */
	public static void changeLockState() {
		if (stateLocked) {
			// Timer expired?
			if (llt.get() > .5) {
				llt.stop();
				llt.reset();
			
				stateLocked = false;
				changeLockState();
			}
		}
		else {
			if (locked) {
				locked = false;
			} else {
				locked = true;
			}
			
			llt.start();
		}
	}
	
	/** Act on our current state */
	public static void actOnLockState() {
		// If locking, make sure it's stopped
		if (locked) {
			stop();
			Hardware.solenoids[5].set(true);		
		} else {
			Hardware.solenoids[5].set(false);
		}
	}
	
}