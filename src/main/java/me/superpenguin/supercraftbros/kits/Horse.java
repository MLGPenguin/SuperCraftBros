package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.SuperCraftBros;
import me.superpenguin.supercraftbros.objects.Kit;
import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.objects.Armour;

public class Horse extends Kit {
	
	public Horse() {
		super(	SuperCraftBros.kitType.HORSE,
				"Horse",
				new Armour(u.getHead("29ac1eab3d6959394b4ef9c6d3f62eda65dc41ad18b816a783eba7074ec5bea0"))
				);
	}
	
	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.BLACK_WOOL).addEnchant(Enchantment.KNOCKBACK, 2).setLocname("weapon1").setName("&bHooves").build()
				);
	}
	
	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.SPEED, 4);
	}

}
