package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.SuperCraftBros.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;

public class ZombiePigman extends Kit {
	
	public ZombiePigman() {
		super(	kitType.ZOMBIEPIGMAN,
				"Zombie Pigman",
				new Armour(u.getHead("95fb2df754c98b742d35e7b81a1eeac9d37c69fc8cfecd3e91c67983516f"))
				);
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.GOLDEN_SWORD).setLocname("weapon1").setName("&BPokey Thing").build()
				);
		
	}

	@Override
	public void DamagedByPlayer(EntityDamageByEntityEvent e) {
		u.applyPotionEffect((Player)e.getEntity(), PotionEffectType.SPEED, 2, 5);
	}


}
