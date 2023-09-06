package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.tags.DoubleFireDamage;
import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.SuperCraftBros;
import me.superpenguin.supercraftbros.SuperCraftBros.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;

public class SnowGolem extends Kit implements DoubleFireDamage {
	
	public SnowGolem() {
		super(	kitType.SNOWGOLEM,
				"Snow Golem",
				new Armour(u.getHead("1fdfd1f7538c040258be7a91446da89ed845cc5ef728eb5e690543378fcf4"))
				);
	}

	@Override
	public List<ItemStack> getItems() {
	return Arrays.asList(
				new MIB(Material.PUMPKIN).addEnchant(Enchantment.KNOCKBACK, 2).setLocname("weapon1").setName("&bHead- less...").build(),
				new MIB(Material.SNOWBALL, 64).setLocname("bonus").setName("&bRigged Snow Ball").build(),
				new MIB(Material.POWDER_SNOW_BUCKET).setLocname("bonus2").setName("Slow").build()
				);		
	}
	
	@Override
	public void HandleProjectileThrow(ProjectileLaunchEvent e) {
		if (u.isHoldingBonusItem(e.getEntity().getShooter())) u.addTag(e.getEntity(), "SnowGolem");
	}
	
	@Override
	public void HandleProjectileHit(ProjectileHitEvent e) {
		if (u.hasTag(e.getEntity(), "SnowGolem")) {
			Location loc = u.getHitLocation(e);
			Player shooter = (Player) e.getEntity().getShooter();
			u.makeExplosionEffect(loc, 5, 2);
			u.DamageWithinRange(loc, 3, shooter, 2, u.CustomCause.SNOW);
		}
	}
	
	@Override
	public void RightClickWithBonusItem(PlayerInteractEvent e) {
		if (u.getHeldLocname(e.getPlayer()).equals("bonus2")) {
			u.applyPotionEffectToAll(SuperCraftBros.getGame(e.getPlayer()).getAlivePlayersWithout(e.getPlayer()), PotionEffectType.SLOW, 1, 10);
			u.takeOneFromItemInHand(e.getPlayer());
		}
	}
	
	@Override
	public void takenDamage(EntityDamageEvent e) {
		DamageCause c = e.getCause();
		if (c == DamageCause.FREEZE) {
			e.setCancelled(true);
			e.getEntity().setFreezeTicks(0);
		}
		if (((Player)e.getEntity()).hasPotionEffect(PotionEffectType.SLOW)) ((Player)e.getEntity()).removePotionEffect(PotionEffectType.SLOW);
			
	}

}
