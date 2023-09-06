package me.Penguin.SuperCraftBros.kits;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.Penguin.SuperCraftBros.Main.kitType;
import me.Penguin.SuperCraftBros.objects.Armour;
import me.Penguin.SuperCraftBros.objects.Clickable;
import me.Penguin.SuperCraftBros.objects.Kit;
import me.Penguin.SuperCraftBros.utils.MIB;
import me.Penguin.SuperCraftBros.utils.u;

public class Husk extends Kit {
	
	public Husk() {
		super(	kitType.HUSK,
				"Husk",
				new Armour(u.getHead("269b9734d0e7bf060fedc6bf7fec64e1f7ad6fc80b0fd8441ad0c7508c850d73"))
				);	
	}
	
	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.SAND).setLocname("weapon1").addEnchant(Enchantment.DAMAGE_ALL, 2).build(),
				new MIB(Material.DEAD_BUSH).setLocname("bonus").setName("&6hot").build()
				);		
	}
	
	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.FIRE_RESISTANCE, 1);
	}
	
	@Override
	public void RightClickWithBonusItem(PlayerInteractEvent e) {
		Clickable c = new Clickable(e);
		if (c.isBonus()) {
			c.setOthersOnFire(60, false);
			c.distributePotionToOthers(PotionEffectType.SLOW, 1, 3);
		}
	}

}
