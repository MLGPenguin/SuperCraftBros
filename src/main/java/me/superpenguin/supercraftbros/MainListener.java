package me.superpenguin.supercraftbros;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import me.superpenguin.supercraftbros.customevents.DropBelowHalfHealthEvent;
import me.superpenguin.supercraftbros.guis.SelectKit;
import me.superpenguin.supercraftbros.utils.ScoreboardUtil;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;

import me.superpenguin.supercraftbros.objects.GUI;
import me.superpenguin.supercraftbros.objects.Game;
import me.superpenguin.supercraftbros.objects.IPlayer;
import me.superpenguin.supercraftbros.objects.Kit;
import me.superpenguin.supercraftbros.objects.Parties;
import me.superpenguin.supercraftbros.objects.SPlayer;
import me.superpenguin.supercraftbros.tags.DoubleFireDamage;
import me.superpenguin.supercraftbros.tags.EnderAcidResistant;
import me.superpenguin.supercraftbros.tags.ExplosionResistant;
import me.superpenguin.supercraftbros.tags.GemMultiplier;
import me.superpenguin.supercraftbros.tags.WitherResistant;

public class MainListener implements Listener{
	
	private SuperCraftBros plugin;
	
	public MainListener(SuperCraftBros plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		IPlayer i = new IPlayer(p.getUniqueId());
		SuperCraftBros.setPersistentPlayer(p.getUniqueId(), i);
		// teleport em to spawn
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		UUID uuid = e.getPlayer().getUniqueId();
		Game g = SuperCraftBros.getGame(e.getPlayer());
		if (g != null) {
			g.eliminatePlayer(e.getPlayer());
			g.removePlayer(e.getPlayer());
		}
		SuperCraftBros.getPersistentPlayer(uuid).save();
		SuperCraftBros.removePersistentPlayer(uuid);
		if (SuperCraftBros.playerData.containsKey(uuid)) SuperCraftBros.playerData.remove(uuid);
		if (Parties.isInParty(uuid)) Parties.getParty(uuid).removePlayer(uuid);
		e.getPlayer().getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
	}
	
	@EventHandler
	public void nullifyXP(PlayerExpChangeEvent e) {
		e.setAmount(0);
	}
	
	@EventHandler
	public void consumePotion(PlayerItemConsumeEvent e) {
		if (e.getItem().getType() == Material.POTION) {
			new BukkitRunnable() {				
				@Override
				public void run() {
					e.getPlayer().getInventory().removeItem(new ItemStack(Material.GLASS_BOTTLE));
				}
			}.runTaskLater(SuperCraftBros.get(), 1);
		}
	}
	
	@EventHandler
	public void InventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		UUID uuid = p.getUniqueId();
		if (SuperCraftBros.viewingGUI.containsKey(uuid)) {
			GUI gui = SuperCraftBros.viewingGUI.get(uuid);
			if (gui.getType() != GUI.GUIType.CRAFTING) e.setCancelled(true);
			if (e.getView().getTopInventory().equals(e.getClickedInventory())) {
				ItemStack clicked = e.getCurrentItem();
				gui.clicked(e);
				if (clicked == null || clicked.getType() == Material.AIR) return;
				boolean hasLocname = clicked.getItemMeta().hasLocalizedName();				
				if (hasLocname) {
					String locname = clicked.getItemMeta().getLocalizedName();
					gui.clicked(p, locname);					
				}
			}
		}
	}
	
	
	@EventHandler
	public void InventoryCloseHandler(InventoryCloseEvent e) {
		UUID uuid = e.getPlayer().getUniqueId();
		if (SuperCraftBros.viewingGUI.containsKey(uuid)) SuperCraftBros.viewingGUI.remove(uuid);
	}
	
	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
			Player damager = (Player) e.getDamager();
			Player damaged = (Player) e.getEntity();
			SPlayer Sdamager = SuperCraftBros.getPlayer(damager.getUniqueId());
			SPlayer Sdamaged = SuperCraftBros.getPlayer(damaged.getUniqueId());
			Sdamaged.setLastDamagedBy(damager);			
			/*
			 * Can for example add Debugger around this and then use getKit().getName() + " Damaged By Player" as the debug name.
			 */
			if (Sdamaged.hasKit()) Sdamaged.getKit().DamagedByPlayer(e);			
			if (Sdamager.hasKit()) {
				Sdamager.getKit().DamagedPlayer(e);
				String locname;
				Kit kit;
				try {
					locname = damager.getInventory().getItemInMainHand().getItemMeta().getLocalizedName();
					kit = Sdamager.getKit();
					if (locname == null) return;
					if (locname.equals("weapon1")) kit.DamageDoneWithWeapon1(e);					
					if (locname.equals("bonus")) kit.DamageDoneWithBonusItem(e);
					Sdamager.addDamageDone((int)Math.round(e.getFinalDamage()));
				} catch (NullPointerException ex) { return; }				
			}
			
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		SPlayer sp = SuperCraftBros.getPlayer(p.getUniqueId());
		if (u.canInteract(p.getUniqueId())) {
			if (sp.hasKit()) {				
				Kit kit = sp.getKit();
				String locname;
				Action a = e.getAction();
				try {
					locname = p.getInventory().getItemInMainHand().getItemMeta().getLocalizedName();
					if (locname.equals("bonus") || locname.equals("bonus2")) {
						if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) kit.RightClickWithBonusItem(e);
					}
					else if (locname.equalsIgnoreCase("weapon1")) {
						if (a == Action.RIGHT_CLICK_BLOCK || a == Action.RIGHT_CLICK_AIR) kit.RightClickWithWeapon1(e);
					} 
					if (!locname.equalsIgnoreCase("selectkit")) SuperCraftBros.lastInteract.put(p.getUniqueId(), Instant.now());
				} catch (NullPointerException ex) { return; }
			}
		}
	}
	
	@EventHandler
	public void kitSelectListener(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack held = e.getItem();
		if (held != null) {
			if (held.getType() == Material.COMPASS && held.getItemMeta().hasLocalizedName()) {
				if (held.getItemMeta().getLocalizedName().equals("selectkit")) {
					if (u.canInteract(p.getUniqueId())) {
						if (!SuperCraftBros.isInGame(p.getUniqueId())) {
							held.setAmount(0);
							return;
						}
						u.openLaterandAdd(p, new SelectKit());
						SuperCraftBros.lastInteract.put(p.getUniqueId(), Instant.now());
						return;
					}
				}
			}
		}		
	}
	

	@EventHandler
	public void onHit(ProjectileHitEvent e) {
		ProjectileSource launcher = e.getEntity().getShooter();
		Entity Hit = e.getHitEntity();
		if (launcher instanceof Player) {
			SPlayer sp = SuperCraftBros.getPlayer(((Player)launcher).getUniqueId());
			if (sp.hasKit()) {
				sp.getKit().HandleProjectileHit(e);				
			}
			if (Hit != null && Hit instanceof Player) {
				SPlayer hit = SuperCraftBros.getPlayer(((Player)Hit).getUniqueId());
				hit.setLastDamagedBy((Player) launcher);
			}
		}
	}
	
	@EventHandler
	public void onLaunch(ProjectileLaunchEvent e) {
		ProjectileSource launcher = e.getEntity().getShooter();
		if (launcher instanceof Player) {
			SPlayer sp = SuperCraftBros.getPlayer(((Player)launcher).getUniqueId());
			if (sp.hasKit()) {
				sp.getKit().HandleProjectileThrow(e);
			}
		}
	}
	
	@EventHandler
	public void onDie(PlayerDeathEvent e) {		
		Player p = e.getEntity();
		SPlayer killed = SuperCraftBros.getPlayer(p.getUniqueId());
		SPlayer killer = null;
		boolean self = false;
		if (killed.getLastDamagedBy() != null) {
			killer = SuperCraftBros.getPlayer(killed.getLastDamagedBy().getUniqueId());
			self = killer.getPlayer().getName().equals(p.getName());
		}
		Game g = SuperCraftBros.getGame(p);
		if (g != null) {
			if (g.getState() == Game.gamestate.ONGOING) {
				if (killed.hasKit()) killed.getKit().OnKitKilled(e);		
				killed.takeLife();
				killed.addDeath();
				killed.setLastDamagedBy(null);
				ScoreboardUtil.update(p, p.getName(), "&b" + killed.getLives());
				if (killer != null ) {
					if (!self) {
						killer.addKill();
						double gems = 1;
						if (killer.getKit() instanceof GemMultiplier) gems = (1 * ((GemMultiplier)killer.getKit()).getGemMultiplier());
						killer.addGems(gems);
						killer.sendMessage("&a+" + gems + " gem" + (gems > 1 ? "s" : ""));
					} else {
						killed.addSuicide();
					}					
				}
				if (killed.getLives() == 0) {
					g.eliminatePlayer(p);
				} else {
					if (killer != null) {
						if (!self) {
							g.broadcastMessage("&d&l" + p.getName() + " &cHas been killed by &d&l" + killer.getPlayer().getName() + "&c. &cThey have " + killed.getLives() + " lives remaining.");
						} else g.broadcastMessage("&d&l" + p.getName() + " &ckilled themselves, real classy bud.");
					} else g.broadcastMessage("&d&l" + p.getName() + " died of... Natural causes...");
				}
			} else {
				killed.setLastDamagedBy(null);
			}
		}		
	}

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			SPlayer sp = SuperCraftBros.getPlayer(((Player)e.getEntity()).getUniqueId());
			if (sp.hasKit()) {
				sp.getKit().takenDamage(e);
			}
		}
	}
	
	@EventHandler
	public void onFireball(EntityExplodeEvent e) {
		if (e.getEntity() instanceof Fireball) {
			e.blockList().clear();
		}
	}
	
	//TODO
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		SPlayer sp = SuperCraftBros.getPlayer(p.getUniqueId());
		Game g = SuperCraftBros.getGame(p);
		if (g != null) {
			if (g.getState() == Game.gamestate.ONGOING) {
				e.setRespawnLocation(g.getSafeSpawn());
				new BukkitRunnable() {			
					@Override
					public void run() {
						if (sp.hasKit()) sp.applyKit();
					}
				}.runTaskLater(plugin, 1);
			} else {
				e.setRespawnLocation(g.getMap().getLobbySpawn());
			}
		}


	}
	
	@EventHandler
	public void onSpawnMob(CreatureSpawnEvent e) {
		if (e.getSpawnReason() == SpawnReason.SPAWNER_EGG) {
			if (e.getEntity().getType() == EntityType.PHANTOM) {
				for (Entity en  : e.getEntity().getNearbyEntities(20, 20, 20).stream().filter(x -> x.getType() == EntityType.PLAYER).collect(Collectors.toList())) {
					SPlayer sp = SuperCraftBros.getPlayer(((Player)en).getUniqueId());
					if (!sp.getKit().getClass().getName().equals("Phantom")) {
						Phantom p = (Phantom) e.getEntity();
						p.setTarget((Player)en);
						break;
					}
				}				
			}
		}
	}
	
	@EventHandler
	public void onLoseHunger(FoodLevelChangeEvent e) {
		if (e.getEntity() instanceof Player) {
			Player hungry = (Player) e.getEntity();
			SPlayer sp = SuperCraftBros.getPlayer(hungry.getUniqueId());
			if (sp.hasKit()) e.setCancelled(true);
		}
	}
	
	
	@EventHandler
	public void onWeaponPlace(BlockPlaceEvent e) {
		try {
			String locname = e.getItemInHand().getItemMeta().getLocalizedName();
			if (locname.equalsIgnoreCase("weapon1") || locname.equalsIgnoreCase("weapon2") || locname.equalsIgnoreCase("bonus") || locname.equalsIgnoreCase("bonus2")) 
				e.setCancelled(true);
		} catch (NullPointerException ex) {}
	}
	
	@EventHandler	
	public void TnTExplode(EntityExplodeEvent e) {
		if (e.getEntityType() == EntityType.PRIMED_TNT) {
			e.blockList().clear();
		}
	}
	
	@EventHandler
	public void handlerBowFire(EntityShootBowEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			SPlayer sp = SuperCraftBros.getPlayer(p.getUniqueId());
			if (sp.hasKit()) sp.getKit().shootBow(e);
		}
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		ItemStack item = e.getItemDrop().getItemStack();
		if (item.hasItemMeta() && item.getItemMeta().hasLocalizedName()) {
			String locname = item.getItemMeta().getLocalizedName();
			if (locname.equalsIgnoreCase("weapon1") || locname.equalsIgnoreCase("weapon2") || locname.equalsIgnoreCase("bonus")) e.setCancelled(true);
		}
	}
	
	
	@EventHandler
	public void onInteract(PlayerInteractAtEntityEvent e) {
		if (e.getRightClicked() instanceof Player) {
			Player hitting = e.getPlayer();
			SPlayer s = SuperCraftBros.getPlayer(hitting.getUniqueId());
			ItemStack weapon = hitting.getInventory().getItemInMainHand();
			try {
				String locname = weapon.getItemMeta().getLocalizedName();
				if (locname.equalsIgnoreCase("bonus")) {
					s.getKit().RightClickPlayerWithBonusItem(e);
				}
			} catch (NullPointerException ex) {}
			
		}
	}

	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (SuperCraftBros.frozen.contains(e.getPlayer().getUniqueId())) {
			Location from = e.getFrom();
			Location to =  e.getTo();
			if (from.getX() != to.getX()  || from.getZ() != to.getZ()) {
				e.setTo(e.getFrom());
			}
		}		
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onTakeDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			if (!e.isCancelled()) {
				Player p = (Player) e.getEntity();
				double max = p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
				double half = max/2;
				double oldhealth = p.getHealth();
				double damage = e.getFinalDamage();
				double newHealth = oldhealth-damage;			
				if (oldhealth > half && newHealth < half) {
					DropBelowHalfHealthEvent event = new DropBelowHalfHealthEvent(p, oldhealth, newHealth);
					Bukkit.getPluginManager().callEvent(event);
				}
			}
		}
	}
	
	@EventHandler
	public void onHalfHealth(DropBelowHalfHealthEvent e) {
		UUID uuid = e.getPlayer().getUniqueId();
		SPlayer s =  SuperCraftBros.getPlayerWithoutCreating(uuid);
		try {
			if (s != null) s.getKit().dropBelowHalfHealth(e);		
		} catch (NullPointerException ex) { return; }
	}
	
	
	@EventHandler
	public void explode(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		Player p = (Player) e.getEntity();
		SPlayer s = SuperCraftBros.getPlayer(p.getUniqueId());
		if (e.getDamager().getType() == EntityType.PRIMED_TNT) {
			TNTPrimed t = (TNTPrimed) e.getDamager();
			if (t.getPersistentDataContainer().has(new NamespacedKey(SuperCraftBros.get(), "Creeper"), PersistentDataType.STRING)) {
				String name = t.getPersistentDataContainer().get(new NamespacedKey(SuperCraftBros.get(), "Creeper"), PersistentDataType.STRING);
				Player creeper = Bukkit.getPlayerExact(name);
				s.setLastDamagedBy(creeper);
			}			
		} else if (e.getDamager().getType() == EntityType.AREA_EFFECT_CLOUD) {
			if (s.hasKit() && s.getKit() instanceof EnderAcidResistant) {
				AreaEffectCloud a = (AreaEffectCloud) e.getDamager();
				List<PotionEffect> effects = a.getCustomEffects();
				for (PotionEffect ef : effects) {
					if (ef.getType().equals(PotionEffectType.HARM) && ef.getAmplifier() == 1) {
						e.setCancelled(true);
						return ;
					}
				}			
			}
		}
	}
	
	@EventHandler
	public void preventHatching(PlayerEggThrowEvent e) {
		Player p = e.getPlayer();
		SPlayer s = SuperCraftBros.getPlayerWithoutCreating(p.getUniqueId());
		if (s != null && s.hasKit()) {
			if (s.getKit().getType().equals(SuperCraftBros.kitType.CHICKEN)) e.setHatching(false);
		}
	}
	
	@EventHandler
	 public void preventFireballFire(BlockIgniteEvent e) {
		IgniteCause i = e.getCause();
	    if (i == IgniteCause.FIREBALL) e.setCancelled(true); 
	}
	
	@EventHandler
	public void preventDamage(EntityDamageEvent e) {
		DamageCause c = e.getCause();
		if (e.getEntityType() != EntityType.PLAYER) return;
		SPlayer s = SuperCraftBros.getPlayerWithoutCreating(((Player)e.getEntity()).getUniqueId());
		if (s != null && s.hasKit()) {			
			Kit type = s.getKit();
			switch (c) {
			case VOID:
				e.setDamage(1000);
			case WITHER : 
				if (type instanceof WitherResistant) u.immuneToWither(e);
				break;
			case BLOCK_EXPLOSION :
			case ENTITY_EXPLOSION : 
				if (type instanceof ExplosionResistant) e.setCancelled(true);
				break;
			case FIRE:
			case FIRE_TICK:
				if (type instanceof DoubleFireDamage) e.setDamage(e.getFinalDamage()*2);
				if (e.getEntity().getFireTicks() > 50) e.getEntity().setFireTicks(50);
				break;
			case FLY_INTO_WALL:
				e.setCancelled(true);
			default:
				break;
			}
		}
	}
	
	
	
}
