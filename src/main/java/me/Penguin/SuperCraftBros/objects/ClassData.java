package me.Penguin.SuperCraftBros.objects;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import me.Penguin.SuperCraftBros.Main.kitType;

public class ClassData {
	
	private kitType type;	
	private int kills, deaths, wins, gamesPlayed, damagedone;
	private long TimeLived;
	
	/**
	 * Individual Class Data store for a player, contained within {@link ClassStructure}
	 * @param type
	 * @param kit Construct this with the ConfigurationSection("ClassData." + kitType.toString());
	 */
	public ClassData(kitType type, ConfigurationSection kit) {
		this.type = type;
		kills = kit.getInt("Kills");
		deaths = kit.getInt("Deaths");
		wins = kit.getInt("Wins");
		gamesPlayed = kit.getInt("GamesPlayed");
		TimeLived = kit.getLong("TimeLived");
		damagedone = kit.getInt("DamageDone");
	}
	
	public ClassData(kitType type) {
		this.type = type;
		kills = 0;
		deaths = 0;
		wins = 0;
		gamesPlayed = 0;
		TimeLived = 0;
		damagedone = 0;
	}
	
	public void set(FileConfiguration cfg) {
		cfg.set("ClassData." + type.toString() + ".Kills", kills);
		ConfigurationSection c = cfg.getConfigurationSection("ClassData." + type.toString());
		c.set("Deaths", deaths);
		c.set("Wins", wins);
		c.set("GamesPlayed", gamesPlayed);
		c.set("TimeLived", TimeLived);
		c.set("DamageDone", damagedone);
	}
	
	public void addTimeLived(long ms) { TimeLived += ms; }
	public void addKills(int amount) { kills += amount; }
	public void addDeaths(int amount) { deaths += amount; }
	public void addWin() { wins++; }
	public void addGamePlayed() { gamesPlayed++; }
	public void addDamageDone(int amount) { damagedone += amount; }
	
	public long getTimeLived() { return TimeLived; }
	public int getKills() { return kills; }
	public int getDeaths() { return deaths; }
	public int getWins() { return wins; }
	public int getGamesPlayed() { return gamesPlayed; }
	public int getDamageDone() { return damagedone; }
	
}
