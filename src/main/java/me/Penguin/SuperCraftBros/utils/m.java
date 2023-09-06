package me.Penguin.SuperCraftBros.utils;

public class m {

	// messages
	private static String prefix = prefix();		
	public static String prefix() { return u.hc("prefix "); }
	public static String noPermission, invalidPlayerSelf, unknownCommand, invalidnumber;
	
	public static void setup() {
		noPermission = msg("&cYou do not have permission to do this"); 
		invalidPlayerSelf = msg("&cYou need to be a player to use this command");
		unknownCommand = msg("&cUnknown Command");
		invalidnumber = msg("&cPlease specify a valid number");
	}

	public static String s() { return msg(""); }
	public static String invalidPlayerOther(String Name) { return msg("&c" + Name + " is not a player"); }
	
	
	private static String msg(String msg) {
		return u.hc(prefix + msg);
	}

}
