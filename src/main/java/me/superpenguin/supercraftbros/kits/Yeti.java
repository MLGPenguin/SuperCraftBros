package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.Main;
import me.superpenguin.supercraftbros.objects.Kit;
import me.superpenguin.supercraftbros.objects.SPlayer;
import me.superpenguin.supercraftbros.tags.DoubleFireDamage;
import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.superpenguin.supercraftbros.objects.Armour;

public class Yeti extends Kit implements DoubleFireDamage {
	
	public Yeti() {
		super(	Main.kitType.YETI,
				"Yeti",
				new Armour(u.getHead("4a089fb5c736f7f288a61c6e81601b5b13c2b945006ee9bab62306cb19df7d07"))
				);
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.SNOW_BLOCK).setLocname("weapon1").setName("&bSnow Cone").addEnchant(Enchantment.DAMAGE_ALL, 2).build(),
				new MIB(Material.ICE).setLocname("bonus").setName("&BCracked Ice").build(),
				new MIB(Material.BLUE_ICE).setLocname("bonus2").setName("&1Freeze").build()
				);		
	}
	
	@Override
	public void DamageDoneWithWeapon1(EntityDamageByEntityEvent e) {
		Player hit = (Player) e.getEntity();
		SPlayer s = Main.getPlayer(hit.getUniqueId());
				if (!s.getKit().toString().equalsIgnoreCase("Yeti")) hit.setFreezeTicks(140  + (20 * 5));
	}
	
	@Override
	public void DamageDoneWithBonusItem(EntityDamageByEntityEvent e) {
		ItemStack ice = ((Player)e.getDamager()).getInventory().getItemInMainHand();
		ice.setAmount(ice.getAmount()-1);
		SPlayer hurt = Main.getPlayer(((Player)e.getEntity()).getUniqueId());
		hurt.setLastDamagedBy((Player)e.getDamager());
		e.setDamage(10);
	}
	
	@Override
	public void RightClickWithBonusItem(PlayerInteractEvent e) {
		if (e.getItem().getItemMeta().getLocalizedName().equalsIgnoreCase("bonus2")) {
			for (Player p : Main.getGame(e.getPlayer()).getAlivePlayersWithout(e.getPlayer())) {
				Main.frozen.add(p.getUniqueId());
				p.setFreezeTicks(180);
				p.sendMessage(u.cc("&cYou have been frozen by a Yeti!"));
				u.sendLater(p, "&aYou have been unfrozen, run!", 3);
			}
			new BukkitRunnable() {				
				@Override
				public void run() {
					Main.frozen.clear();
				}
			}.runTaskLater(Main.getPlugin(Main.class), 60);
			e.getPlayer().getInventory().getItemInMainHand().setAmount(0);
		}
	}
	
	@Override
	public void takenDamage(EntityDamageEvent e) {
		DamageCause cause = e.getCause();
		Player p = (Player) e.getEntity();
		if (cause == DamageCause.FREEZE) {
			e.setCancelled(true);
			p.setFreezeTicks(0);
			return;
		}
	}

}