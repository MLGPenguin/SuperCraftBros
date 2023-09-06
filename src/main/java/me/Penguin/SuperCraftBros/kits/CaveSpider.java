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

public class CaveSpider extends Kit {

	public CaveSpider() { 
		super(kitType.CAVESPIDER,
				"Cave Spider",
				new Armour(u.getHead("41645dfd77d09923107b3496e94eeb5c30329f97efc96ed76e226e98224"))
		);
	}
	
	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.FERMENTED_SPIDER_EYE).addEnchant(Enchantment.DAMAGE_ALL, 2).setLocname("weapon1").setName("&bOctoBall").build()
		);
	}
	
	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.SPEED, 1);
	}
	
	@Override
	public void DamageDoneWithWeapon1(EntityDamageByEntityEvent e) {
		u.applyPotionEffect(((Player)e.getEntity()), PotionEffectType.POISON, 1, 3);
	}
}
