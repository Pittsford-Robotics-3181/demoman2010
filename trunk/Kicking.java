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

        // IMPORTANT!!! The kickerLatchSwitch is WIRED BACKWARDS!!!!

        //
        public static Timer latchTimer = new Timer();
        public static Timer kickTimer = new Timer();
        public static boolean canKick = true;

	/**
	*	Kick the ball.  ONLY CALL THIS FUNCTION IF YOU ACTUALLY WANT TO KICK GODDAMMIT.
	*	Uses a thread to retract.  If the thread isn't working check out Kicking.java.bak
	*	to view the previous solution without messing with the SVN.
	*
	*/
        public static void kickBall()
        {
            // Make sure the reset sequence has finished
            if (!canKick)
            {
                return;
            }

            // Should already be off, but make sure we aren't trying to retract
            Hardware.solenoids[0].set(false);
            
            // Release the hounds....
            Hardware.solenoids[4].set(true);

            // And start the cycle
            canKick = false;
            kickTimer.start();
        }

        public static void pressureMaintenance()
        {
            //--- post kick maintenance ---
            // 1.5 seconds after fire:
            if (kickTimer.get() > 0.5)
            {
                // Open the gates!  Let the kicker in!
                Hardware.solenoids[4].set(true);
            }

            // 1.7 seconds after fire:
            if (kickTimer.get() > 0.85)
            {
                // Start retracting
                Hardware.solenoids[0].set(true);
                //kickTimer.stop();
                //kickTimer.reset();
            }

            //--- limit switch stuff ---
            if (1 == 2)
            {
                // Kicker is out, DI = 1
            }
            else
            {
                // Kicker is in, DI = 0
                    // Since the limit switch isn't working properly, wait
                    // until you can be pretty damn sure the kicker is in
                    // before you do stuff.
                if (kickTimer.get() > 2.35 && kickTimer.get() < 3.0)
                {
                    // It's been one second since the kicker returned

                    // Keep the pressure on, but drop the latch
                    Hardware.solenoids[0].set(true);
                    Hardware.solenoids[4].set(false);
                    
                } else if (kickTimer.get() >= 3.0) {
                    // It's been 1.5 seconds since the kicker returned

                    // Turn the pressure off
                    Hardware.solenoids[0].set(false);
                    Hardware.solenoids[4].set(false);

                    // Reset the timer, we've done all we need
                    kickTimer.stop();
                    kickTimer.reset();

                    // And the cycle is complete
                    canKick = true;
                } else {
                    // Kicker is in, but one second hasn't passed yet
                    latchTimer.start();
                }
            }

        }

        public static void pressureInit()
        {
            kickTimer.stop();
            kickTimer.reset();

            latchTimer.stop();
            latchTimer.reset();
        }

}