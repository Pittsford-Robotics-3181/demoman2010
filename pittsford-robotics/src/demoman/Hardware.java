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
	// Driver Station.  It's a freaking Joystick.
	public static DriveHub DS = new DriveHub(3);

	// Robot drive system
	// 1 is left; 10 is right
	public static DriveSystem robotDrive = new DriveSystem(1, 10);
	
	// Ball roller motor
	public static Victor ballRoller = new Victor(4, 4);
	
	// Winch motor
	public static Jaguar winchMotor = new Jaguar(4, 3);
	
	// Joysticks
	public static Joystick rightJoystick = new Joystick(1);
	public static Joystick leftJoystick = new Joystick(2);
	
	// Solenoids
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
		
		// Kicker Sensor - Are we ready to kick
		public static DigitalInput ballSensor = new DigitalInput(4, 5);
	
		// Are we in "Stopped" mode?
		// need hardware verification
		// public static DigitalInput stoppedModeSwitch = new DigitalInput(9,9);
	
	// Digital Outputs
		// Kicker Ready
	/*	public static DigitalOutput[] kickerReady = {
			new DigitalOutput(4, 3), new DigitalOutput(4, 4)
			};
	
		// Ball sensor
		public static DigitalOutput[] ballReady = {
			new DigitalOutput(4, 1), new DigitalOutput(4, 2)
			};
			
		// Winch Locked
		public static DigitalOutput[] winchLocked = {
			new DigitalOutput(4, 7), new DigitalOutput(4, 8)
			};
	*/
	// Compressor
		public static Compressor compressor  = new Compressor(14, 1);
	
}