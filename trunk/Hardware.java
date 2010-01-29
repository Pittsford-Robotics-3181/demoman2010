package demoman;
import edu.wpi.first.wpilibj.*;

/**
*	Team 3181 Robotics
*		Project:	Breakaway
*		Codename:	Demoman
*						(The Chargin Scottsman)
*		Filename:	Hardware.java
*		Authors:	@eric
*                               @ben
*					
*		This is where all hardware declarations occur.  If you need to check
*		slots, channels, etc., it's in here.  Separation of locic ;).
*
*		Currently there aren't any functions, just a lot of static primitives
*		and references.
*
*		!! TODO:
*			-> Rework imports to only pull what we need
*			-> Make sure the solenoids are mapped properly
*
*/

public class Hardware {
	// Robot drive system
	public static RobotDrive robotDrive = new RobotDrive(1, 3, 2, 4);
	
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
		// Has the kicker returned to its home position, with latch closed?
		public static DigitalInput kickerLatchSwitch = new DigitalInput(4, 1);
	
}