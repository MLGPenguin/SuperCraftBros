package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.Main.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;

public class Warden extends Kit {
	
	public Warden() {
		super(	kitType.WARDEN,
				"Warden",
				new Armour(u.getHead("54afb30794433f32c97b02c1d228327333f259f56c68c02027b2a16dfb4aa66d"))
				);
	}
	
	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.BLUE_CANDLE).setLocname("weapon1").setName("&bOuch").addEnchant(Enchantment.DAMAGE_ALL, 7).build()
				);
	}
	
	@Override
	public void DamagedByPlayer(EntityDamageByEntityEvent e) {
		u.applyPotionEffect((Player)e.getEntity(), PotionEffectType.SPEED, 4, 5);
	}
	
	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.BLINDNESS, 2);
		u.applyInfinitePotionEffect(p, PotionEffectType.SLOW, 3);
		p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);		
	}
	
	@Override
	public void removePerks(Player p) {
		p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
	}

}
