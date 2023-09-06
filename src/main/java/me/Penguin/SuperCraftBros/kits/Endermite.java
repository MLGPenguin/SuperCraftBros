package me.Penguin.SuperCraftBros.kits;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.Penguin.SuperCraftBros.Main;
import me.Penguin.SuperCraftBros.Main.kitType;
import me.Penguin.SuperCraftBros.objects.Armour;
import me.Penguin.SuperCraftBros.objects.Kit;
import me.Penguin.SuperCraftBros.utils.MIB;
import me.Penguin.SuperCraftBros.utils.u;

public class Endermite extends Kit {
	
	public Endermite() {
		super(	kitType.ENDERMITE,
				"Endermite",
				new Armour(u.getHead("5bc7b9d36fb92b6bf292be73d32c6c5b0ecc25b44323a541fae1f1e67e393a3e"))
				);
	}
	
	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(	
			new MIB(Material.CHORUS_FLOWER).setName("&cboopy").setLocname("weapon1").addEnchant(Enchantment.DAMAGE_ALL, 1).addEnchant(Enchantment.KNOCKBACK, 2).build(),
			new MIB(Material.END_STONE, 3).setLocname("bonus").setName("&6SURPRISE").build()
		);
	}
	
	
	@Override
	public void RightClickWithBonusItem(PlayerInteractEvent e) {
		if (e.getItem().getItemMeta().getLocalizedName().equals("bonus")) {
			u.takeOneFromItemInHand(e.getPlayer());
			e.getPlayer().teleport(u.getClosestPlayer(e.getPlayer(), Main.getGame(e.getPlayer()).getAlivePlayersWithout(e.getPlayer())));
		}
	}
	
	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.SPEED, 1);
	}

}
