package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.Main.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;

public class Experienced extends Kit {
	
	public Experienced() {
		super(	kitType.EXPERIENCED,
				"Experienced",
				new Armour(u.getHead("52a5d929cde70acb9790e81a78141f5cae58329888ba0f37cd632b5e751723a1"))
				);
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.STICK).setLocname("weapon1").addEnchant(Enchantment.KNOCKBACK, 2).build()
				);		
	}
	
	@Override
	public void DamageDoneWithWeapon1(EntityDamageByEntityEvent e) {
		u.applyPotionEffect((Player)e.getEntity(), PotionEffectType.SLOW, 3, 2);
	}
	
	@Override
	public void activatePerks(Player p) {
		p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(0.5);
	}
	
	@Override
	public void removePerks(Player p) {
		p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(0);
	}
	
	
	
	
	
}
