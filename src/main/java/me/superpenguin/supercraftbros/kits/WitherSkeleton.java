package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.tags.WitherResistant;
import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.SuperCraftBros;
import me.superpenguin.supercraftbros.SuperCraftBros.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;

public class WitherSkeleton extends Kit implements WitherResistant {
	
	public WitherSkeleton() {
		super(	kitType.WITHERSKELETON,
				"Wither Skeleton",
				new Armour(u.getHead("7953b6c68448e7e6b6bf8fb273d7203acd8e1be19e81481ead51f45de59a8"))
				);
	}
	
	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.WITHER_SKELETON_SKULL).setLocname("weapon1").setName("&aImprovised Wither Skull").addEnchant(Enchantment.DAMAGE_ALL, 2).build(),
				new MIB(Material.SOUL_SAND).setLocname("bonus").setName("&bSlowby").build()
				);		
	}
	@Override
	public void RightClickWithBonusItem(PlayerInteractEvent e) {
		u.applyPotionEffectToAll(SuperCraftBros.getGame(e.getPlayer()).getAlivePlayersWithout(e.getPlayer()), PotionEffectType.SLOW, 1, 10);
		u.takeOneFromItemInHand(e.getPlayer());
	}
	@Override
	public void DamageDoneWithWeapon1(EntityDamageByEntityEvent e) {
		u.applyPotionEffect((Player)e.getEntity(),PotionEffectType.WITHER, 1, 3);
	}
}
