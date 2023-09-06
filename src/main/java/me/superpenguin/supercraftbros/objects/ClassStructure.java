package me.superpenguin.supercraftbros.objects;

import java.util.HashMap;

import me.superpenguin.supercraftbros.SuperCraftBros;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class ClassStructure {
	
	private HashMap<SuperCraftBros.kitType, ClassData> structure;
	
	/**
	 * Class created to handle the class data within a players {@link PlayerData} file.
	 */
	public ClassStructure(FileConfiguration cfg) {
		structure = new HashMap<>();
		ConfigurationSection data = cfg.getConfigurationSection("ClassData");
		for (String x : data.getKeys(false)) {
			SuperCraftBros.kitType type = SuperCraftBros.kitType.valueOf(x);
			structure.put(type, new ClassData(type, data.getConfigurationSection(x)));
		}
	}

	public void set(FileConfiguration cfg) {
		for (ClassData c : structure.values()) c.set(cfg);		
	}
	
	
	public ClassData get(SuperCraftBros.kitType type) {
		if (structure.containsKey(type)) return structure.get(type);
		else {
			structure.put(type, new ClassData(type));
			return structure.get(type);
		}
	}
	
	public void addTimeLived(SuperCraftBros.kitType type, long ms) { get(type).addTimeLived(ms); }
	public void addKills(SuperCraftBros.kitType type, int amount) { get(type).addKills(amount); }
	public void addDeaths(SuperCraftBros.kitType type, int amount) { get(type).addDeaths(amount); }
	public void addWin(SuperCraftBros.kitType type) { get(type).addWin(); }
	public void addGamePlayed(SuperCraftBros.kitType type) { get(type).addGamePlayed(); }
	public void addDamageDone(SuperCraftBros.kitType type, int amount) { get(type).addDamageDone(amount); }
}
