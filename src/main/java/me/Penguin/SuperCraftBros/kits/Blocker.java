package me.Penguin.SuperCraftBros.kits;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Penguin.SuperCraftBros.Main.kitType;
import me.Penguin.SuperCraftBros.objects.Armour;
import me.Penguin.SuperCraftBros.objects.Kit;
import me.Penguin.SuperCraftBros.utils.MIB;
import me.Penguin.SuperCraftBros.utils.u;

public class Blocker extends Kit {
	
	public Blocker() {
		super(	kitType.BLOCKER,
				"Blocker",
				new Armour(u.getHead("92430a916151427c6c871fe2f6b5a4e7d17794ac9775a87fbc303e13e5bca29f"))
				);
	}
	
	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.BRICK).addEnchant(Enchantment.KNOCKBACK, 2).setLocname("weapon1").setName("&bConcrete").build()
				);
	}
	
	@Override
	public void activatePerks(Player p) {
		p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
	}
	
	@Override
	public void removePerks(Player p) {
		p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(0);
	}

}
