package me.superpenguin.supercraftbros.crafting;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.superpenguin.supercraftbros.crafting.Component.components;

public class craftingutil {

	static List<Integer> grid = Arrays.asList(3,4,5,12,13,14,21,22,23);
	
	public static void clearGrid(Inventory inv) {		
		for ( int i : grid ) inv.setItem(i, null);		
	}
	
	public static List<Integer> getPaneSlots() {
		return Arrays.asList(0,1,2,6,7,8,9,10,11,15,17,18,19,20,24,25,26);
	}
	
	// If required, can easily be optimised by checking null values and storing all possible 'components' materials in a list on startup;
	public static Component ItemToComponent(ItemStack item) {
		if (u.isItem(item)) {
			if (item.getItemMeta().hasLocalizedName()) {
				components c;
				try {
					c = components.valueOf(item.getItemMeta().getLocalizedName());
					return c.getComponent();
				} catch(EnumConstantNotPresentException e) {}
			}
			return new Component(null, item);
		} else return null;
	}
	
	public static Component[] getGrid(Inventory inv) {
		Component[] array = new Component[9];
		for (int i = 0 ; i < 9 ; i++) {
			int slot = grid.get(i);
			array[i] = ItemToComponent(inv.getItem(slot));
		}
		return array;
	}
	
	public static void UpdateResult(Inventory inv) {
		components found = null;
		for (components c : components.values()) {
			int matches = 0;
			Component[] array = c.getComponent().getRecipe().getGrid();
			Component[] array2 = getGrid(inv);
			for (int i = 0 ; i < 9 ; i++) {
				if (array[i].equals(array2[i])) matches++;				
			}			
			if (matches == 9) {
				found = c;
				break;
			}
			matches = 0;
		}
		if (found == null) inv.setItem(16, null);
		else inv.setItem(16, found.getComponent().getItem());
	}
	
	
}
