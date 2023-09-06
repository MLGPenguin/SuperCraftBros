package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.SuperCraftBros.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;

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
