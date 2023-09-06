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

public class Blaze extends Kit {

	public Blaze() {
		super( 	kitType.BLAZE,
				"Blaze",
				new Armour(u.getHead("b20657e24b56e1b2f8fc219da1de788c0c24f36388b1a409d0cd2d8dba44aa3b"))				
				);
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.BLAZE_ROD).addEnchant(Enchantment.DAMAGE_ALL, 2).addEnchant(Enchantment.KNOCKBACK, 2).addEnchant(Enchantment.FIRE_ASPECT, 1)
				.setLocname("weapon1").setName("&cFirery Rod").build()
				);
		
	}

	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.FIRE_RESISTANCE, 1);
	}

}
