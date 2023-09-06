package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.customevents.DropBelowHalfHealthEvent;
import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.PIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import me.superpenguin.supercraftbros.SuperCraftBros;
import me.superpenguin.supercraftbros.SuperCraftBros.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;
import me.superpenguin.supercraftbros.objects.SPlayer;

public class PoisonMage extends Kit {
	
	public PoisonMage() {
		super(	kitType.POISONMAGE,
				"Poison Mage",
				new Armour(u.getHead("7b97befbce24c1785af3ebbd1b3761da592f5f8e29eda99a7b9ed09f820f577b"))
				);
	}
	
	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.LEVER).setLocname("weapon1").setName("&bWand").build()
				);
	}
	
	@Override
	public void RightClickWithWeapon1(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		SPlayer s = SuperCraftBros.getPlayer(p.getUniqueId());
		if (s.isBonusReady()) {
			ThrownPotion t = p.launchProjectile(ThrownPotion.class);
			t.setItem(((PIB)new PIB(Material.SPLASH_POTION, 1, PotionEffectType.POISON, 1, 5).setBasePotionData(PotionType.POISON).setLocname("bonus")).buildPotion());
			s.setBonusCooldown(5);
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
