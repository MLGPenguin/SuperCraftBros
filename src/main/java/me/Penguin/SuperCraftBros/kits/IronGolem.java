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
import me.Penguin.SuperCraftBros.utils.MIB;
import me.Penguin.SuperCraftBros.utils.u;

public class IronGolem extends Kit {
	
	public IronGolem() {
		super(	kitType.IRONGOLEM,
				"Iron Golem",
				new Armour(u.getHead("89091d79ea0f59ef7ef94d7bba6e5f17f2f7d4572c44f90f76c4819a714"))
				);
	}
	
	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.IRON_AXE).setLocname("weapon1").setName("&CVIKING AXE").build()
				);		
	}

	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.DAMAGE_RESISTANCE, 1);
		u.applyInfinitePotionEffect(p, PotionEffectType.SLOW, 3);
	}
}
