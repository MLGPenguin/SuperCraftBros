package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import me.superpenguin.supercraftbros.Main.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;

public class Skeleton extends Kit {
	
	public Skeleton() {
		super(	kitType.SKELETON,
				"Skeleton",
				new Armour(u.getHead("fca445749251bdd898fb83f667844e38a1dff79a1529f79a42447a0599310ea4"))
				);
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.BONE).setLocname("weapon1").setName("&BBig Bone").addEnchant(Enchantment.KNOCKBACK, 1).build(),
				new MIB(Material.BOW).setLocname("bonus2").setName("&BHawkeye").addEnchant(Enchantment.ARROW_INFINITE, 1).build(),
				new MIB(Material.ARROW).setLocname("bonus").build()
				);		
	}

}
