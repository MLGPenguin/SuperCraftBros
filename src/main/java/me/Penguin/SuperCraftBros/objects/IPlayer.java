package me.Penguin.SuperCraftBros.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.Penguin.SuperCraftBros.Main.kitType;
import me.Penguin.SuperCraftBros.Main.pack;
import me.Penguin.SuperCraftBros.utils.levelutil;

/**
 * This class is intended to be the persistent player class of SuperCraftBros players,
 * PlayerData will be cached within this class (on PlayerJoin) and is expected to be saved periodically. (At the end of each game and on disconnect)
 * 
 * @author baww1
 *
 */
public class IPlayer {
	
	private UUID uuid;
	private int totalKills, totalWins, totalGamesPlayed, totalDeaths, gems, damagedone, suicides, xp, totalxp, level, prestige;
	private long msLived;
	private String name;
	private List<kitType> unlockedKits;
	private List<pack> unlockedPacks;
	private ClassStructure structure;
	
	
	
	public IPlayer(UUID uuid) {
		this.uuid = uuid;
		this.unlockedKits = new ArrayList<>();
		this.unlockedPacks = new ArrayList<>();
		loadDataFromFile();
	}

	public UUID getUUID() { return uuid; }
	public Player getPlayer() { return Bukkit.getPlayer(uuid); }
	public int getKills() { return totalKills; }
	public int getWins() { return totalWins; }
	public int getGamesPlayed() { return totalGamesPlayed; }
	public int getDeaths() { return totalDeaths; }
	public int getLevel() { return level; }
	public int getPrestigeLevel() { return prestige; }
	public int getGems() { return gems; }
	public int getDamageDone() { return damagedone; }
	public int getSuicides() { return suicides; }
	public int getXP() { return xp; }
	public int getTotalXP() { return totalxp; }
	public List<kitType> getUnlockedKits() { return unlockedKits; }	
	public String getName() { return name; }
	public long getmsLived() { return msLived; }
	public ClassStructure getStructure() { return structure; }
	
	public void addmsLived(kitType type, long amount) {  
		msLived += amount;
		structure.addTimeLived(type, amount);
	}
	public void addKills(kitType type, int amount) { 
		totalKills += amount; 
		structure.addKills(type, amount);
	}
	public void addDeaths(kitType type, int amount) { 
		totalDeaths += amount; 
		structure.addDeaths(type, amount);
	}
	public void addWin(kitType type) { 
		totalWins++; 
		structure.addWin(type);
	}
	public void addGamePlayed(kitType type) { 
		totalGamesPlayed++;
		structure.addGamePlayed(type);
	}
	
	public void addDamageDone(kitType type, int amount) {
		damagedone+= amount;
		structure.get(type).addDamageDone(amount);
	}
	
	public void addXP(int amount) {
		xp += amount;
		totalxp += amount;
		levelutil.levelUp(this);
	}
	
	public void addGems(int amount) { gems+=amount; }
	public void addSuicides(int amount) { suicides += amount; }	
	public void addLevel() { level++; }
	
	public void removeGems(int amount) { gems -= amount; }	
	public void removeXP(int amount) { xp -= amount; }
	
	public boolean hasGems(int amount) { return gems >= amount; }
	
	public void setName(String name) { this.name = name; }
	
	
	private void loadDataFromFile() {
		PlayerData d = new PlayerData(uuid);
		FileConfiguration c = d.getConfig();
		gems = c.getInt("gems");
		totalKills = c.getInt("kills");
		totalDeaths = c.getInt("deaths");
		totalWins = c.getInt("wins");
		totalGamesPlayed = c.getInt("gamesPlayed");
		level = c.getInt("level");
		prestige = c.getInt("prestige");
		msLived = c.getLong("TimeLived");
		name = c.getString("name");
		damagedone = c.getInt("DamageDone");
		suicides = c.getInt("Suicides");
		xp = c.getInt("XP");
		totalxp = c.getInt("TotalXP");
		if (c.contains("unlockedKits")) for (String x : c.getStringList("unlockedKits")) unlockedKits.add(kitType.valueOf(x));
		for (String x : c.getStringList("unlockedPacks")) unlockedPacks.add(pack.valueOf(x));
		structure = new ClassStructure(c);
	}
	
	public List<kitType> getAllUnlockedKits() {
		List<kitType> kits = new ArrayList<>();
		kits.addAll(unlockedKits);
		unlockedPacks.forEach(p -> kits.addAll(p.getKits()));
		return kits;
	}
	
	public void save() {
		new PlayerData(uuid).save(this);
	}
	
	public void saveAsync() { 
		new PlayerData(uuid).saveAsync(this);
	}
	
	
	
	
}
