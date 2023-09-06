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

public class Spider extends Kit {
	
	public Spider() {
		super(	kitType.SPIDER,
				"Spider",
				new Armour(u.getHead("c87a96a8c23b83b32a73df051f6b84c2ef24d25ba4190dbe74f11138629b5aef"))
				);
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.SPIDER_EYE).setLocname("weapon1").setName("&BSharp Eyes").addEnchant(Enchantment.KNOCKBACK, 1).addEnchant(Enchantment.DAMAGE_ALL, 2).build()
				);		
	}

	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.SPEED, 1);
	}
}
