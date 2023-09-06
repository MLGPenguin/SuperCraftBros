package me.Penguin.SuperCraftBros.kits;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.Penguin.SuperCraftBros.Main.kitType;
import me.Penguin.SuperCraftBros.objects.Armour;
import me.Penguin.SuperCraftBros.objects.Kit;
import me.Penguin.SuperCraftBros.utils.MIB;
import me.Penguin.SuperCraftBros.utils.u;
import me.Penguin.SuperCraftBros.utils.u.CustomCause;

public class FireFighter extends Kit {
	
	public FireFighter() {
		super(	kitType.FIREFIGHTER,
				"Fire Fighter",
				new Armour(u.getHead("4da3b64af2d1a45a9cdb55949e811a3a7a4de11d8769d35f03d333f55a78becc"))
				);		
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.WATER_BUCKET).addEnchant(Enchantment.KNOCKBACK, 3).setLocname("weapon1").setName("&bHOSE").build(),
				new MIB(Material.SNOWBALL, 16).setLocname("bonus").setName("&bIce Ice B..ombs").build()
				);
	}

	@Override
	public void HandleProjectileHit(ProjectileHitEvent e) {
		if (u.hasTag(e.getEntity(), "FireFighter")) {
			Player shooter = (Player) e.getEntity().getShooter();
			Location loc = u.getHitLocation(e);
			u.makeExplosionEffect(loc, 10, 2);
			u.DamageWithinRange(loc, 4, shooter, 3, CustomCause.SNOW);
		}
	}

	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.FIRE_RESISTANCE, 1);
	}

	@Override
	public void takenDamage(EntityDamageEvent e) {
		if (e.getEntity().getLocation().getBlock().getType() == Material.WATER) {
			e.setDamage(e.getFinalDamage() * 0.75);
		}
	}
	
	@Override
	public void HandleProjectileThrow(ProjectileLaunchEvent e) {
		Player p = (Player) e.getEntity().getShooter();
		String locname = u.getHeldLocname(p);
		if (locname.equals("bonus")) {
			u.addTag(e.getEntity(), "FireFighter");
			p.setCooldown(Material.SNOWBALL, 10);
		}
	}

}
