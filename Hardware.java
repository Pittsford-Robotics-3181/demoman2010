package demoman;
import edu.wpi.first.wpilibj.*;

/*
*	Team 3181 Robotics
*		Project:	Breakaway
*		Codename:	Demoman
*						(The Chargin Scottsman)
*		Filename:	Hardware.java
*/
/**		
*		This is where all hardware declarations occur.  If you need to check
*		slots, channels, etc., it's in here.  Separation of logic ;).
*		<p>
*		Currently there aren't any functions, just a lot of static primitives
*		and references.
*	
*		@author eric                                                  
*
*/

public class Hardware {
	// Robot drive system
	public static DriveSystem robotDrive = new DriveSystem(1, 3, 2, 4);
	
	// Ball roller motor
	// Need hardware verification
	//public static PWM ballRoller = new PWM(9,9);
	
	
	// Joysticks
	public static Joystick rightJoystick = new Joystick(1);
	public static Joystick leftJoystick = new Joystick(2);
	
	// Solenoids
	// Declared anonymously because we couldn't get the syntax to work otherwise D:
    public static Solenoid[] solenoids = {
        new Solenoid(1), new Solenoid(2), new Solenoid(3),
        new Solenoid(4), new Solenoid(5), new Solenoid(6),
        new Solenoid(7), new Solenoid(8)
			};

	// Digital Inputs:
		// Autonomous switches
		public static DigitalInput[] autonomousSwitches = {
		// 4, 2 is the leftmost switch
		new DigitalInput(4, 2), new DigitalInput(4, 3), new DigitalInput(4, 4)
			};
	
		// Has the kicker returned to its home position, with latch closed?
		// TRUE means CLOSED
		public static DigitalInput kickerLatchSwitch = new DigitalInput(4, 1);
	
		// Are we in "Stopped" mode?
		// need hardware verification
		// public static DigitalInput stoppedModeSwitch = new DigitalInput(9,9);
		
	// Compressor
		public static Compressor compressor  = new Compressor(14, 1);
	
}