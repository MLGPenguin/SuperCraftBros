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

public class Firefly extends Kit {
	
	public Firefly() {
		super(	kitType.FIREFLY,
				"Firefly",
				new Armour(u.getHead("bf92d3f385cc16c77675a46de3e833ac17c74ada3e1946ef7021ecdbf9f1ba"), true)
				);
	}
	
	@Override
	public List<ItemStack> getItems() {
	return Arrays.asList(
				new MIB(Material.LANTERN).addEnchant(Enchantment.DAMAGE_ALL, 2).addEnchant(Enchantment.FIRE_ASPECT, 1).setLocname("weapon1").setName("&bSmells like Strawberries").build()
				);		
	}
	
	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.SPEED, 3);
		p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(14);
	}
	
	@Override
	public void removePerks(Player p) {
		p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
	}

}
