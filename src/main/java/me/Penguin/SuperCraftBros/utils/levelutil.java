package me.Penguin.SuperCraftBros.utils;

import java.util.HashMap;

import me.Penguin.SuperCraftBros.objects.CPlayer;
import me.Penguin.SuperCraftBros.objects.IPlayer;

public class levelutil {

	/**
	 * The amount of XP it requires to LEVEL UP TO the next level,
	 * * 1:5 means it would take 5 xp to reach level 2
	 */
	public static HashMap<Integer, Integer> getXP = new HashMap<>() {{
		put(0, 0);
		put(1, 5);
		put(2, 8);
		put(3, 12);
		put(4, 16);
		put(5, 20);		
	}};
		
	
	public static boolean canLevelUp(int level, int xp) {
		return xp >= getXP.get(level);
	}
	
	/**
	 * Level up the player IF they have the required XP
	 * @param player player to level up.
	 */
	public static void levelUp(IPlayer player) {
		if (canLevelUp(player.getLevel(), player.getXP())) {
			CPlayer c = new CPlayer(player.getPlayer());
			player.removeXP(getXP.get(player.getLevel()));
			player.addLevel();
			c.updateLevelXP();
			player.getPlayer().sendMessage(u.cc("&aCongratulations! You have levelled up"));
		}
	}
	
	
	
	
}
