package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.objects.Clickable;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.Main.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;
import me.superpenguin.supercraftbros.utils.MIB;

public class RedstoneGolem extends Kit {
	
	public RedstoneGolem() {
			super(	kitType.REDSTONEGOLEM,
					"Redstone Golem",
					new Armour(u.getHead("bfd0dfacc17ec1a4b260218fb688d3326300bac0868c90f2d31fee6767b84ff8"))
					);	
	}
		
		
		@Override
		public List<ItemStack> getItems() {
			return Arrays.asList(
					new MIB(Material.REDSTONE_BLOCK).setLocname("weapon1").addEnchant(Enchantment.DAMAGE_ALL, 3).addEnchant(Enchantment.KNOCKBACK, 2).build(),
					new MIB(Material.REDSTONE).setLocname("bonus").setName("&6hot").build()
					);		
		}
		
		@Override
		public void RightClickWithBonusItem(PlayerInteractEvent e) {
			Clickable c = new Clickable(e);
			c.setOthersOnFire(80);
		}
		
		@Override
		public void activatePerks(Player p) {
			p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
			p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(0.3);
			u.applyInfinitePotionEffect(p, PotionEffectType.SLOW, 4);
		}
		
		@Override
		public void removePerks(Player p) {
			p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
			p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(0);
		}
		
		@Override
		public void DamagedByPlayer(EntityDamageByEntityEvent e) {
			ItemStack used = ((Player)e.getDamager()).getInventory().getItemInMainHand();
			if (used.getType().toString().contains("_PICKAXE")) {
				e.setDamage(e.getFinalDamage()*3);
			}
		}
		
		

}
