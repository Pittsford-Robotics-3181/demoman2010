/* Authors:
	@ben
	@ericisapatheticfailure
*/
public class AutonomousZone1 {
	static void function run(){
		int timerValue=Demoman.autonomousTimer.get();
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