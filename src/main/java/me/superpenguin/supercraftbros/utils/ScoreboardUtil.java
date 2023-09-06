package me.superpenguin.supercraftbros.utils;

import org.bukkit.entity.Player;

public class ScoreboardUtil {
	
	public static void update(Player p, String teamName, String newSuffix) {
		p.getScoreboard().getTeam(teamName).setSuffix(u.cc(newSuffix));
	}

}
