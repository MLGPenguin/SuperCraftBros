package me.Penguin.SuperCraftBros.kits;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.Penguin.SuperCraftBros.Main.kitType;
import me.Penguin.SuperCraftBros.objects.Armour;
import me.Penguin.SuperCraftBros.objects.Kit;
import me.Penguin.SuperCraftBros.utils.MIB;
import me.Penguin.SuperCraftBros.utils.u;

public class Panda extends Kit {
	
	public Panda() {
		super(	kitType.PANDA,
				"Panda",
				new Armour(u.getHead("9b9151bcab196e930702d867f833148d23a54a5f0e7d554907dd8df016eb6d92"))
				);
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.BAMBOO).setLocname("weapon1").setName("&BFighting Stick").addEnchant(Enchantment.KNOCKBACK, 2).addEnchant(Enchantment.DAMAGE_ALL, 1).build(),
				new MIB(Material.COD).setLocname("bonus").setName("&6Protein Boost").addLores("&bRight click activate rage mode").build()
				);
		
	}

	@Override
	public void RightClickWithBonusItem(PlayerInteractEvent e) {
		e.setCancelled(true);
		Player p = e.getPlayer();
		u.applyPotionEffect(p, PotionEffectType.INCREASE_DAMAGE, 2, 10);
		u.applyPotionEffect(p, PotionEffectType.SPEED, 3, 10);
		p.getWorld().spawnParticle(Particle.PORTAL, p.getLocation(), 30, 1, 1, 1);	
		p.sendMessage(u.cc("&aActivated Rage Mode!"));
		u.removeItemInMainHand(p);
		u.sendLater(p, "&cRage mode has worn off", 10);
	}

	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.SLOW, 1);
	}

}
