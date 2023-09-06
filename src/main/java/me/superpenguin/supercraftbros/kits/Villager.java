package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.tags.GemMultiplier;
import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.SuperCraftBros.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;

public class Villager extends Kit implements GemMultiplier {
	
	public Villager() {
		super(	kitType.VILLAGER,
				"Villager",
				new Armour(u.getHead("41b830eb4082acec836bc835e40a11282bb51193315f91184337e8d3555583"))
				);
	}

	@Override
	public List<ItemStack> getItems() {
	return Arrays.asList(
				new MIB(Material.POTATO).setLocname("weapon1").addEnchant(Enchantment.DAMAGE_ALL, 2).setName("&bHMMM").build()
				);		
	}
	
	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.REGENERATION, 1);
	}

	@Override
	public double getGemMultiplier() { 
		return 1.15;
	}
	
}
