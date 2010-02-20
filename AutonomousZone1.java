package demoman;

/*
*	Team 3181 Robotics
*		Project:	Breakaway
*		Codename:	Demoman
*						(The Chargin Scottsman)
*		Filename:	AutonomousZone1.java
*/
/**		
*	This class contains the autonomous program "AutonomousZone1"
*	
*	@author ben   
*	@author eric                                               
*
*/

public class AutonomousZone1 {

	/**
	*	Execute the program "AutonomousZone1".
	*	Stop for .5 seconds, drive full speed forward for 1 second,
	*	then stop, and attempt to kick the ball for 1.5 seconds.
	*	Then stop executing altogether.
	*
	*/
	static void run(){
	
		// I don't care where you are.  If you can kick, go for it.
		if (Hardware.ballSensor.get()) {
			Kicking.kickBall();
		}
	
		double timerValue=Demoman.autonomousTimer.get();
		if(timerValue<.5){
		
		}else if(timerValue<1.5){
			Hardware.robotDrive.driveAtSpeed(1.0,1.0);
		}else if(timerValue<3.0){
			Hardware.robotDrive.stop();
			Kicking.kickBall();
		}else{
		
		}
	}
}