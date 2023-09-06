package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.SuperCraftBros.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;
import me.superpenguin.supercraftbros.utils.MIB;

public class Phantom extends Kit {
	
	public Phantom() {
		super(	kitType.PHANTOM,
				"Phantom",
				new Armour(u.getHead("746830da5f83a3aaed838a99156ad781a789cfcf13e25beef7f54a86e4fa4"), true)
				);
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.PHANTOM_MEMBRANE).setLocname("weapon1").setName("&BBrains").addEnchant(Enchantment.DAMAGE_ALL, 3).build()
				);		
	}

	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.SPEED, 1);
	}
}
