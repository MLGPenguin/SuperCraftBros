package me.Penguin.SuperCraftBros.kits;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import me.Penguin.SuperCraftBros.Main;
import me.Penguin.SuperCraftBros.Main.kitType;
import me.Penguin.SuperCraftBros.customevents.DropBelowHalfHealthEvent;
import me.Penguin.SuperCraftBros.objects.Armour;
import me.Penguin.SuperCraftBros.objects.Game;
import me.Penguin.SuperCraftBros.objects.Kit;
import me.Penguin.SuperCraftBros.objects.SPlayer;
import me.Penguin.SuperCraftBros.utils.MIB;
import me.Penguin.SuperCraftBros.utils.u;

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
		SPlayer s = Main.getPlayer(e.getPlayer().getUniqueId());
		if (s.hasKit() && s.getKit().getType() == kitType.WOLF) {
			Location l = e.getPlayer().getLocation();
			Game g = Main.getGame(e.getPlayer());
			for (int i = 0 ; i < 3; i++) {
				org.bukkit.entity.Wolf wolf = (org.bukkit.entity.Wolf) l.getWorld().spawnEntity(l, EntityType.WOLF);
				wolf.setOwner(e.getPlayer());
				g.addSpawnedEntity(wolf);
			}
		}
	}

	
	

}
