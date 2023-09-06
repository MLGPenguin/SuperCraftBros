package me.Penguin.SuperCraftBros.objects;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import me.Penguin.SuperCraftBros.utils.u;

public class scoreboard {
	
	private Scoreboard scoreboard;
	private Objective objective;
	private int currentLine;
	private List<String> colours;
	
	public scoreboard(String name, String displayName) {
		this.scoreboard	= Bukkit.getScoreboardManager().getNewScoreboard();
		this.objective = scoreboard.registerNewObjective(name, "dummy", u.cc(displayName));
		this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		this.currentLine = 1;
		this.colours = Arrays.asList("&a", "&b", "&c", "&d", "&e", "&f", "&1", "&2", "&3", "&4", "&5", "&6", "&7", "&8", "&9", "&0");
	}
	
	/**
	 * All lines, static and dynamic, must be unique
	 * @param line
	 */
	public void addStaticLine(String line) {
		// Score
		objective.getScore(u.cc(line)).setScore(currentLine);
		currentLine++;
	}
	
	/**
	 * @param teamName
	 * @param prefix
	 * @param suffix
	 */
	public void addDynamicLine(String teamName, String prefix, String suffix) {
		Team t = scoreboard.registerNewTeam(teamName);
		String x = colours.get(currentLine);
		t.addEntry(u.cc(x));
		t.setPrefix(u.cc(prefix));
		t.setSuffix(u.cc(suffix));
		objective.getScore(u.cc(x)).setScore(currentLine);
		currentLine++;
	}
	
	public Scoreboard getScoreboard() { return scoreboard; }
	
	public void apply(Player p) {
		
		p.setScoreboard(scoreboard);
		
		
		//Updating it:
		// player.getScoreboard().getTeam(teamName).setSuffix(kills);
	}
	
	
	

}
