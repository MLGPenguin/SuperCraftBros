package me.Penguin.SuperCraftBros.kits;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Fireball;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.Penguin.SuperCraftBros.Main.kitType;
import me.Penguin.SuperCraftBros.objects.Armour;
import me.Penguin.SuperCraftBros.objects.Kit;
import me.Penguin.SuperCraftBros.utils.MIB;
import me.Penguin.SuperCraftBros.utils.u;

public class Ghast extends Kit {
	
	public Ghast() {
		super(	kitType.GHAST,
				"Ghast",
				new Armour(u.getHead("8b6a72138d69fbbd2fea3fa251cabd87152e4f1c97e5f986bf685571db3cc0"))
				);
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.GHAST_TEAR).setLocname("weapon1").setName("&BTears of Glass").addEnchant(Enchantment.KNOCKBACK, 1).addEnchant(Enchantment.DAMAGE_ALL, 3).build(),
				new MIB(Material.FIRE_CHARGE, 3).setLocname("bonus").build()
				);		
	}

	@Override
	public void RightClickWithBonusItem(PlayerInteractEvent e) {
		e.setCancelled(true);
		Fireball b = e.getPlayer().launchProjectile(Fireball.class);
		b.setShooter(e.getPlayer());
		u.takeOneFromItemInHand(e.getPlayer());
	}
	
}
