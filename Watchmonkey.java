/** 
	This is a new name for the Watchdog class.  Cause "Watchdog" is a stupid name.
	Also I added a new method, attack.  Just for fun.
	@class	AttackMonkey
	@author Eric Vernon
	@written Jan 16, 2010
------------------------------------------------------- */
import edu.wpi.first.wpilibj.*;
public class AttackMonkey extends Watchdog
{
	/** 
		This function attacks whomever you tell it to, just like a good attack monkey.
		@method attack
		@author Eric Vernon
		@written Jan 16, 2010
	------------------------------------------------------- */
	public String attack(String name)
	{
		return String.format("%s has been attacked!  Maimed and clawed!", name);
	}
	
	/** 
		This function attacks Pesce.
		@OVERLOAD
		@method attack
		@author Eric Vernon
		@written Jan 16, 2010
	------------------------------------------------------- */
	public String attack()
	{
		return "Pesce has been attacked!  Maimed and clawed!";
	}
}