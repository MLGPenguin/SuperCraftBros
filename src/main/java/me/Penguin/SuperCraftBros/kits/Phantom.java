package me.Penguin.SuperCraftBros.kits;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.Penguin.SuperCraftBros.Main.kitType;
import me.Penguin.SuperCraftBros.objects.Armour;
import me.Penguin.SuperCraftBros.objects.Kit;
import me.Penguin.SuperCraftBros.utils.MIB;
import me.Penguin.SuperCraftBros.utils.u;

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
