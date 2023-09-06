package me.Penguin.SuperCraftBros.kits;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.Penguin.SuperCraftBros.Main.kitType;
import me.Penguin.SuperCraftBros.objects.Armour;
import me.Penguin.SuperCraftBros.objects.Kit;
import me.Penguin.SuperCraftBros.utils.MIB;
import me.Penguin.SuperCraftBros.utils.u;

public class Vex extends Kit {
	
	public Vex() {
		super( 	kitType.VEX,
				"Vex",
				new Armour(u.getHead("c2ec5a516617ff1573cd2f9d5f3969f56d5575c4ff4efefabd2a18dc7ab98cd"))				
				);
	}
	
	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.IRON_NUGGET).addEnchant(Enchantment.DAMAGE_ALL, 2).setLocname("weapon1").setName("&bBitch Axe").build()
			);		
	}
	
	@Override
	public void takenDamage(EntityDamageEvent e) {
		Player p = (Player) e.getEntity();
		double finalhealth = p.getHealth()-e.getFinalDamage();
		if (finalhealth < 6) u.reapplyIfBelowHealthTimer(6, p, PotionEffectType.INCREASE_DAMAGE, 2);		
	}

	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.SPEED, 2);
	}

}
