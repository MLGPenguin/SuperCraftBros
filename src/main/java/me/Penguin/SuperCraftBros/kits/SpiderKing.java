package me.Penguin.SuperCraftBros.kits;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.Penguin.SuperCraftBros.Main;
import me.Penguin.SuperCraftBros.Main.kitType;
import me.Penguin.SuperCraftBros.objects.Armour;
import me.Penguin.SuperCraftBros.objects.Game;
import me.Penguin.SuperCraftBros.objects.Kit;
import me.Penguin.SuperCraftBros.objects.SPlayer;
import me.Penguin.SuperCraftBros.utils.MIB;
import me.Penguin.SuperCraftBros.utils.u;

public class SpiderKing extends Kit {

	public SpiderKing() {
		super(	kitType.SPIDERKING,
				"Spider King",
				new Armour(u.getHead("8300986ed0a04ea79904f6ae53f49ed3a0ff5b1df62bba622ecbd3777f156df8"))
				);
	}
	
	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.SPIDER_EYE).addEnchant(Enchantment.DAMAGE_ALL, 2).setLocname("weapon1").setName("&bEYEBALL").build(),
				new MIB(Material.COBWEB, 5).setLocname("bonus").setName("&bSticky..").build(),
				new MIB(Material.FERMENTED_SPIDER_EYE).setLocname("bonus2").setName("&6Swarm").build()
				);
	}
	
	@Override
	public void DamageDoneWithWeapon1(EntityDamageByEntityEvent e) {
		u.applyPotionEffect((Player)e.getEntity(), PotionEffectType.POISON, 2, 1);
	}
	
	@Override
	public void RightClickWithBonusItem(PlayerInteractEvent e) {
		ItemStack held = e.getPlayer().getInventory().getItemInMainHand();	
		SPlayer sp = Main.getPlayer(e.getPlayer().getUniqueId());
		if (held.getItemMeta().getLocalizedName().equals("bonus")) {
			e.setCancelled(true);
			if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				Block newPlace = e.getClickedBlock().getRelative(e.getBlockFace());
				if (newPlace.getType() == Material.AIR) {
					newPlace.setType(Material.COBWEB);
					sp.addBlockToReplaceList(newPlace);
					u.takeOneFromItemInHand(e.getPlayer());
				}				
			}
		} else if (held.getItemMeta().getLocalizedName().equals("bonus2")) {
			Location l = e.getPlayer().getLocation();
			Game g = Main.getGame(e.getPlayer());
			Player target = u.getClosestPlayer(e.getPlayer(), g.getAlivePlayersWithout(e.getPlayer()));
			for (int i = 0 ; i < 5 ; i++) {
				org.bukkit.entity.Spider s = (org.bukkit.entity.Spider) l.getWorld().spawnEntity(l, EntityType.SPIDER);
				s.setTarget(target);
				g.addSpawnedEntity(s);
			}
			u.takeOneFromItemInHand(e.getPlayer());
		}
	}
	
	
	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.SPEED, 2);
	}

}
