package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.tags.EnderAcidResistant;
import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.DragonFireball;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.superpenguin.supercraftbros.Main.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;

public class EnderDragon extends Kit implements EnderAcidResistant {
	
	public EnderDragon() {		
		super(	kitType.ENDERDRAGON,
				"Ender Dragon",
				new Armour(u.getHead("e3c081320c722b82455c2946ca547cd8ac9c8d6d15990ebe9d2c6f1a5ec4e7ef"), true)
				);
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.END_ROD).setLocname("weapon1").setName("&CMagical Rod").addEnchant(Enchantment.DAMAGE_ALL, 3).build(),
				new MIB(Material.ENDER_EYE, 2).setLocname("bonus").setName("&6Fireball").build()
				);		
	}

	@Override
	public void RightClickWithBonusItem(PlayerInteractEvent e) {
		e.setCancelled(true);
		e.getPlayer().launchProjectile(DragonFireball.class);
		u.takeOneFromItemInHand(e.getPlayer());
	}
}
