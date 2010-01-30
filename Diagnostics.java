/**
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

class Diagnostics {
	static final boolean DIAGNOSTICS_ENABLED = true;
	static final String TEAM_PREFIX = "[3181] ";
	static String sectionPrefix = "";
	
	static void sendMessage(String message) {
		System.out.println(String.format("%s%s %s" % TEAM_PREFIX, sectionPrefix, message));
	}
	
	static void setSectionPrefix(String pre) {
		this.sectionPrefix = pre;
	}
	
	static String getTeamPrefix() {
		return this.TEAM_PREFIX;
	}
	
	static String getSectionPrefix() {
		return this.sectionPrefix;
	}

}