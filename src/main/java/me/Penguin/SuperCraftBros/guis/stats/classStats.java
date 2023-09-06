package me.Penguin.SuperCraftBros.guis.stats;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.Penguin.SuperCraftBros.objects.GUI;
import me.Penguin.SuperCraftBros.utils.u;

public class classStats extends GUI {

	public classStats() {
		super(GUIType.CLASSSTATS);
	}
	
	@Override
	public Inventory getInventory(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9, u.cc("&bStatistics"));
		
		
		return inv;
	}

	@Override
	public void clicked(Player p, String locname) {
		switch (locname) {
		
		}
	}
	
	
}
