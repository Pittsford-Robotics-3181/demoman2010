package demoman;
import edu.wpi.first.wpilibj.*;

/*
*	Team 3181 Robotics
*		Project:	Breakaway
*		Codename:	Demoman
*						(The Chargin Scottsman)
*		Filename:	Kicking.java
*/
/**		
*	All the kicking functions are housed here.
*	
*	@author eric                                            
*
*/

public class Kicking {

	// [if you're looking for those kicking variables, they've been replaced by threading]
	
	/**
	*	Kick the ball.  ONLY CALL THIS FUNCTION IF YOU ACTUALLY WANT TO KICK GODDAMMIT.
	*	Uses a thread to retract.  If the thread isn't working check out Kicking.java.bak
	*	to view the previous solution without messing with the SVN.
	*
	*/
	public static void kickBall() {
		/*
		 *	When not called:	- Fire S3 and S4 to keep the pressure up
		 *	When called:		- Fire S5, which releases the latch
		 *						- Start a timer
		 *	S1, S2 fire 1.5 seconds afterwards
		 *	S3, S4 turn off 
		 */

		// Shoot if we can
		if (Hardware.kickerLatchSwitch.get()) {
			Hardware.solenoids[4].set(true);
			// Retract the kicker
			try {
				(new Thread(new RetractRunnable())).start();
			} catch (Exception ex) {
				// This shouldn't ever happen
				System.out.println("Kicking thread has been interrupted :o");
			}
		} else {
            // Can't kick because the kicker isn't there!
		}
		
		// If the latch is closed, make sure S1 and S2 are off, because they've done their job
		if (Hardware.kickerLatchSwitch.get()) {
			Hardware.solenoids[0].set(false);
			Hardware.solenoids[1].set(false);
			// Make sure 3&4 are on to repressurize the piston, assuming that's what we want
			if (want normal power) {
			Hardware.solenoids[2].set(true);
			Hardware.solenoids[3].set(true);
			}
		}
		
		// DIAGNOSTIC - use a solenoid because println eats all the ram
		Hardware.solenoids[7].set(Hardware.kickerLatchSwitch.get());
		
	}
	
	// INNER CLASSS
	public static class RetractRunnable implements Runnable {
	
		public void run() {
			// Wait .85 seconds
			try {
			Thread.sleep(850); } catch (Exception e) {}
			// Reset the latch solenoid
			Hardware.solenoids[4].set(false);
			
			// Wait .65 seconds
			try {
			Thread.sleep(650); } catch (Exception e) {}
			// Retract the kicker
			while (!Hardware.kickerLatchSwitch.get()) {
				// Uh, stop pressurizing
				Hardware.solenoids[2].set(false);
				Hardware.solenoids[3].set(false);
				// And start retracting
				Hardware.solenoids[0].set(true);
				Hardware.solenoids[1].set(true);
			}
		}
	}
	// END INNER CLASS

}