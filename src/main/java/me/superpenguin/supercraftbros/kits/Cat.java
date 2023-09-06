package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.Main;
import me.superpenguin.supercraftbros.objects.Clickable;
import me.superpenguin.supercraftbros.objects.Kit;
import me.superpenguin.supercraftbros.objects.SPlayer;
import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.objects.Armour;

public class Cat extends Kit {


	public Cat() {
		super( 	Main.kitType.CAT,
				"Cat",
				new Armour(u.getHead("399128eefb66b7a221c39e06e77d3a996e226052c5342a5358d6413a0e7f8498"))
				);
	}

	@Override
	public int getLives() {
		return 9;
	}
	
	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.QUARTZ).addEnchant(Enchantment.DAMAGE_ALL, 2).setLocname("weapon1").setName("&bClaw").build(),
				new MIB(Material.CLAY).setLocname("bonus").setName("&bHiss").build()
				);		
	}
	
	@Override
	public void RightClickWithBonusItem(PlayerInteractEvent e) {
		Clickable c = new Clickable(e);
		c.distributePotionToOthers(PotionEffectType.WEAKNESS, 2, 5);
		
	}
	
	@Override
	public void RightClickWithWeapon1(PlayerInteractEvent e) {
		SPlayer s = Main.getPlayer(e.getPlayer().getUniqueId());
		if (s.canUse()) {
			e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(2));					
			s.setBonusCooldown(2);
		}
	}

	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.SPEED, 2);
		p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10);
	}
	
	@Override
	public void removePerks(Player p) {
		p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
	}

}
