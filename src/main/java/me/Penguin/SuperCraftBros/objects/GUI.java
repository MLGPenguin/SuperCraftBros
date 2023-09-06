package me.Penguin.SuperCraftBros.objects;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public abstract class GUI {

	protected GUIType type;
	
	public GUI(GUIType type) {
		this.type = type;
	}
	
	public abstract Inventory getInventory(Player p);
	public abstract void clicked(Player p, String locname);
	public void clicked(InventoryClickEvent e) {}
	public GUIType getType() { return type; }
	
	public enum GUIType {
		SelectKit, CurrentGames, StartGame, ViewKits, partyHome, partyPlayerList, PARTYINVITES, 
		PARTYDECIDE, PARTYMEMBERLIST, GLOBALSTATS, CLASSSTATS, CRAFTING, BACKPACK;
	}
	
}
