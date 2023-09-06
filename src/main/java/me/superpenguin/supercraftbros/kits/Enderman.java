package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

import me.superpenguin.supercraftbros.Main.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;
import me.superpenguin.supercraftbros.utils.MIB;

public class Enderman extends Kit {
	
	public Enderman() {
		super( 	kitType.ENDERMAN,
				"Enderman",
				new Armour(u.getHead("7a59bb0a7a32965b3d90d8eafa899d1835f424509eadd4e6b709ada50b9cf"))
				);
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.ENDER_EYE).setLocname("weapon1").setName("&BAmaterasu").addEnchant(Enchantment.FIRE_ASPECT, 1).addEnchant(Enchantment.DAMAGE_ALL, 2).build(),
				new MIB(Material.ENDER_PEARL, 2).setLocname("bonus").build()
				);		
	}

	@Override
	public void HandleProjectileThrow(ProjectileLaunchEvent e) {
		try {
		if (((Player)e.getEntity().getShooter()).getInventory().getItemInMainHand().getItemMeta().getLocalizedName().equals("weapon1")) e.setCancelled(true);
		} catch (NullPointerException ex) { return; }				
	}
	
	@Override
	public void takenDamage(EntityDamageEvent e) {
		if (e.getCause().equals(DamageCause.PROJECTILE)) e.setCancelled(true);
	}
	
}
