package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.objects.Game;
import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.SuperCraftBros;
import me.superpenguin.supercraftbros.SuperCraftBros.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;

public class Bee extends Kit {
	
	public Bee() {
		super( 	kitType.BEE,
				"Bee",
				new Armour(u.getHead("d0299a2aae9a605b5dbd1945fc4368ccee88ae06e47dc90f953131e0d903b322"), false)
				);		
	}
	
	@Override
	public List<ItemStack> getItems() {
	return Arrays.asList(
				new MIB(Material.HONEYCOMB).addEnchant(Enchantment.DAMAGE_ALL, 2).setLocname("weapon1").setName("&bHoney").build(),
				new MIB(Material.BEEHIVE, 1).setLocname("bonus").setName("&6SWARM TIME").build()
				);		
	}

	@Override
	public void DamageDoneWithWeapon1(EntityDamageByEntityEvent e) {
		if (u.testProbability(0.2)) u.applyPotionEffect((Player)e.getEntity(), PotionEffectType.POISON, 1, 3);
	}

	@Override
	public void RightClickWithBonusItem(PlayerInteractEvent e) {
			e.setCancelled(true);
			Player p = e.getPlayer();
			Player target = u.getClosestPlayer(p, SuperCraftBros.getGame(p).getAlivePlayersWithout(p));
			Game g = SuperCraftBros.getGame(p);
			for (int i = 0 ; i < 5 ; i++) {
				org.bukkit.entity.Bee bee = (org.bukkit.entity.Bee) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.BEE);
				bee.setAnger(1);
				bee.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(1);
				bee.setHealth(1);
				bee.setTarget(target);
				g.addSpawnedEntity(bee);
			}
			u.takeOneFromItemInHand(p);
		
	}

}
