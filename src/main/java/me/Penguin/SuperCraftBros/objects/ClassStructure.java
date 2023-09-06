package me.Penguin.SuperCraftBros.objects;

import java.util.HashMap;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import me.Penguin.SuperCraftBros.Main.kitType;

public class ClassStructure {
	
	private HashMap<kitType, ClassData> structure;
	
	/**
	 * Class created to handle the class data within a players {@link PlayerData} file.
	 */
	public ClassStructure(FileConfiguration cfg) {
		structure = new HashMap<>();
		ConfigurationSection data = cfg.getConfigurationSection("ClassData");
		for (String x : data.getKeys(false)) {
			kitType type = kitType.valueOf(x);
			structure.put(type, new ClassData(type, data.getConfigurationSection(x)));
		}
	}

	public void set(FileConfiguration cfg) {
		for (ClassData c : structure.values()) c.set(cfg);		
	}
	
	
	public ClassData get(kitType type) {
		if (structure.containsKey(type)) return structure.get(type);
		else {
			structure.put(type, new ClassData(type));
			return structure.get(type);
		}
	}
	
	public void addTimeLived(kitType type, long ms) { get(type).addTimeLived(ms); }
	public void addKills(kitType type, int amount) { get(type).addKills(amount); }
	public void addDeaths(kitType type, int amount) { get(type).addDeaths(amount); }
	public void addWin(kitType type) { get(type).addWin(); }
	public void addGamePlayed(kitType type) { get(type).addGamePlayed(); }
	public void addDamageDone(kitType type, int amount) { get(type).addDamageDone(amount); }
}
