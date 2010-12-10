package demoman;

/*
*	Team 3181 Robotics
*		Project:	Breakaway
*		Codename:	Demoman
*						(The Chargin Scottsman)
*		Filename:	AutonomousZone2.java
*/
/**
*	This class contains the autonomous program "AutonomousZone3"
*
*/

public class AutonomousZone3 {

	static void run(){

                System.out.println("AutonomousZone3");

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
		else if (timerValue<6.2) {
                    Hardware.robotDrive.stop();
                    Kicking.kickBall();
                } else if (timerValue < 6.65) {
                        Hardware.robotDrive.driveAtSpeed(-.35,-.35);
                    
                } else if (timerValue < 7.18) {
                    // Rotate left 45 degrees
                    Hardware.robotDrive.driveAtSpeed(-0.6, 0.6);
                } else if (timerValue < 8.5) {
                    Hardware.robotDrive.driveAtSpeed(-.35, -.35);
                } else {
                    Hardware.robotDrive.stop();
                    Kicking.kickBall();
                }
	}
}