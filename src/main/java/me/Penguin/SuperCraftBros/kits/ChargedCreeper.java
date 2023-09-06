package me.Penguin.SuperCraftBros.kits;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;

import me.Penguin.SuperCraftBros.Main;
import me.Penguin.SuperCraftBros.Main.kitType;
import me.Penguin.SuperCraftBros.objects.Armour;
import me.Penguin.SuperCraftBros.objects.Kit;
import me.Penguin.SuperCraftBros.tags.ExplosionResistant;
import me.Penguin.SuperCraftBros.utils.MIB;
import me.Penguin.SuperCraftBros.utils.u;

public class ChargedCreeper extends Kit implements ExplosionResistant {
	
	public ChargedCreeper() {
		super(	kitType.CHARGEDCREEPER,
				"Charged Creeper",
				new Armour(u.getHead("f2ceb39dd4de24a7adfe291a3a0cfc7cf4f645de59b603fcfe06c6b5a06e26"))
				);
	}
	
	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.GUNPOWDER).addEnchant(Enchantment.DAMAGE_ALL, 2).setLocname("weapon1").setName("&bAshes").build(),
				new MIB(Material.TNT, 2).setLocname("bonus").setName("&bCANNON").build()
				);
	}
	
	@Override
	public void RightClickWithBonusItem(PlayerInteractEvent e) {
		e.setCancelled(true);
		u.takeOneFromItemInHand(e.getPlayer());
		Player p = e.getPlayer();
		Location l = p.getLocation();
		TNTPrimed tnt  = (TNTPrimed) l.getWorld().spawnEntity(l, EntityType.PRIMED_TNT);
		tnt.setVelocity(p.getLocation().getDirection());
		tnt.setFuseTicks(30);
		tnt.getPersistentDataContainer().set(new NamespacedKey(Main.get(), "Creeper"), PersistentDataType.STRING, e.getPlayer().getName());
	}
	
	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.SPEED, 1);
	}

}
