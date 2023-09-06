package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.SuperCraftBros.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;

public class Squid extends Kit {
	
	public Squid() {
		super(	kitType.SQUID,
				"Squid",
				new Armour(u.getHead("d8705624daa2956aa45956c81bab5f4fdb2c74a596051e24192039aea3a8b8"))
				);
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.INK_SAC).setLocname("weapon1").setName("&BINK!").addEnchant(Enchantment.DAMAGE_ALL, 2).build()
				);		
	}

	@Override
	public void DamageDoneWithWeapon1(EntityDamageByEntityEvent e) {
		if (u.testProbability(0.25)) u.applyPotionEffect((Player)e.getEntity(), PotionEffectType.BLINDNESS, 1, 2);
	}

	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.DOLPHINS_GRACE, 1);
		u.applyInfinitePotionEffect(p, PotionEffectType.WATER_BREATHING, 10);
	}
	
}
