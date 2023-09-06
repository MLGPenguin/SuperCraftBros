package me.Penguin.SuperCraftBros.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class MIB {
	
	private Material mat;
	private int amount;
	private List<String> lore;
	private ItemMeta meta;
	private Map<Enchantment, Integer> enchants;	
	private ItemStack stack;
	private String name = null;
	private boolean hideEnchants, clearEnchants, glowing = false;
	private String locname = null;
	private HashMap<NamespacedKey, String> persistentStringData = new HashMap<>();
	
	
	// CONSTRUCTORS
	public MIB(Material type) {		
		mat = type; 
		amount = 1; 
		stack = new ItemStack(type, amount); 
		meta = stack.getItemMeta(); 
		enchants = new HashMap<>(); 
		lore = new ArrayList<>();
	}
	
	public MIB(Material type, int amount) {
		mat = type; 
		this.amount = amount; 
		stack = new ItemStack(type, amount); 
		meta = stack.getItemMeta(); 
		enchants = new HashMap<>();
		lore = new ArrayList<>();
	}
	
	public MIB(ItemStack is) {
		mat = is.getType(); 
		amount = is.getAmount(); 
		stack = is; meta = is.getItemMeta(); 
		enchants = is.getItemMeta().getEnchants().size() == 0 ? new HashMap<>() : is.getItemMeta().getEnchants();
		lore = meta.getLore();
	}

	public MIB copy() {
		return new MIB(mat, amount).setName(name).setLocname(locname).setLore(lore).setGlowing(glowing).setEnchants(enchants);
	}
	
	// GETTERS
	public int getAmount() { return amount; }
	public Material getType() { return mat; }
	
	// SETTERS
	public MIB setType(Material type) {
		this.mat = type; return this;
	}
	
	public MIB setName(String name) {
		this.name = name; 
		return this;
	}
	
	public MIB setAmount(int amount) {
		this.amount =amount; return this;
	}
	
	public MIB setGlowing(boolean glow) {
		this.glowing = glow;
		return this;
	}

	public MIB hideEnchants(boolean hide) {
		this.hideEnchants = hide; 
		return this;
	}
	
	public MIB setEnchants(Map<Enchantment, Integer> enchants) {
		this.enchants = enchants; return this;
	}
	
	public MIB clearEnchants(boolean clearEnchants) {
		this.clearEnchants = clearEnchants;
		return this;
	}
	
	public MIB addEnchant(Enchantment enchant, int level) {
		this.enchants.put(enchant, level);
		return this;
	}
	
	public MIB addPersistentStringData(NamespacedKey key, String data) {
		persistentStringData.put(key, data);
		return this;
	}
	
	public MIB addLores(String... lores) {
		if (this.lore == null) lore = new ArrayList<>();
		for (String x : lores) lore.add(u.cc(x));
		return this;
	}
	
	public MIB setLore(List<String> lore) {
		this.lore = lore;
		return this;
	}
	
	public MIB setLocname(String loc) {
		this.locname = loc;
		return this;
	}
	
	// BUILDER
	public ItemStack build() {
		stack.setAmount(amount);
		stack.setType(mat);
		if (enchants.size() > 0) {
			for (Enchantment e : enchants.keySet()) {
				meta.addEnchant(e, enchants.get(e), true);
			}
		}
		if (lore != null && lore.size() > 0) meta.setLore(lore);
		
		if (clearEnchants) {
			for (Enchantment e : meta.getEnchants().keySet()) meta.removeEnchant(e);			
		}
		if (persistentStringData.size() > 0) {
			for (NamespacedKey key : persistentStringData.keySet()) {
				PersistentDataContainer c = meta.getPersistentDataContainer();
				c.set(key, PersistentDataType.STRING, persistentStringData.get(key));
			}
		}
		if (glowing) {
			if (enchants == null || enchants.size() == 0) meta.addEnchant(Enchantment.DURABILITY, 1, true);
			hideEnchants = true;
		}
		if (name != null) meta.setDisplayName(u.hc(name));
		if (hideEnchants) meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		if (locname != null) meta.setLocalizedName(locname);
		
		stack.setItemMeta(meta);
		return stack;
	}

}
