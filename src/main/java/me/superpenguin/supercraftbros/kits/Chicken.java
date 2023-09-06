package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.Main.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;

public class Chicken extends Kit {
	
	public Chicken() {
		super(	kitType.CHICKEN,
				"Chicken",
				new Armour(u.getHead("f48c73dba21951766f144bde2c0f028260603ac9454de3d67172d7e8ecfdff68"))
				);		
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.FEATHER).addEnchant(Enchantment.KNOCKBACK, 3).setLocname("weapon1").setName("&bWing").build(),
				new MIB(Material.EGG, 12).setLocname("bonus").setName("&bBoomy Eggs").build()
				);
	}

	@Override
	public void HandleProjectileHit(ProjectileHitEvent e) {
		if (u.hasTag(e.getEntity(), "Chicken")) {
			Player shooter = (Player) e.getEntity().getShooter();
			Location loc = u.getHitLocation(e);
			u.makeExplosionEffect(loc, 10, 2);
			u.DamageWithinRange(loc, 4, shooter, 3, u.CustomCause.EXPLOSION);
		}
	}

	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.SLOW_FALLING, 1);
		u.applyInfinitePotionEffect(p, PotionEffectType.JUMP, 1);
	}

	@Override
	public void HandleProjectileThrow(ProjectileLaunchEvent e) {
		Player p = (Player) e.getEntity().getShooter();
		String locname = u.getHeldLocname(p);
		if (locname.equals("bonus")) {
			u.addTag(e.getEntity(), "Chicken");
			p.setCooldown(Material.EGG, 10);
		}
	}
	

}
