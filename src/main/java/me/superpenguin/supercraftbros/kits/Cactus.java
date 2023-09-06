package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.objects.Clickable;
import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.Main.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;

public class Cactus extends Kit {
	
	public Cactus() {
		super( 	kitType.CACTUS,
				"Cactus",
				new Armour(u.getHead("265ea35e3d75ccff36424b0c7daceb4ff4e3f7a6ad3baeb45b0d59841f7c627f"))
				);
	}
	
	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.CACTUS).addEnchant(Enchantment.DAMAGE_ALL, 3).setLocname("weapon1").build()
				);		
	}
	
	@Override
	public void RightClickWithBonusItem(PlayerInteractEvent e) {
		Clickable c = new Clickable(e);
		c.distributePotionToOthers(PotionEffectType.WEAKNESS, 2, 5);
		
	}
	
	@Override
	public void DamageDoneWithWeapon1(EntityDamageByEntityEvent e) {
		Clickable c = new Clickable((Player)e.getDamager(), ((Player)e.getDamager()).getInventory().getItemInMainHand());
		c.applyPotionEffectWithChance((Player)e.getEntity(), 0.25, PotionEffectType.CONFUSION, 1, 10);		
	}

	@Override
	public void activatePerks(Player p) {
		for (int i = 0 ; i < 3 ; i++) p.getInventory().getArmorContents()[i].addEnchantment(Enchantment.THORNS, 1);
	}


}
