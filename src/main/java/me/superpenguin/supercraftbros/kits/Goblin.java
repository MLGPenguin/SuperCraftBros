package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.tags.GemMultiplier;
import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.Main.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;

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
