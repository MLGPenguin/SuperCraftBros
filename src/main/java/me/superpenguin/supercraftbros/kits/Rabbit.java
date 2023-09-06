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

import me.superpenguin.supercraftbros.Main.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;

public class Rabbit extends Kit {
	
	public Rabbit() {
		super(	kitType.RABBIT,
				"Rabbit",
				new Armour(u.getHead("c1db38ef3c1a1d59f779a0cd9f9e616de0cc9acc7734b8facc36fc4ea40d0235"))
				);
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.RABBIT_FOOT).setLocname("weapon1").setName("&BMy Foot").addEnchant(Enchantment.KNOCKBACK, 1).addEnchant(Enchantment.DAMAGE_ALL, 1).build()
				);
	}

	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.SPEED, 3);
		u.applyInfinitePotionEffect(p, PotionEffectType.JUMP, 2);
	}
}
