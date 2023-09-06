package me.Penguin.SuperCraftBros.kits;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.Penguin.SuperCraftBros.Main.kitType;
import me.Penguin.SuperCraftBros.objects.Armour;
import me.Penguin.SuperCraftBros.objects.Kit;
import me.Penguin.SuperCraftBros.utils.MIB;
import me.Penguin.SuperCraftBros.utils.u;

public class CookieMonster extends Kit {
	
	public CookieMonster() {
		super(	kitType.COOKIEMONSTER,
				"Cookie Monster",
				new Armour(u.getHead("a38340d17d20c648505cd3dc132c9c9b5ef9ebb1ef39b992c1ad6360c4bc098e"))
				);
		
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.COOKIE).setLocname("weapon1").setName("&BCookiee").addEnchant(Enchantment.KNOCKBACK, 2)
				.addEnchant(Enchantment.DAMAGE_ALL, 1).addEnchant(Enchantment.FIRE_ASPECT, 1).build()
				);		
	}

	@Override
	public void DamagedByPlayer(EntityDamageByEntityEvent e) {
		u.applyPotionEffect((Player)e.getEntity(), PotionEffectType.INCREASE_DAMAGE, 1, 3);
	}
	
}
