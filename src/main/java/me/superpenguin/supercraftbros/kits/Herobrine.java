package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.objects.Clickable;
import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.SuperCraftBros.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;

public class Herobrine extends Kit {
	
	public Herobrine() {
		super(kitType.HEROBRINE,
				"Herobrine",
				new Armour(u.getHead("98b7ca3c7d314a61abed8fc18d797fc30b6efc8445425c4e250997e52e6cb"))
				);
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.WOODEN_SWORD).setLocname("weapon1").setName("&BDagger").build(),
				new MIB(Material.DIAMOND).setLocname("bonus").setName("&6Curse of Blinding").build(),			
				new MIB(Material.TNT).setLocname("bonus2").setName("&4&LSELF DESTRUCT").build()				
				);		
	}
	@Override
	public void RightClickWithBonusItem(PlayerInteractEvent e) {
		Clickable c = new Clickable(e.getPlayer(), e.getItem());
		if (c.isBonus()) c.distributePotionToOthers(PotionEffectType.BLINDNESS, 1, 3);
		else if (c.isBonus2()) {
			e.getItem().setAmount(0);
			e.getPlayer().getWorld().createExplosion(e.getPlayer().getLocation(), 5, false, false, e.getPlayer());
			e.getPlayer().damage(1000);
		}
	}
	
}
