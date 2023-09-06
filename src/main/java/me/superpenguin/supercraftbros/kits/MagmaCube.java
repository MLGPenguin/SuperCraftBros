package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.PIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import me.superpenguin.supercraftbros.Main.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;

public class MagmaCube extends Kit {
	
	public MagmaCube() {
		super(	kitType.MAGMACUBE,
				"Magma Cube",
				new Armour(u.getHead("a1c97a06efde04d00287bf20416404ab2103e10f08623087e1b0c1264a1c0f0c"))
				);
	}
	
	@Override
	public List<ItemStack> getItems() {
	return Arrays.asList(
				new MIB(Material.MAGMA_CREAM).addEnchant(Enchantment.KNOCKBACK, 3).setLocname("weapon1").setName("&BBOUNCY").build(),
				((PIB)new PIB(Material.POTION, 1, PotionEffectType.REGENERATION, 2, 10).setBasePotionData(PotionType.REGEN).setLocname("bonus").setName("&7Healy Stuff")).buildPotion()
				);
		
	}
	
	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.JUMP, 5);
		u.applyInfinitePotionEffect(p, PotionEffectType.FIRE_RESISTANCE, 1);
	}

}
