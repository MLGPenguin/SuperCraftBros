package me.Penguin.SuperCraftBros;


import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import me.Penguin.SuperCraftBros.kits.*;
import me.Penguin.SuperCraftBros.objects.GUI;
import me.Penguin.SuperCraftBros.objects.Game;
import me.Penguin.SuperCraftBros.objects.IPlayer;
import me.Penguin.SuperCraftBros.objects.Kit;
import me.Penguin.SuperCraftBros.objects.KitSorter;
import me.Penguin.SuperCraftBros.objects.KitSorter.SortingOrder;
import me.Penguin.SuperCraftBros.objects.Parties;
import me.Penguin.SuperCraftBros.objects.SPlayer;
import me.Penguin.SuperCraftBros.utils.MIB;
import me.Penguin.SuperCraftBros.utils.button;
import me.Penguin.SuperCraftBros.utils.m;
import me.Penguin.SuperCraftBros.utils.u;

public class Main extends JavaPlugin {
	
	public static Set<UUID> frozen;
	public static HashMap<UUID, GUI> viewingGUI;
	public static HashMap<Integer, Game> runningGames;
	public static HashMap<kitType, Kit> ClassSelector;
	
	public static List<Integer> panes = Arrays.asList(0,1,2,3,4,5,6,7,8,46,47,48,49,50,51,52);
	public static HashMap<UUID, IPlayer> persistentData;

	
	// remove people from list when their game ends.
	public static HashMap<UUID, SPlayer> playerData;
	// clear these on game end.
	public static HashMap<UUID, Location> waypointTable;
	public static HashMap<UUID, Instant> lastInteract;

	@Override
	public void onDisable() {
		for (Game g : runningGames.values()) g.endGameWithoutRemoving();
		runningGames.clear();
		Bukkit.getLogger().log(Level.INFO, "[SuperCraftBros] Disabled successfully");

	}
	
	
	public void onEnable() {
		
		Instant start = Instant.now();
				
		
		/*
		 * are assists necessary?
		 * Remove wolf being hit noises? by cancelling and custom damaging/
		 * 
		 * 
		 * pillager -> 75% dmg
		 * ghast register kills --> need to test how fireballs hit reg. (Can set the shooter of them though)
		 * mage needs to claim own kills and be immune to their own spells
		 * spiderkings spiders attack him
		 * add stat for "natural causes"
		 * nerf IG damage by changing axe attack dmg
		 * inviting and accepting party invites returns wrong identifiers (names)
		 * leaving party as owner throws error
		 * vampire could use smoke effect
		 * stops mobs from dropping xp

		 * 
		 * TIMER AND DEATHMATCH UPDATE
		 * CLASS SELECTION DESCRIPTION UPDATE
		 * 
		 * TO TEST:
		 * ?self destruct not killing herobrine
		 * ?half health still preregistering
		 * cactus armour
		 * firefighter less dmg in water
		 * satan death
		 * vex strength OP
		 * fire fighter balls do 2 hearts / hit
		 * 
		 * WHAT I'VE DONE: (FOR UPDATE)
		 * Added XP collection and levels
		 * Nullified XP collection
		 * Synchronised XP bar to level
		 * Void insta kills
		 * Receive message on how many gems you've earnt in a game
		 * Secret Crafting Items..? O.o
		 * 
		 */
		
		m.setup();
		
		new MainListener(this);
		new MainCmd(this);		
		
		//getConfig().options().copyDefaults();
		//saveDefaultConfig();
		lastInteract = new HashMap<>();
		waypointTable = new HashMap<>();
		playerData = new HashMap<>();
		frozen = new HashSet<>();
		runningGames = new HashMap<>();
		ClassSelector = new HashMap<>();
		persistentData = new HashMap<>();
		viewingGUI = new HashMap<>();
		
		Parties.initialise();
		setupKits();
		new File("plugins/SuperCraftBros/Players").mkdirs();
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			IPlayer i = new IPlayer(p.getUniqueId());
			setPersistentPlayer(p.getUniqueId(), i);
		}
		
		Instant end = Instant.now();
		
		Bukkit.getLogger().log(Level.INFO, "[SuperCraftBros] Enabled! [" + Duration.between(start, end).toMillis() + "ms]");
		
	}
	
	public static IPlayer getPersistentPlayer(UUID uuid) {
		return persistentData.get(uuid);
	}
	
	public static void setPersistentPlayer(UUID uuid, IPlayer player) {
		persistentData.put(uuid, player);
		// TODO test if this is required to save the data that has been changed;
	}
	
	public static void removePersistentPlayer(UUID uuid) {
		persistentData.remove(uuid);
	}
	
	
	public static int getNewGameID() {
		int id = -1;
		int i = 0;
		while (id == -1) {
			if (runningGames.keySet().contains(i)) continue;
			else id = i;
		}
		return i;
	}
	
	
	public static SPlayer getPlayer(UUID uuid) {
		return hasPlayer(uuid) ? Main.playerData.get(uuid) : new SPlayer(uuid);
	}
	
	public static SPlayer getPlayerWithoutCreating(UUID uuid) {
		if (hasPlayer(uuid)) return getPlayer(uuid);
		else return null;
	}
	
	public static boolean hasPlayer(UUID uuid) {
		return playerData.containsKey(uuid);
	}
	
	public static boolean isInGame(UUID uuid) {
		return (getGame(Bukkit.getPlayer(uuid)) != null );
	}
	
	public static Game getGame(Player p) {
		for (Game g : runningGames.values()) {
			if (g.containsPlayer(p)) return g;
		}
		return null;
	}
	
	public static Game getGame(int id) {
		if (runningGames.containsKey(id)) return runningGames.get(id);
		else return null;
	}
	
	public static Main get() { return Main.getPlugin(Main.class); }
	
	public enum kitType {
		COW, BAT, BLAZE, CHICKEN, PANDA, GINGERBREADMAN, BEE, OCELOT, RABBIT, IRONGOLEM, ENDERDRAGON, PUFFERFISH, ZOMBIEPIGMAN, HEROBRINE, STEVE, GHAST, ENDERMAN,
		SPIDER, SQUID, COOKIEMONSTER, PHANTOM, SKELETON, CREEPER, PILLAGER, WITHER, YETI, NINJA, ENGINEER, MAGMACUBE, SNOWGOLEM, SKELETONHORSE, GOBLIN, VILLAGER,
		POLARBEAR, NOTCH, WOLF, ANGEL, TURTLE, EXPERIENCED, FIREFLY, DEMON, ENDERMITE, WITHERSKELETON, MAGICMAGE, BLOCKER, CAVESPIDER, HORSE, GIANT, 
		SPIDERKING, CHARGEDCREEPER, WARRIOR, INFERNOMAGE, WARDEN, POISONMAGE, PIXIE, HUSK, REDSTONEGOLEM, ALEX, CAT, CACTUS, VEX, FIREFIGHTER, VAMPIRE, SATAN;
	}
	
	public static int getPages() { return (int) Math.ceil((double)ClassSelector.values().size()/36); }	
	
	public static Inventory getKitInventory(int page) { 
		Inventory inv = Bukkit.createInventory(null, 54, u.cc("&cSelect Kit Page " + page));	
		
		List<Kit> kits = new ArrayList<>();
		List<kitType> t = new KitSorter(new ArrayList<>(ClassSelector.keySet()), SortingOrder.RARITY).getSorted();
		for (int i = 0 ; i < t.size() ; i++) kits.add(ClassSelector.get(t.get(i)));
		
		
		int slot = 9;
		int size = kits.size();
		ItemStack pane = button.PANE.item;
		for (int i : panes) inv.setItem(i, pane);
		for (int i = (page-1) * 36 ; (i < page * 36 && i < size) ; i++) {
			inv.setItem(slot, kits.get(i).getIcon());
			slot++;
		}
		inv.setItem(45, button.PREVIOUS.item);
		inv.setItem(53, button.NEXT.item);
		inv.setItem(49, new MIB(Material.ACACIA_DOOR).setName("&bSorting Order").setLocname("sort").addLores("&bAlphabetical").build());
		
		return inv;
	}
	
	/**
	 * for giving players access to multiple kits in one chunk
	 * @author baww1
	 *
	 */
	public enum pack {
		DEFAULT(Arrays.asList(kitType.STEVE, kitType.RABBIT, kitType.SPIDER, kitType.SKELETON, kitType.SQUID, kitType.ALEX, kitType.HUSK)),
		GEM(Arrays.asList(kitType.NOTCH, kitType.WARRIOR, kitType.BLOCKER, kitType.COW, kitType.BLAZE, kitType.WITHERSKELETON, kitType.BEE, 
				kitType.WOLF, kitType.INFERNOMAGE, kitType.GHAST, kitType.HORSE, kitType.OCELOT, kitType.POLARBEAR, kitType.MAGICMAGE,
				kitType.ENGINEER, kitType.FIREFLY, kitType.ENDERMAN, kitType.VILLAGER, kitType.ENDERDRAGON)),
		RANK(Arrays.asList(kitType.PHANTOM, kitType.TURTLE, kitType.PUFFERFISH, kitType.COOKIEMONSTER, kitType.PANDA, kitType.NINJA,
				kitType.PILLAGER)),
		ACHIEVEMENT(Arrays.asList(kitType.MAGMACUBE, kitType.CHICKEN, kitType.ZOMBIEPIGMAN, kitType.ENDERMITE, kitType.GOBLIN));
		
		List<kitType> kits;
		
		pack(List<kitType> kits){
			this.kits = kits;
		}
		
		public List<kitType> getKits() { return kits; }
	}
	
	private void setupKits() {
		new Cow();
		new Engineer();
		new Blaze();
		new Chicken();
		new Panda();
		new GingerBreadMan();
		new Bee();
		new Ocelot();
		new Rabbit();
		new IronGolem();
		new EnderDragon();
		new Pufferfish();
		new ZombiePigman();
		new Herobrine();
		new Steve();
		new Ghast();
		new Enderman();
		new Yeti();
		new Spider();
		new Squid();
		new CookieMonster();
		new Phantom();
		new Skeleton();
		new Creeper();
		new Pillager();
		new Wither();
		new Ninja();
		new MagmaCube();
		new SnowGolem();
		new SkeletonHorse();
		new Goblin();
		new Villager();
		new PolarBear();
		new Notch();
		new Wolf();
		new Angel();
		new Turtle();
		new Experienced();
		new Firefly();
		new Demon();
		new Endermite();
		new WitherSkeleton();
		new MagicMage();
		new Blocker();
		new CaveSpider();
		new Horse();
		new Giant();
		new SpiderKing();
		new ChargedCreeper();
		new Warrior();
		new InfernoMage();
		new Warden();
		new PoisonMage();
		new Pixie();
		new Husk();
		new RedstoneGolem();
		new Alex();
		new Cat();
		new Cactus();
		new Vex();
		new FireFighter();
		new Vampire();
		new Satan();
	}
	

}
