package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.objects.Clickable;
import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.Main;
import me.superpenguin.supercraftbros.Main.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;

public class Pixie extends Kit {
	
	public Pixie() {
		super(	kitType.PIXIE,
				"Pixie",
				new Armour(u.getHead("26464680b57f125f9f379c27398a09f9703864a727e5ca41395e0ffa2f46030"))
				);		
	}
	
	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.WOODEN_SWORD).setLocname("weapon1").setName("&CPixie Dust?").build(),
				new MIB(Material.SUGAR).setLocname("bonus").setName("&6Magic Dust").build()
				);		
	}
	
	@Override
	public void RightClickWithBonusItem(PlayerInteractEvent e) {
		Clickable c = new Clickable(e.getPlayer(), e.getItem());
		if (c.isBonus()) c.distributePotionToOthers(PotionEffectType.CONFUSION, 1, 12);
	}
	
	@Override
	public void activatePerks(Player p) {
		p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(14);
		u.applyInfinitePotionEffect(p, PotionEffectType.SPEED, 3);
	}
	
	@Override
	public void removePerks(Player p) {
		p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
	}
	
	@Override
	public void DamagedByPlayer(EntityDamageByEntityEvent e) {
		e.getEntity().teleport(Main.getGame((Player)e.getEntity()).getSafeSpawn());
	}


}
