package demoman;

/**
*	Team 3181 Robotics
*		Project:	Breakaway
*		Codename:	Demoman
*						(The Chargin Scottsman)
*		Filename:	AutonomousZone1.java
*		Authors:	@ben
*					@eric
*					
*		This file contains the autonomous program "Zone1"
*		
*/
public class AutonomousZone1 {
	static void run(){
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