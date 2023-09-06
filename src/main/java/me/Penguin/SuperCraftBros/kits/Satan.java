package me.Penguin.SuperCraftBros.kits;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.Penguin.SuperCraftBros.Main.kitType;
import me.Penguin.SuperCraftBros.objects.Armour;
import me.Penguin.SuperCraftBros.objects.Kit;
import me.Penguin.SuperCraftBros.utils.MIB;
import me.Penguin.SuperCraftBros.utils.u;

public class Satan extends Kit {
	
	public Satan() {
		super(	kitType.SATAN,
				"Satan",
				new Armour(u.getHead("a20a3386b813aa15ce09e2c92a2a67db13142e57d5f793167df170488df40271"), true)
				);
	}

	@Override
	public List<ItemStack> getItems() {
	return Arrays.asList(
				new MIB(Material.WITHER_ROSE).addEnchant(Enchantment.FIRE_ASPECT, 1).setLocname("weapon1").setName("&bPROFANITY").build()
				);		
	}
	
	@Override
	public void DamageDoneWithWeapon1(EntityDamageByEntityEvent e) {
		u.applyPotionEffect((Player)e.getEntity(), PotionEffectType.WITHER, 1, 2);
	}
	
	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.DAMAGE_RESISTANCE, 1);
		u.applyInfinitePotionEffect(p, PotionEffectType.JUMP, 2);
	}
	
	@Override
	public void OnKitKilled(EntityDeathEvent e) {
		Location loc = e.getEntity().getLocation();
		loc.setPitch(0);
		loc.setYaw(0);
		for (int i = 0 ; i < 8 ; i++) {
			WitherSkull w = (WitherSkull) loc.getWorld().spawnEntity(loc, EntityType.WITHER_SKULL);
			w.setVelocity(loc.getDirection());
			loc.setYaw(loc.getYaw() + 45);	
		}
	}
	

}
