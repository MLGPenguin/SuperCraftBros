package me.Penguin.SuperCraftBros.kits;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.Penguin.SuperCraftBros.Main.kitType;
import me.Penguin.SuperCraftBros.objects.Armour;
import me.Penguin.SuperCraftBros.objects.Kit;
import me.Penguin.SuperCraftBros.tags.GemMultiplier;
import me.Penguin.SuperCraftBros.utils.MIB;
import me.Penguin.SuperCraftBros.utils.u;

public class Goblin extends Kit implements GemMultiplier {
	
	public Goblin() {
		super(	kitType.GOBLIN,
				"Goblin",
				new Armour(u.getHead("b6b972e32d761b192626e5d6d01edc094940910103cea5e2e2d1f231adb755d5"))
				);
	}

	@Override
	public List<ItemStack> getItems() {
	return Arrays.asList(
				new MIB(Material.GOLD_NUGGET).setLocname("weapon1").setName("&bwoosh").build()
				);		
	}
	
	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.SPEED, 5);
	}
	
	@Override
	public double getGemMultiplier() {
		return 5;
	}

}
