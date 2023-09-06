package me.Penguin.SuperCraftBros.utils;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class PIB extends MIB {
	
	private PotionEffectType type;
	private int time, amplifier;
	private PotionData potiondata = null;
	private HashMap<PotionEffectType, Integer> extra = new HashMap<>();
	
	public PIB(Material mat, int amount, PotionEffectType type, int amplifier, int seconds) {
		super(mat, amount);
		this.type = type;
		this.time = 20 * seconds;
		this.amplifier = amplifier -1;
	}
	public PIB(Material mat, PotionEffectType type, int amplifier, int seconds) {
		super(mat, 1);
		this.type = type;
		this.time = 20 * seconds;
		this.amplifier = amplifier -1;
	}
	
	public PIB setPotionType(PotionEffectType type) {
		this.type = type;
		return this;
	}
	
	public PIB addPotionType(PotionEffectType type, int strength) {
		extra.put(type, strength);
		return this;
	}
			
	
	public PIB setTime(int seconds) {
		this.time = 20 *seconds;
		return this;
	}
	
	public PIB setAmplifier(int amplifier) {
		this.amplifier = amplifier-1;
		return this;
	}
	
	public PIB setBasePotionData(PotionType type) {
		this.potiondata = new PotionData(type);
		return this;
	}
	

	public ItemStack buildPotion() {
		ItemStack stack = build();
		ItemMeta meta = stack.getItemMeta();
		PotionMeta pmeta = (PotionMeta) meta;
		pmeta.addCustomEffect(new PotionEffect(type, time, amplifier), false);
		for (PotionEffectType s : extra.keySet()) pmeta.addCustomEffect(new PotionEffect(s, time, extra.get(s)-1), false);
		pmeta.setColor(type.getColor());
		stack.setItemMeta(pmeta);
		return stack;
		
	}

}
