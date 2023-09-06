package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.customevents.DropBelowHalfHealthEvent;
import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.Main;
import me.superpenguin.supercraftbros.Main.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;
import me.superpenguin.supercraftbros.objects.SPlayer;

public class InfernoMage extends Kit {
	
	public InfernoMage() {
		super(	kitType.INFERNOMAGE,
				"Inferno Mage",
				new Armour(u.getHead("5ac740014a8ced1732e8d568b001bc4db8ffcfc69910966acb68b39b018292ed"))
				);
	}
	
	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.BLAZE_ROD).setLocname("weapon1").setName("&bWand").build()
				);
	}

	@Override
	public void RightClickWithWeapon1(PlayerInteractEvent e) {
		SPlayer s = Main.getPlayer(e.getPlayer().getUniqueId());
		if (s.isBonusReady()) {
			Fireball b = e.getPlayer().launchProjectile(SmallFireball.class);
			b.setVelocity(b.getVelocity().multiply(2));
			s.setBonusCooldown(3);
		}
	}
	
	@Override
	public void dropBelowHalfHealth(DropBelowHalfHealthEvent e) {
		u.reapplyIfBelowHalfHealthTimer(e.getPlayer(), PotionEffectType.SPEED, 2);
	}
	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.SLOW_FALLING, 1);
	}
	
	
}
