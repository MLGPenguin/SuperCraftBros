package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.Main;
import me.superpenguin.supercraftbros.Main.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;
import me.superpenguin.supercraftbros.utils.MIB;

public class GingerBreadMan extends Kit {
	
	public GingerBreadMan() {
		super(	kitType.GINGERBREADMAN,
				"Gingerbread Man",
				new Armour(u.getHead("a79c932c31e16fd65ce3c99cca98645ab2f16b2623b5e1e72c6de689a65187f"))
				);
	}
	
	@Override
	public List<ItemStack> getItems() {
	return Arrays.asList(
				new MIB(Material.DARK_OAK_BUTTON).addEnchant(Enchantment.KNOCKBACK, 1).addEnchant(Enchantment.DAMAGE_ALL, 1).setLocname("weapon1").setName("&bChocolate Chip").build(),
				new MIB(Material.IRON_INGOT, 1).setLocname("bonus").setName("&cINCINERATOR").build()
				);
		
	}

	@Override
	public void RightClickWithBonusItem(PlayerInteractEvent e) {
		e.setCancelled(true);
		for (Player p : Main.getGame(e.getPlayer()).getAlivePlayersWithout(e.getPlayer())) p.setFireTicks(80);
		u.takeOneFromItemInHand(e.getPlayer());
	}

	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.REGENERATION, 1);
	}
}
