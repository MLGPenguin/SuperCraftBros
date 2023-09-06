package me.Penguin.SuperCraftBros.kits;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import me.Penguin.SuperCraftBros.Main;
import me.Penguin.SuperCraftBros.Main.kitType;
import me.Penguin.SuperCraftBros.customevents.DropBelowHalfHealthEvent;
import me.Penguin.SuperCraftBros.objects.Armour;
import me.Penguin.SuperCraftBros.objects.Kit;
import me.Penguin.SuperCraftBros.objects.SPlayer;
import me.Penguin.SuperCraftBros.utils.MIB;
import me.Penguin.SuperCraftBros.utils.PIB;
import me.Penguin.SuperCraftBros.utils.u;

public class MagicMage extends Kit {
	
	public MagicMage() {
		super(	kitType.MAGICMAGE,
				"Magic Mage",
				new Armour(u.getHead("969b946896468d035f9f8631895bc5858da68ad5dae21b5f090d36f5a2e3f61f"))
				);
	}
	
	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.STICK).setLocname("weapon1").setName("&bWand").addEnchant(Enchantment.KNOCKBACK, 1).build()
				);		
	}
	
	@Override
	public void RightClickWithWeapon1(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		SPlayer s = Main.getPlayer(p.getUniqueId());
		if (s.isBonusReady()) {
			ThrownPotion t = p.launchProjectile(ThrownPotion.class);
			t.setItem(((PIB)new PIB(Material.SPLASH_POTION, 1, PotionEffectType.HARM, 1, 0).setBasePotionData(PotionType.INSTANT_DAMAGE).setLocname("bonus")).buildPotion());
			s.setBonusCooldown(2);
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
