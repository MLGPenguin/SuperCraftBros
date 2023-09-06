package me.Penguin.SuperCraftBros.kits;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import me.Penguin.SuperCraftBros.Main.kitType;
import me.Penguin.SuperCraftBros.objects.Armour;
import me.Penguin.SuperCraftBros.objects.Kit;
import me.Penguin.SuperCraftBros.utils.MIB;
import me.Penguin.SuperCraftBros.utils.PIB;
import me.Penguin.SuperCraftBros.utils.u;

public class Alex extends Kit {
	
	public Alex() {
		super(	kitType.ALEX,
				"Alex",
				new Armour(u.getHead("a2f71de1fd66e37362cdd86589e9b34e847999e232aa717ebbc86330ffb4f637"))
				);	
	}
	
	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.OAK_PLANKS).setLocname("weapon1").addEnchant(Enchantment.KNOCKBACK, 2).build(),
				new MIB(Material.ENDER_PEARL).setLocname("bonus").setName("&6woosh").build(),
				((PIB)new PIB(Material.POTION, 1, PotionEffectType.HEAL, 3, 1).setBasePotionData(PotionType.INSTANT_HEAL).setLocname("bonus2").setName("&7Health	")).buildPotion()			
				);		
	}

}
