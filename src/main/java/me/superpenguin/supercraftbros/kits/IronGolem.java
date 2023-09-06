package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.SuperCraftBros;
import me.superpenguin.supercraftbros.objects.Kit;
import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.objects.Armour;

public class IronGolem extends Kit {
	
	public IronGolem() {
		super(	SuperCraftBros.kitType.IRONGOLEM,
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
