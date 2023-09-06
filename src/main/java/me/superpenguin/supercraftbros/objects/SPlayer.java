package me.superpenguin.supercraftbros.objects;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.superpenguin.supercraftbros.SuperCraftBros;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

/**
 * This class is not intended to persist beyond the scope of an individual game.
 * At the end of the game save any relevant data such as kills or statistics, then remove this from any persistent list.
 * However it is recommended to save any persistent data directly to the {@link IPlayer} class.
 * @author baww1
 *
 */
public class SPlayer {
	
	// Class data
	private final UUID uuid;
	private Player p;
	private List<Block> replaceWithAirOnDeath = new ArrayList<>();
	
	// Game specific
	private Kit kit;
	private Player lastDamagedBy;
	private int kills, lives, deaths, damagedone, suicides, xp; 
	private double gems;
	private boolean won = false;
	private long msAlive;
		
	// CoolDowns
	private Instant bonusCooldownStarted;
	private long bonusCooldownTimems;
	
	public SPlayer(UUID uuid) {
		this.uuid = uuid;	
		this.p = Bukkit.getPlayer(uuid);
		this.bonusCooldownStarted = Instant.now();
		this.bonusCooldownTimems = 1;
		this.kills = 0;
		this.lives = 5;
		this.deaths = 0;
		this.gems = 0;
		this.msAlive = 0;
		this.damagedone = 0;
		this.suicides = 0;
		this.xp = 0;
		if (!SuperCraftBros.playerData.containsKey(uuid)) SuperCraftBros.playerData.put(uuid, this);
	}
	
	
	public void takeLife() { lives--; }
	public void resetLastAttacker() { this.lastDamagedBy = null; }
	public int getLives() { return lives; }
	public void setLives(int lives) { this.lives = lives; }
	public int getKills() { return kills; }
	public void addKill() { kills++; }
	public int getDeaths() { return deaths; }
	public void addDeath() { deaths++; }
	public void addWin() { won = true; }
	public void addGem() { gems++; }
	public void addSuicide() { suicides++; }
	public void addDamageDone(int amount) { damagedone += amount; }
	public void addGems(double amount) { gems += amount; }
	public void multiplyGems(double multiplier) { gems = gems * multiplier; }
	public void addAlivems(long ms) { this.msAlive += ms; }
	public void addXP(int amount) { this.xp += amount; }

	public long getAlivems() { return msAlive; }
	public int getXP() { return xp; }
	public double getGems() { return gems; }
	public boolean hasKit() { return this.kit != null; }	
	public void setKit(Kit kit) { this.kit = kit; }	
	public Kit getKit() { return kit; }
	public void removeKit() {
		kit.removePerks(p);
		setBlocksToAir();
		this.kit = null;
		u.stripPlayer(p);
	}
	
	public void sendMessage(String msg) {
		p.sendMessage(u.cc(msg));
	}
	
	public void saveToIPlayer() {
		IPlayer i = SuperCraftBros.getPersistentPlayer(uuid);
		if (kit == null) return;
		SuperCraftBros.kitType type = kit.getType();
		i.addDeaths(type, deaths);
		i.addKills(type, kills);
		i.addGamePlayed(type);
		i.addGems((int)Math.round(gems));
		i.addmsLived(type, msAlive);
		i.addDamageDone(type, damagedone);
		i.addSuicides(suicides);
		i.addXP(xp);
		if (won) i.addWin(type);
		i.saveAsync();
	}
	
	public void applyKit() {	
		if (hasKit()) kit.removePerks(p);
		p.getInventory().clear();
		for (PotionEffect e : p.getActivePotionEffects()) p.removePotionEffect(e.getType());
		
		p.getInventory().setArmorContents(kit.getArmour());
		for (ItemStack x : kit.getItems()) p.getInventory().addItem(x);
		kit.activatePerks(p);
		
		p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
		p.setFoodLevel(20);
	}
	
	
	
	
	public long getBonusCooldownElapsedms() { return Duration.between(bonusCooldownStarted, Instant.now()).toMillis(); }
	public void setBonusCooldown(int seconds) {
		bonusCooldownStarted = Instant.now();
		bonusCooldownTimems = seconds * 1000;
	}	
	public boolean isBonusReady() {
		return getBonusCooldownElapsedms() >= bonusCooldownTimems;
	}
	public int getBonusRemainingTimeSeconds() {
		int remaining = Math.round((bonusCooldownTimems - getBonusCooldownElapsedms())/1000);
		return Math.max(remaining, 0);
	}
	public String getCooldownMessage() {
		return u.cc("&cThis is on cooldown for another " + getBonusRemainingTimeSeconds() + " seconds.");
	}
	
	public void setLastDamagedBy(Player p) {
		this.lastDamagedBy = p; 
	}
	
	public Player getPlayer() { return Bukkit.getPlayer(uuid); }
	
	public Player getLastDamagedBy() { 
		return lastDamagedBy; 
	}
	
	public void addBlockToReplaceList(Block b) {
		replaceWithAirOnDeath.add(b);
	}
	
	public List<Block> getReplaceWithAirOnDeathList() {
		return replaceWithAirOnDeath;
	}
	
	public void setBlocksToAir() {
		for (Block b : replaceWithAirOnDeath) b.setType(Material.AIR);
	}
	
	public void clearReplaceList() {
		replaceWithAirOnDeath = new ArrayList<>();
	}
	
	public void clearGameCache() {
		// called at the end of each game; removes cooldowns, blockCaches, etc.
	}
	
	public boolean canUse() {
		if (u.canInteract(uuid)) {
			if (isBonusReady()) {
				return true;		
			} else sendMessage(getCooldownMessage());
		}
		return false;
	}
	
	

}
