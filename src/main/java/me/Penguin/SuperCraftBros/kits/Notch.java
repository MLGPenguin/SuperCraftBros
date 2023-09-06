package me.Penguin.SuperCraftBros.kits;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.Penguin.SuperCraftBros.Main.kitType;
import me.Penguin.SuperCraftBros.objects.Armour;
import me.Penguin.SuperCraftBros.objects.Kit;
import me.Penguin.SuperCraftBros.utils.MIB;
import me.Penguin.SuperCraftBros.utils.u;

public class Notch extends Kit {
	
	public Notch() {
		super(	kitType.NOTCH,
				"Notch",
				new Armour(u.getHead("292009a4925b58f02c77dadc3ecef07ea4c7472f64e0fdc32ce5522489362680"))
				);	
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.WOODEN_SWORD).setLocname("weapon1").build(),
				new MIB(Material.GOLDEN_APPLE).setLocname("bonus").setName("&byum").build()
				);		
	}

}
