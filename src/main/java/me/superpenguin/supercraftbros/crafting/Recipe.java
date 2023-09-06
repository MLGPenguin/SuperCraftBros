package me.superpenguin.supercraftbros.crafting;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Recipe {

	private Component[] grid;	
	
	public Recipe(Component[] grid) {
		this.grid = grid;
	}
	
	public Recipe(Material... materials) {
		assert materials.length == 9;
		grid = new Component[9];
		for (int i = 0 ; i < 9 ; i++) {
			Material m = materials[i];
			if (m == null) grid[i] = null;
			else grid[i] = new Component(null, new ItemStack(m));
		}	
	}
	
	public Component[] getGrid() {
		return grid;
	}
	
	

}
