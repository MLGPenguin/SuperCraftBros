package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.Main.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;

public class Giant extends Kit {
	
	public Giant() {
		super(	kitType.GIANT,
				"Giant",
				new Armour(u.getHead("c73b32205364255fa28512be578b7d98853398699ea8f6b2854231890e296"))
				);
	}
	
	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.BAMBOO).addEnchant(Enchantment.KNOCKBACK, 4).setLocname("weapon1").setName("&bBeanstalk").build()
				);
	}

	@Override
	public void activatePerks(Player p) {
		p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
		p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		u.applyInfinitePotionEffect(p, PotionEffectType.SLOW, 4);		
	}
	
	@Override
	public void removePerks(Player p) {
		p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
		p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(0);
	}
	
}
