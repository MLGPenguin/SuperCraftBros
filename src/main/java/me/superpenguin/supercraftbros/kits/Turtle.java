package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.utils.PIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import me.superpenguin.supercraftbros.SuperCraftBros.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;
import me.superpenguin.supercraftbros.utils.MIB;

public class Turtle extends Kit {
	
	public Turtle() {
		super(	kitType.TURTLE,
				"Turtle",
				new Armour(u.getHead("0a4050e7aacc4539202658fdc339dd182d7e322f9fbcc4d5f99b5718a"))
				);
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.TURTLE_EGG).setLocname("weapon1").addEnchant(Enchantment.DAMAGE_ALL, 1).addEnchant(Enchantment.KNOCKBACK, 2).build(),
				((PIB)new PIB(Material.POTION, 1, PotionEffectType.DAMAGE_RESISTANCE, 4, 20).addPotionType(PotionEffectType.SLOW, 3).setBasePotionData(PotionType.TURTLE_MASTER).setLocname("bonus").setName("&6Stronk")).buildPotion()
				);		
	}
	
	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.DOLPHINS_GRACE, 1);
		u.applyInfinitePotionEffect(p, PotionEffectType.WATER_BREATHING, 10);
	}
	
}
