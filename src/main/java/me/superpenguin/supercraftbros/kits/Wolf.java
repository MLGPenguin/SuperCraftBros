package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.customevents.DropBelowHalfHealthEvent;
import me.superpenguin.supercraftbros.objects.Game;
import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import me.superpenguin.supercraftbros.SuperCraftBros;
import me.superpenguin.supercraftbros.SuperCraftBros.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;
import me.superpenguin.supercraftbros.objects.SPlayer;

public class Wolf extends Kit {
	
	public Wolf() {
		super(	kitType.WOLF,
				"Wolf",
				new Armour(u.getHead("24d7727f52354d24a64bd6602a0ce71a7b484d05963da83b470360faa9ceab5f"))
				);
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.BONE).setLocname("weapon1").setName("&bPointy Bone").addEnchant(Enchantment.DAMAGE_ALL, 2).build()
				);		
	}
	
	@Override
	public void dropBelowHalfHealth(DropBelowHalfHealthEvent e) {
		SPlayer s = SuperCraftBros.getPlayer(e.getPlayer().getUniqueId());
		if (s.hasKit() && s.getKit().getType() == kitType.WOLF) {
			Location l = e.getPlayer().getLocation();
			Game g = SuperCraftBros.getGame(e.getPlayer());
			for (int i = 0 ; i < 3; i++) {
				org.bukkit.entity.Wolf wolf = (org.bukkit.entity.Wolf) l.getWorld().spawnEntity(l, EntityType.WOLF);
				wolf.setOwner(e.getPlayer());
				g.addSpawnedEntity(wolf);
			}
		}
	}

	
	

}
