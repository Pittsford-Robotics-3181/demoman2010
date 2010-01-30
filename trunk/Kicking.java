/**
*	Team 3181 Robotics
*		Project:	Breakaway
*		Codename:	Demoman
*						(The Chargin Scottsman)
*		Filename:	Kicking.java
*		Authors:	@eric
*					
*		This file defines the kicking system.  It includes functions for kicking. 
*
*		--------------------------------------------------------------------
*		--------------------------------------------------------------------
*		Function reference:
*
*		static void kickBall()
*			-> Kicks the ball assuming the kicker is retracted
*			-> Retracts the kicker
*			-> Only call if you're sure you want to kick
*
*		--------------------------------------------------------------------
*		--------------------------------------------------------------------
*
*/

public class Kicking {

	public void kickBall() {
		/*	When called: Fire S3, S4
		*	S1, S2 fire 1.5 seconds afterwards
		*	S3, S4 turn off 
		*/
		
		Diagnostics.setSection("[Kicking]");
		Diagnostics.sendMessage("kickBall has been called");
		
		// Shoot
		if (Hardware.kickerLatchSwitch.get()) {
			Hardware.solenoids[4].set(true);
			Diagnostics.sendMessage("Kicked!");
		} else {
			Diagnostics.sendMessage("Tried to kick, but the latch wasn't closed");
		}
		
		// Check to see if this is the first loop the latch has been open for
		if (!Hardware.kickerLatchSwitch.get() && !kickerTimerIsStale) {
			kickerTimer.reset();
			kickerTimer.start();
			kickerTimerIsStale = true;
		}
		
		// If the latch is closed, shut off S1 and S2, because they've done their job
		if (Hardware.kickerLatchSwitch.get()) {
			Hardware.solenoids[0].set(false);
			Hardware.solenoids[1].set(false);
			// Reset the timer, or else it'll think it needs to retract the arm
			kickerTimer.stop();
			kickerTimer.reset();
            kickerTimerIsStale = false;
			// Start 3&4 to repressurize
			Hardware.solenoids[2].set(true);
			Hardware.solenoids[3].set(true);
		}
		
		// Reset the latch solenoid BEFORE you retract the kicker
		if (kickerTimer.get() > .85) {
			Hardware.solenoids[4].set(false);
		}
		
		// Retract the kicker
		if (kickerTimer.get() >= 1.5) {
			Hardware.solenoids[0].set(true);
			Hardware.solenoids[1].set(true);
			Hardware.solenoids[2].set(false);
			Hardware.solenoids[3].set(false);
		}
		
		// DIAGNOSTIC - use a solenoid because println eats all the ram
		Diagnostics.reset();
		Hardware.solenoids[7].set(Hardware.kickerLatchSwitch.get());
	}

}