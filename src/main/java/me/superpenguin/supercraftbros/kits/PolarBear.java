package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.tags.DoubleFireDamage;
import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.Main;
import me.superpenguin.supercraftbros.Main.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;

public class PolarBear extends Kit implements DoubleFireDamage {
	
	public PolarBear() {
		super(	kitType.POLARBEAR,
				"Polar Bear",
				new Armour(u.getHead("442123ac15effa1ba46462472871b88f1b09c1db467621376e2f71656d3fbc"))
				);
	}

	@Override
	public List<ItemStack> getItems() {
	return Arrays.asList(
				new MIB(Material.COD).addEnchant(Enchantment.DAMAGE_ALL, 2).setLocname("weapon1").setName("&bRA").build(),
				new MIB(Material.FIREWORK_ROCKET).setLocname("bonus").setName("&6Roar").build()
				);		
	}
	
	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.HEALTH_BOOST, 2);
	}
	
	@Override
	public void RightClickWithBonusItem(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (u.isHoldingBonusItem(p)) {
			u.applyPotionEffectToAll(Main.getGame(p).getAlivePlayersWithout(p), PotionEffectType.WEAKNESS, 1, 15);
			u.takeOneFromItemInHand(p);
		}
	}

}
