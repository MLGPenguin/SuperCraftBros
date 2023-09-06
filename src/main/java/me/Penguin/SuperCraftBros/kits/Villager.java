package me.Penguin.SuperCraftBros.kits;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.Penguin.SuperCraftBros.Main.kitType;
import me.Penguin.SuperCraftBros.objects.Armour;
import me.Penguin.SuperCraftBros.objects.Kit;
import me.Penguin.SuperCraftBros.tags.GemMultiplier;
import me.Penguin.SuperCraftBros.utils.MIB;
import me.Penguin.SuperCraftBros.utils.u;

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
