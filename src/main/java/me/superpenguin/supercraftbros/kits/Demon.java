package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.customevents.DropBelowHalfHealthEvent;
import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.SuperCraftBros;
import me.superpenguin.supercraftbros.SuperCraftBros.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;

public class Demon extends Kit {
	
	public Demon() {
		super(	kitType.DEMON,
				"Demon",
				new Armour(u.getHead("d9fcc28a98d1cfcc950130ba153aa99604cca51ddb94bfba5fa4e31c363a1c81"), true)
				);
	}
	
	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(new MIB(Material.BLACK_CANDLE).setName("&bSPooky").setLocname("weapon1").addEnchant(Enchantment.DAMAGE_ALL, 2).build());
	}
	
	@Override
	public void DamageDoneWithWeapon1(EntityDamageByEntityEvent e) {
		u.applyPotionEffect((Player)e.getEntity(), PotionEffectType.WEAKNESS, 1, 2);
	}
	
	@Override
	public void takenDamage(EntityDamageEvent e) {
		u.immuneToWither(e);
	}
	
	@Override
	public void dropBelowHalfHealth(DropBelowHalfHealthEvent e) {
		u.makeExplosionEffect(e.getPlayer().getLocation(), 40, 2);
		e.getPlayer().teleport(SuperCraftBros.getGame(e.getPlayer()).getSafeSpawn());
	}
	
	
	

}
