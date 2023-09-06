package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.superpenguin.supercraftbros.Main.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;

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
