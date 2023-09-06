package me.Penguin.SuperCraftBros.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum button {
	
		NEXT(new MIB(Material.PAPER).setName("&a&lNext Page").setLocname("next").build()), 
		PREVIOUS(new MIB(Material.PAPER).setName("&c&lPrevious Page").setLocname("previous").build()), 
		BACK(new MIB(Material.BARRIER).setName("&4&lBack").setLocname("back").build()), 
		PANE(new MIB(Material.BLACK_STAINED_GLASS_PANE).setName("&a").setLocname("pane").build());
		
		public ItemStack item;
		
		button(ItemStack item) {
			this.item = item;
		}
}


