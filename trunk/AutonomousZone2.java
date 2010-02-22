package demoman;

/*
*	Team 3181 Robotics
*		Project:	Breakaway
*		Codename:	Demoman
*						(The Chargin Scottsman)
*		Filename:	AutonomousZone2.java
*/
/**		
*	This class contains the autonomous program "AutonomousZone2".
*
*/

public class AutonomousZone2 {
	
	static void run(){

                System.out.println("AutonomousZone2");

		if (Hardware.ballSensor.get()) {
			Kicking.kickBall();
		}
	
		double timerValue=Demoman.autonomousTimer.get();
		if(timerValue<.5){
                    Hardware.robotDrive.stop();
                }
		else if(timerValue<2.0){
			Hardware.robotDrive.driveAtSpeed(-.35, -.35);
		}
		else if(timerValue<2.9){
			Hardware.robotDrive.stop();
			Kicking.kickBall();
		}
		else if(timerValue<3.5){
			//Rotate right 45 degrees
                        Hardware.robotDrive.driveAtSpeed(.6,-.6);
		}
		else if(timerValue<5.8){
			Hardware.robotDrive.driveAtSpeed(-0.35,-0.35);
		}
		else{
                    Hardware.robotDrive.stop();
                    Kicking.kickBall();
                }
	}
}