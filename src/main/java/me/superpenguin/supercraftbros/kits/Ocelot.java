package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

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

public class Ocelot extends Kit {
	
	public Ocelot() {
		super(	kitType.OCELOT,
				"Ocelot",
				new Armour(u.getHead("51f07e3f2e5f256bfade666a8de1b5d30252c95e98f8a8ecc6e3c7b7f67095"))
				);
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.COD).setLocname("weapon1").setName("&BFishy Business").addEnchant(Enchantment.KNOCKBACK, 2).addEnchant(Enchantment.DAMAGE_ALL, 3).build()
				);		
	}

	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.SPEED, 4);
	}
}
