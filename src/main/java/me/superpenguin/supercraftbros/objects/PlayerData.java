package me.superpenguin.supercraftbros.objects;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import me.superpenguin.supercraftbros.SuperCraftBros;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerData {
	
	private UUID uuid;
	private FileConfiguration cfg;
	
	/**
	 * The PlayerData class provides access to a player's playerdata file.
	 * @param uuid
	 */
	public PlayerData(UUID uuid) {
		this.uuid = uuid;
		this.cfg = getConfigFromFile();
	}
		
	public UUID getUUID() { return uuid; }
	public FileConfiguration getConfig() { return cfg; }
	
	public void setup(File file) {
		if (!file.exists()) {
			try {				
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void setDefaults(File file) {
		cfg = YamlConfiguration.loadConfiguration(file);
		cfg.set("name", Bukkit.getPlayer(uuid).getName());
		cfg.set("gems", 0);
		cfg.set("kills", 0);
		cfg.set("deaths", 0);
		cfg.set("wins", 0);
		cfg.set("gamesPlayed", 0);
		cfg.set("level", 1);
		cfg.set("prestige", 0);
		cfg.set("TimeLived", 0);
		cfg.set("DamageDone", 0);
		cfg.set("Suicides", 0);
		cfg.set("XP", 0);
		cfg.set("TotalXP", 0);
		cfg.set("unlockedPacks", Arrays.asList(SuperCraftBros.pack.DEFAULT.toString()));
		new ClassData(SuperCraftBros.kitType.STEVE).set(cfg);
		saveInitial(file);
	}

	public void save(IPlayer player) {
		cfg.set("name", player.getName());
		cfg.set("gems", player.getGems());
		cfg.set("kills", player.getKills());
		cfg.set("deaths", player.getDeaths());
		cfg.set("wins", player.getWins());
		cfg.set("gamesPlayed", player.getGamesPlayed());
		cfg.set("level", player.getLevel());
		cfg.set("prestige", player.getPrestigeLevel());
		cfg.set("TimeLived", player.getmsLived());
		cfg.set("DamageDone", player.getDamageDone());
		cfg.set("Suicides", player.getSuicides());
		cfg.set("XP", player.getXP());
		cfg.set("TotalXP", player.getTotalXP());
		player.getStructure().set(cfg);
		save();
	}
	
	
	private File getFile() {
		File file = new File("plugins/SuperCraftBros/Players/" + uuid + ".yml");
		if (!file.exists()) {
			setup(file);
			setDefaults(file);
		}
		return file;
	}

	public FileConfiguration getConfigFromFile() {
		return YamlConfiguration.loadConfiguration(getFile());
	}
	
	public void setName(String name) {
		cfg.set("name", name);
		saveAsync();
	}
	
	public void unlockKit(SuperCraftBros.kitType type) {
		Set<SuperCraftBros.kitType> unlockedKits = getUnlockedKits();
		unlockedKits.add(type);
	//	List<String> names = unlockedKits.stream().map(kitType::toString).collect(Collectors.toList());
		cfg.set("unlockedKits", unlockedKits);
		saveAsync();
	}
	
	public void unlockPack(SuperCraftBros.pack pack) {
		Set<SuperCraftBros.pack> unlockedPacks = getUnlockedPacks();
		unlockedPacks.add(pack);
		cfg.set("unlockedPacks", unlockedPacks);
		saveAsync();
		//TODO test to make sure I don't have to convert to string first
	}
	
	/**
	 * Does not include packs
	 * @return
	 */
	private Set<SuperCraftBros.kitType> getUnlockedKits() {
		if (cfg.contains("unlockedKits"))
		return cfg.getStringList("unlockedKits").stream().map((SuperCraftBros.kitType::valueOf)).collect(Collectors.toSet());
		else return new HashSet<>();
	}	
	
	
	private Set<SuperCraftBros.pack> getUnlockedPacks() {
		return cfg.getStringList("unlockedPacks").stream().map(SuperCraftBros.pack::valueOf).collect(Collectors.toSet());
	}

	
	private void saveInitial(File file) {
		try { cfg.save(file); } catch (IOException e) { e.printStackTrace(); }
	}
	
	private void save() {
		try {
			cfg.save(getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void saveAsync() {
		new BukkitRunnable() {			
			@Override
			public void run() {
				save();
			}
		}.runTaskAsynchronously(SuperCraftBros.get());
	}	


	public void saveAsync(IPlayer player) {
		new BukkitRunnable() {			
			@Override
			public void run() {
				save(player);
			}
		}.runTaskAsynchronously(SuperCraftBros.get());
	}
	
}
