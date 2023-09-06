package me.Penguin.SuperCraftBros.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;

import me.Penguin.SuperCraftBros.Main;
import me.Penguin.SuperCraftBros.Main.kitType;
import me.Penguin.SuperCraftBros.objects.GUI;
import me.Penguin.SuperCraftBros.objects.Kit;
import me.Penguin.SuperCraftBros.objects.SPlayer;
import me.Penguin.SuperCraftBros.tags.ExplosionResistant;


public class u {
	
	
	public static String translateHexColorCodes(/*String startTag, String endTag,*/ String message) {
		final char COLOR_CHAR = '\u00A7';
		final Pattern hexPattern = Pattern.compile(/*startTag*/ "#" + "([A-Fa-f0-9]{6})" /*+ endTag*/);
		Matcher matcher = hexPattern.matcher(message);
		StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
		while (matcher.find()) {
			String group = matcher.group(1);
			matcher.appendReplacement(buffer, COLOR_CHAR + "x"
					+ COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1)
					+ COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3)
					+ COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5)
					);
		}
		return matcher.appendTail(buffer).toString();
	}
	
	public static String hc(String s) { if (s == null) return null; else return translateHexColorCodes(cc(s)); }

	public static String cc(String s) { return ChatColor.translateAlternateColorCodes('&', s);	}	
	

	public static String dc(int value) {
		String pattern = "###,###,###";
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		return decimalFormat.format(value); 
	}
	
	public static String dc(double value) {
		String pattern = "###,###,###.##";
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		return decimalFormat.format(value); 
	}

	/**
	 * @param commands a list of possible commands
	 * @param Input the players input on the given argument
	 * @return all items on the commands list that start with the given input, most often used for tab completing.
	 */	
	public static List<String> TabCompleter(String Input, List<String> commands) {
		List<String> wordsThatStartWithArg = new ArrayList<>();
		for (String x : commands) if (x.toLowerCase().startsWith(Input.toLowerCase())) wordsThatStartWithArg.add(x);
		return wordsThatStartWithArg;	
	}
	/**
	 * @param input Input from the given argument
	 * @param cmds All available commands
	 * @return all items on the commands list that start with the given input, most often used for tab completing.
	 */	
	public static List<String> TabCompleter(String input, String... cmds) {
		List<String> wordsThatStartWithArg = new ArrayList<>();
		for (String x : cmds) if (x.toLowerCase().startsWith(input.toLowerCase())) wordsThatStartWithArg.add(x); 
		return wordsThatStartWithArg;
	}

	public static String capitaliseFirstLetters(String s) {
		String[] words = s.split(" ");
		String returnable = "";
		for (String x : words) {
			String firstletter = x.substring(0, 1);
			String notfirstletter = x.substring(1, x.length()).toLowerCase();
			returnable = (returnable.length() == 0 ? returnable: returnable+ " ") + firstletter.toUpperCase() + notfirstletter;
		}
		return returnable;
	}
	
	public static ItemStack itemInMainHand(Player p) { return p.getInventory().getItemInMainHand(); }
	public static void send(Player p, String s) { p.sendMessage(u.cc(s)); }
	public static boolean isMaterial(String s) { return Material.matchMaterial(s) != null; }	
	public static Material getMaterial(String s) { return Material.matchMaterial(s); }	
	public static boolean hasInventorySpace(Player p) { return (p.getInventory().firstEmpty() != -1); }	
	public static boolean isPlayer(CommandSender s) { return (s instanceof Player); }	
	public static boolean isPlayer(String name) { return Bukkit.getPlayer(name) != null; }	
	public static Player getPlayer(String s) { return Bukkit.getPlayer(s); }	
	public static String getNicerName(Material m) { return capitaliseFirstLetters(m.toString().toLowerCase().replaceAll("_", " ")); }	
	public static String getNicerName(ItemStack item) { return getNicerName(item.getType()); }	
	public static boolean isItem(ItemStack item) { return (item != null && item.getType() != Material.AIR); }	
	public static String getNicerMaterialNameSingular(String materialName) { return removeFinalS(getNicerMaterialName(materialName)); }
	public static void bc(String s) { Bukkit.getPlayer("MLGPenguin").sendMessage(hc(s)); }

	public static List<String> getAllPlayersNames(){
		List<String> names = new ArrayList<>();
		for (Player p : Bukkit.getOnlinePlayers()) names.add(p.getName());
		return names;
	}

	public static List<Player> getOnlinePlayers(){
		List<Player> players = new ArrayList<>();
		players.addAll(Bukkit.getOnlinePlayers());
		return players;
	}
	
	public static String getNicerMaterialName(String materialName) {
		if (isMaterial(materialName)) {
			return getNicerName(Material.matchMaterial(materialName));
		} else return null;
	}

	public static String removeFinalS(String s) {
		if (s.toLowerCase().endsWith("s")) {
			s = s.substring(0, s.length()-1);
		}
		return s;
	}
	
	public static int getRandomNumberBetween(int lower, int higher) {
		Random rand = new Random();
		return lower + rand.nextInt(higher - lower);
	}

	public static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	public static int getInt(String s) { return Integer.parseInt(s); }
	
	public static boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static String pluralise(int amount, String singularName) { return amount == 1 ? amount + " " + singularName : amount + " " + singularName + "s"; }
	public static String pluralise(long amount, String singularName) { return amount == 1 ? amount + " " + singularName : amount + " " + singularName + "s"; }
	public static Timestamp getCurrentTimestamp() { return Timestamp.from(Instant.now()); }

	

public static int getLargest(List<Integer> ints) {
	TreeSet<Integer> set = new TreeSet<>();
	set.addAll(ints);
	return set.last();
}

public static Long getLargestLong(List<Long> longs) {
	TreeSet<Long> set = new TreeSet<>();
	set.addAll(longs);
	return set.last();
}


	public static boolean testProbability(double chance) {
		return (new Random().nextDouble() <= chance);
	}
	
	public static void applyPotionEffect(LivingEntity target, PotionEffectType type, int strength, int seconds) {
		target.addPotionEffect(new PotionEffect(type, seconds * 20, strength-1));
	}
	public static void applyInfinitePotionEffect(LivingEntity target, PotionEffectType type, int strength) {
		applyPotionEffect(target, type, strength, 255000000);
	}
	
	public static void applyPotionEffectToAll(List<Player> players, PotionEffectType type, int strength, int seconds) {
		for (Player p : players) applyPotionEffect(p, type, strength, seconds);
	}
	
	public static void sendLater(Player p, String message, int seconds) {
		new BukkitRunnable() {
			@Override
			public void run() {
				p.sendMessage(u.cc(message));
			}
		}.runTaskLater(Main.getPlugin(Main.class), 20*seconds);
	}

	public static void removeItemInMainHand(Player p) {
		p.getInventory().getItemInMainHand().setAmount(0);
	}
	
	public static void takeOneFromItemInHand(Player p) {
		ItemStack holding = p.getInventory().getItemInMainHand();
		holding.setAmount(holding.getAmount()-1);
	}
	
	public static void takeOne(ItemStack s) {
		s.setAmount(s.getAmount()-1);
	}
	
	public static List<Player> getNearbyPlayers(Location loc, int range) {
		return (List<Player>) loc.getWorld().getNearbyEntities(loc, range, range, range).stream().filter(e -> e instanceof Player).map(e -> (Player) e).collect(Collectors.toList());
	}
	
	public static List<Player> getNearbyPlayersExcept(Location loc, int range, Player exception) {
		List<Player> all = getNearbyPlayers(loc, range);
		if (all.contains(exception)) all.remove(exception);
		return all;
	}
	
	public static List<Player> getAllOnlinePlayersExcept(Player p) {
		List<Player> all = getOnlinePlayers();
		all.remove(p);
		return all;
	}
	
	public static Player getClosestPlayer(Player p, List<Player> players) {
		Player closest = null;
		for (Player en : players)
			if (closest == null || p.getLocation().distance(en.getLocation()) < p.getLocation().distance(closest.getLocation())) closest = (Player) en;	
		return closest;
	}
	
	public static void addTag(Entity entity, String keyname) {
		entity.getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class), keyname), PersistentDataType.INTEGER, 1);
	}
	
	public static boolean hasTag(Entity Entity, String keyname) {
		return Entity.getPersistentDataContainer().has(new NamespacedKey(Main.getPlugin(Main.class), keyname), PersistentDataType.INTEGER);
	}
	
	public static String getHeldLocname(Player p) {
		try {
			ItemStack held = p.getInventory().getItemInMainHand();
			return held.getItemMeta().getLocalizedName();
		} catch (NullPointerException e) { return ""; }
	}
	
	public static boolean isHoldingBonusItem(Player p) {
		return getHeldLocname(p).equals("bonus");
	}
	
	public static boolean isHoldingBonusItem(ProjectileSource p) {
		return isHoldingBonusItem((Player)p);
	}
	
	public static ItemStack getHead(String texture) {
		ItemStack head = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta s  = (SkullMeta) head.getItemMeta();
		PlayerProfile profile = Bukkit.getServer().createPlayerProfile(UUID.randomUUID());
		PlayerTextures t = profile.getTextures();
		try {
			t.setSkin(new URL("http://textures.minecraft.net/texture/" + texture));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		profile.setTextures(t);
		s.setOwnerProfile(profile);
		head.setItemMeta(s);
		return head;
	}
	
	public static long getTimeSinceLastInteractms(UUID uuid) {
		if (Main.lastInteract.containsKey(uuid)) {
			return Duration.between(Main.lastInteract.get(uuid), Instant.now()).toMillis();
		} else return Integer.MAX_VALUE;
	}
	
	public static boolean canInteract(UUID uuid) {
		return getTimeSinceLastInteractms(uuid) > 300;
	}
	
	public static Location getHitLocation(ProjectileHitEvent e) {
		boolean hitEntity = e.getHitEntity() != null;
		return hitEntity ? e.getHitEntity().getLocation() : e.getHitBlock().getLocation();
	}
	
	public static void makeExplosionEffect(Location loc, int amplitude, int range) {
		World w = loc.getWorld();
		w.playSound(loc, Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
		w.spawnParticle(Particle.EXPLOSION_LARGE, loc, amplitude, range, range, range);	
	}
	
	public static void DamageWithinRange(Location loc, double damage, Player source, double range, CustomCause Exception) {
		List<Player> players = loc.getWorld().getNearbyEntities(loc, range, range, range).stream().filter(e -> e instanceof Player).map(e -> (Player) e).collect(Collectors.toList());
		for (Player p : players) {
			SPlayer s = Main.getPlayerWithoutCreating(p.getUniqueId());
			if (s != null && shouldHurt(s.getKit(), Exception)) {
				p.damage(damage);
				s.setLastDamagedBy(source);				
			}
		}
	}
	
	public static boolean shouldHurt(Kit kit, CustomCause cause) {
		switch (cause) {
		case NONE: return true;
		case EXPLOSION: return !(kit instanceof ExplosionResistant);
		case SNOW: return (kit.getType() != kitType.SNOWGOLEM);
		default: return true;
		}
	}
	
	public static void stripPlayer(Player p) {
		p.setGameMode(GameMode.ADVENTURE);
		p.getInventory().clear();
		for (PotionEffect e : p.getActivePotionEffects()) p.removePotionEffect(e.getType());
	}
	
	public static void immuneToWither(EntityDamageEvent e) {
		DamageCause c = e.getCause();
		if (c == DamageCause.WITHER) {
			e.setCancelled(true);
			((Player)e.getEntity()).removePotionEffect(PotionEffectType.WITHER);
		}
	}
	
	public static void openLater(Player p, Inventory inv) {
		new BukkitRunnable() {			
			@Override
			public void run() {
				p.openInventory(inv);
			}
		}.runTaskLater(Main.get(), 1);
	}
	
	public static void openLaterandAdd(Player p, GUI gui) {
		new BukkitRunnable() {			
			@Override
			public void run() {
				p.openInventory(gui.getInventory(p));
				Main.viewingGUI.put(p.getUniqueId(), gui);				
			}
		}.runTaskLater(Main.get(), 1);
	}
	
	public static int getHalfHealth(Player p) { 
		return (int) Math.round(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()/2);
	}
	
	public static boolean isBelowHealth(Player p, int health) {
		return p.getHealth() < health;
	}
	
	public static void reapplyIfBelowHealthTimer(int health, Player p, PotionEffectType type, int strength) {
		new BukkitRunnable() {			
			@Override
			public void run() {
				SPlayer s = Main.getPlayerWithoutCreating(p.getUniqueId());
				if (s != null) {
					if (isBelowHealth(p, health)) {
						u.applyPotionEffect(p, type, strength, 2);
					} else cancel();
				} else cancel();
			}
		}.runTaskTimer(Main.get(), 0, 39);
	}
	
	public static void reapplyIfBelowHalfHealthTimer(Player p, PotionEffectType type, int strength) {
		reapplyIfBelowHealthTimer(getHalfHealth(p), p, type, strength);
	}
	
	public static List<PotionEffectType> getNegativeEffects() {
		return Arrays.asList(
				PotionEffectType.BLINDNESS,
				PotionEffectType.WITHER,
				PotionEffectType.CONFUSION,
				PotionEffectType.POISON,
				PotionEffectType.SLOW,
				PotionEffectType.WEAKNESS
				);
		
	}
	
	
	
	
	
	
	
	public enum CustomCause {
		NONE, EXPLOSION, SNOW;
	}
	
	
}



