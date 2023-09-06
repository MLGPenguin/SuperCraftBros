package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.Main;
import me.superpenguin.supercraftbros.Main.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;

public class Angel extends Kit {
	
	public Angel() {
		super ( kitType.ANGEL,
				"Angel",
				new Armour(u.getHead("7832fec7762d7fe6842d01f04a869f2486c0292d9c75a74493da0c842579ddb7"), true)
				);
	}
	
	@Override
	public int getLives() { return 6; }

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.SPECTRAL_ARROW).setLocname("weapon1").setName("&bStaBBY STABBy").addEnchant(Enchantment.DAMAGE_ALL, 2).build(),
				new MIB(Material.RED_DYE).setLocname("bonus").setName("&bCatastrophic Healing").build()
				);		
	}
	
	
	@Override
	public void DamageDoneWithWeapon1(EntityDamageByEntityEvent e) {
		u.applyPotionEffect((Player) e.getEntity(), PotionEffectType.LEVITATION, 1, 1);
	}
	
	@Override
	public void RightClickWithBonusItem(PlayerInteractEvent e) {
		u.takeOne(e.getItem());
		u.applyPotionEffectToAll(Main.getGame(e.getPlayer()).getAlivePlayers(), PotionEffectType.REGENERATION, 1, 15);
	}
	
	@Override
	public void DamagedPlayer(EntityDamageByEntityEvent e) {
		if (((Player)e.getEntity()).hasPotionEffect(PotionEffectType.REGENERATION)) e.setDamage(e.getFinalDamage()*2);
	}

}
