package me.superpenguin.supercraftbros.crafting;

import me.superpenguin.supercraftbros.utils.MIB;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class Component {
	
	private boolean hasRecipe;
	private Recipe recipe;
	private ItemStack item;	
	
	public Component(Recipe recipe, ItemStack result) {
		this.hasRecipe = recipe != null;
		this.recipe = recipe;
		this.item = result;
	}
	
	public boolean hasRecipe() { return hasRecipe; }
	public Recipe getRecipe() { return recipe; }
	public ItemStack getItem() { return item; }
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Component)) return false;
		Component b = (Component) o;
		if (!hasRecipe) {
			if (!b.hasRecipe && item.equals(b.getItem())) return true;
		} else if (b.hasRecipe && recipe.equals(b.getRecipe()) && b.getItem().equals(item)) return true;
		return false;
	}
	
	
	
	public enum components {
		BIGDIRT(new Component(
				new Recipe(
				Material.DIRT, Material.DIRT, Material.DIRT,
				Material.DIRT, Material.DIRT, Material.DIRT,
				Material.DIRT, Material.DIRT, Material.DIRT),
				new MIB(Material.DIRT).setName("&cBig Dirt").addEnchant(Enchantment.DURABILITY, 1).hideEnchants(true).setLocname("BIGDIRT").build()
				));
		
		private Component comp;
		
		components(Component component) {
			this.comp = component;
		}
		
		public Component getComponent() { return comp; }
	}
	

	
	
}
