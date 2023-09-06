package me.Penguin.SuperCraftBros.kits;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.Penguin.SuperCraftBros.Main.kitType;
import me.Penguin.SuperCraftBros.objects.Armour;
import me.Penguin.SuperCraftBros.objects.Kit;
import me.Penguin.SuperCraftBros.utils.MIB;
import me.Penguin.SuperCraftBros.utils.u;

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
