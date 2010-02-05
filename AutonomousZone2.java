package demoman;

/*
*	Team 3181 Robotics
*		Project:	Breakaway
*		Codename:	Demoman
*						(The Chargin Scottsman)
*		Filename:	AutonomousZone2.java
*/
/**		
*	This class contains the autonomous program "AutonomousZone1"
*	
*	@author Chris                                                  
*
*/

/* Execute this Autonomous Method if:

----------------------------------
|                       [Robot]  |  --- Zone 2
|                          |     |
|                          v     |
|                                |
|   [Empty]   [Empty]   [Ball ]  |
|                                |
|   [Empty]   [Ball ]   [Empty]  |
|                                |
|   [Empty]   [Empty]   [Empty]  |
|                                |
----------------------------------

*/

public class AutonomousZone2 {

	/*
	In ENGLISH:
	
	Wait (.5 sec)          .5
	Go forward (1 sec)    1.5
	Stop
	Kick (1.5 sec)        3.0
	Rotate Right (1 sec)  4.0
	Go forward (1 sec)    5.0
	Stop
	Rotate Left (1 sec)   6.0
	Go forward (1 sec)    7.0
	Stop
	Kick (1.5 sec)        8.5
	End
	*/
	
	static void run(){
		double timerValue=Demoman.autonomousTimer.get();
		if(timerValue<.5){}
		else if(timerValue<1.5){
			Hardware.robotDrive.driveAtSpeed(1.0,1.0);
		}
		else if(timerValue<3.0){
			Hardware.robotDrive.stop();
			Kicking.kickBall();
		}
		else if(timerValue<4.0){
			//Rotate right - use Hardware.robotDrive.driveAtSpeed(0.0,1.0)?
		}
		else if(timerValue<5.0){
			Hardware.robotDrive.driveAtSpeed(1.0,1.0);
		}
		else if(timerValue<6.0){
			Hardware.robotDrive.stop();
			//Rotate left - use Hardware.robotDrive.driveAtSpeed(1.0,0.0)?
		}
		else if(timerValue<7.0){
			Hardware.robotDrive.driveAtSpeed(1.0,1.0);
		}
		else if(timerValue<8.5){
			Hardware.robotDrive.stop();
			Kicking.kickBall();
		}
		else{}
}