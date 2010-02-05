/*

   / /_  _________  / /_____  ____     / /_  _________  / /_____  ____ 
  / __ \/ ___/ __ \/ //_/ _ \/ __ \   / __ \/ ___/ __ \/ //_/ _ \/ __ \
 / /_/ / /  / /_/ / ,< /  __/ / / /  / /_/ / /  / /_/ / ,< /  __/ / / /
/_.___/_/   \____/_/|_|\___/_/ /_/  /_.___/_/   \____/_/|_|\___/_/ /_/ 

   / /_  _________  / /_____  ____     / /_  _________  / /_____  ____ 
  / __ \/ ___/ __ \/ //_/ _ \/ __ \   / __ \/ ___/ __ \/ //_/ _ \/ __ \
 / /_/ / /  / /_/ / ,< /  __/ / / /  / /_/ / /  / /_/ / ,< /  __/ / / /
/_.___/_/   \____/_/|_|\___/_/ /_/  /_.___/_/   \____/_/|_|\___/_/ /_/ 

   / /_  _________  / /_____  ____     / /_  _________  / /_____  ____ 
  / __ \/ ___/ __ \/ //_/ _ \/ __ \   / __ \/ ___/ __ \/ //_/ _ \/ __ \
 / /_/ / /  / /_/ / ,< /  __/ / / /  / /_/ / /  / /_/ / ,< /  __/ / / /
/_.___/_/   \____/_/|_|\___/_/ /_/  /_.___/_/   \____/_/|_|\___/_/ /_/ 

I'm sorry I'm such a failtrain.
-Eric

*/

/*
*	Team 3181 Robotics
*		Project:	Breakaway
*		Codename:	Demoman
*						(The Chargin Scottsman)
*		Filename:	Diagnostics.java
*		Authors:	@eric
*					
*		This file contains the functions related to diagnostics
*
*		--------------------------------------------------------------------
*		--------------------------------------------------------------------
*		Function reference:
*
*		static void sendMessage(String message)
*			-> Send a message prefixed with [TeamPrefix] and [SectionPrefix]
*
*		static void setSectionPrefix(String pre)
*			-> Set the section prefix
*
*		static void reset()
*			-> Reset anything set by sections
*
*		static String getTeamPrefix()
*			-> Get the team prefix
*
*		static String getSectionPrefix()
*			-> Get the section prefix
*
*		--------------------------------------------------------------------
*		--------------------------------------------------------------------
*		
*/

public class Diagnostics {
	static final boolean DIAGNOSTICS_ENABLED = true;
	static final String TEAM_PREFIX = "[3181] ";
	static String sectionPrefix = "";
	
	static void sendMessage(String message) {
			System.out.print((TEAM_PREFIX));
			System.out.print(sectionPrefix);
			System.out.println(message);
	}
	
	static void setSectionPrefix(String pre) {
		sectionPrefix = pre;
	}
	
	static void reset() {
		sectionPrefix = null;
	}
	
	static String getTeamPrefix() {
		return TEAM_PREFIX;
	}
	
	static String getSectionPrefix() {
		return sectionPrefix;
	}

}