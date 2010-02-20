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
	
	private static boolean locked = false;
	// If true, we can't lock or unlock
	private static boolean stateLocked = false; 
	private static Timer llt = new Timer();
	
	
	/**
	*	Move up move up move up!  Still need to figure out when to stop though....
	*
	*/
	public static void lift(double speed) {
		// 0 - 1023
		// need channel verification
                System.out.println("Lifting the winch.  The requested speed is " + speed);
		
		Hardware.winchMotor.set(speed);
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