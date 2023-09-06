package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.superpenguin.supercraftbros.Main;
import me.superpenguin.supercraftbros.Main.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;
import me.superpenguin.supercraftbros.objects.SPlayer;
import me.superpenguin.supercraftbros.utils.MIB;

public class Steve extends Kit {
	
	public Steve() {
		super(	kitType.STEVE,
				"Steve",
				new Armour(u.getHead("eb7af9e4411217c7de9c60acbd3c3fd6519783332a1b3bc56fbfce90721ef35"))
				);
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.DIAMOND_PICKAXE).setLocname("weapon1").setName("&BBludgeon").build(),
				new MIB(Material.ENDER_PEARL).setLocname("bonus2").build(),			
				new MIB(Material.LAVA_BUCKET).setLocname("bonus").build()	
				);		
	}

	@Override
	public void RightClickWithBonusItem(PlayerInteractEvent e) {
		ItemStack held = e.getPlayer().getInventory().getItemInMainHand();		
		if (held.getItemMeta().getLocalizedName().equals("bonus")) {
			e.setCancelled(true);
			if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				Block newPlace = e.getClickedBlock().getRelative(e.getBlockFace());
				if (newPlace.getType() == Material.AIR) {
					newPlace.setType(Material.LAVA);
					SPlayer sp = Main.getPlayer(e.getPlayer().getUniqueId());
					sp.addBlockToReplaceList(newPlace);
					held.setAmount(0);
				}				
			}
		}
	}

	@Override
	public void OnKitKilled(EntityDeathEvent e) {
		SPlayer sp =  Main.getPlayer(((Player)e.getEntity()).getUniqueId());
		for (Block b : sp.getReplaceWithAirOnDeathList()) b.setType(Material.AIR);
		sp.clearReplaceList();		
	}

}
