package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.utils.PIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import me.superpenguin.supercraftbros.SuperCraftBros.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;
import me.superpenguin.supercraftbros.utils.MIB;

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
