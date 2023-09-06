package me.Penguin.SuperCraftBros.kits;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.Penguin.SuperCraftBros.Main.kitType;
import me.Penguin.SuperCraftBros.objects.Armour;
import me.Penguin.SuperCraftBros.objects.Kit;
import me.Penguin.SuperCraftBros.utils.MIB;
import me.Penguin.SuperCraftBros.utils.u;

public class Warrior extends Kit {
	
	public Warrior() {
		super(	kitType.WARRIOR,
				"Warrior",
				new Armour(u.getHead("14ea1d97a3cb11aeba23b6065101d7bbdc607bbbbb0cc8796fccf87749dcd177"))
				);
	}
	
	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.STONE_SWORD).setLocname("weapon1").build(),
				new MIB(Material.SHIELD).setLocname("bonus").build()
				);
	}
	
	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.SLOW, 1);
	}

}
