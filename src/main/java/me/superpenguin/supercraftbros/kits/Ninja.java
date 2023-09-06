package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.Main.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;

public class Ninja extends Kit {
	
	public Ninja() {
		super(	kitType.NINJA,
				"Ninja",
				new Armour(u.getHead("90c0757ff200c0ee059d90a43c2876b3c5e196bbef06bab5a2ee66c69ef10dd0"))
				);	
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.SHEARS).setLocname("weapon1").setName("&b...Katana?").addEnchant(Enchantment.DAMAGE_ALL, 3).build(),
				new MIB(Material.SNOWBALL, 3).setLocname("bonus").setName("&BSmoke Grenade").build(),
				new MIB(Material.ENDER_PEARL).build()
				);		
	}
	
	@Override
	public void HandleProjectileThrow(ProjectileLaunchEvent e) {
		String locname = u.getHeldLocname((Player) e.getEntity().getShooter());
		if (locname.equalsIgnoreCase("bonus")) u.addTag(e.getEntity(), "Ninja");		
	}
	
	@Override
	public void HandleProjectileHit(ProjectileHitEvent e) {
		if (u.hasTag(e.getEntity(), "Ninja")) {
			boolean hitEntity = e.getHitEntity() != null;
			Location hit = hitEntity ? e.getHitEntity().getLocation() : e.getHitBlock().getLocation();
			for (Player p : u.getNearbyPlayersExcept(hit, 3, (Player) e.getEntity().getShooter())) 
				u.applyPotionEffect(p, PotionEffectType.BLINDNESS, 2, 4);
			hit.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, hit, 10, 2, 2, 2);
		}
	}
	
	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.SPEED, 3);
	}

}
