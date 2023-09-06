package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.PIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import me.superpenguin.supercraftbros.Main.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;

public class Pufferfish extends Kit {
	
	public Pufferfish() {
		super(	kitType.PUFFERFISH,
				"Pufferfish",
				new Armour(u.getHead("292350c9f0993ed54db2c7113936325683ffc20104a9b622aa457d37e708d931"))
				);
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.PUFFERFISH).setLocname("weapon1").setName("&BPufferfish").addEnchant(Enchantment.KNOCKBACK, 1).addEnchant(Enchantment.DAMAGE_ALL, 1).build(),
				((PIB)new PIB(Material.SPLASH_POTION, 3, PotionEffectType.POISON, 2, 5).setBasePotionData(PotionType.POISON).setLocname("bonus").setName("&6Poison")).buildPotion()
				);		
	}
	
	@Override
	public void takenDamage(EntityDamageEvent e) {
		if (e.getCause() == DamageCause.POISON) {
			e.setCancelled(true);
			((Player)e.getEntity()).removePotionEffect(PotionEffectType.POISON);
		}
	}

	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.HEALTH_BOOST, 1);
	}

	@Override
	public void DamagedByPlayer(EntityDamageByEntityEvent e) {
		u.applyPotionEffect((Player)e.getDamager(), PotionEffectType.POISON, 1, 2);		
	}
}
