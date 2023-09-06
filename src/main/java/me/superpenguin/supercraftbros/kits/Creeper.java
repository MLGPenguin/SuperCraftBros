package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.Main;
import me.superpenguin.supercraftbros.objects.Kit;
import me.superpenguin.supercraftbros.tags.ExplosionResistant;
import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import me.superpenguin.supercraftbros.objects.Armour;

public class Creeper extends Kit implements ExplosionResistant {
	
	public Creeper() {
		super(	Main.kitType.CREEPER,
				"Creeper",
				new Armour(u.getHead("f4254838c33ea227ffca223dddaabfe0b0215f70da649e944477f44370ca6952"))
				);
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.GUNPOWDER).setLocname("weapon1").setName("&bBoom Dust").addEnchant(Enchantment.KNOCKBACK, 1).addEnchant(Enchantment.DAMAGE_ALL, 2).build(),
				new MIB(Material.TNT, 2).setLocname("bonus").setName("&bInsta-Kill").build()
				);		
	}

	@Override
	public void RightClickWithBonusItem(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			e.setCancelled(true);
			Block toSummon = e.getClickedBlock().getRelative(e.getBlockFace());
			TNTPrimed p =(TNTPrimed)toSummon.getWorld().spawnEntity(toSummon.getLocation(), EntityType.PRIMED_TNT);
			p.getPersistentDataContainer().set(new NamespacedKey(Main.get(), "Creeper"), PersistentDataType.STRING, e.getPlayer().getName());
			p.setFuseTicks(20);
			u.takeOneFromItemInHand(e.getPlayer());
		}
	}
		
	
}
