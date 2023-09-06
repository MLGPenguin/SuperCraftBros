package me.Penguin.SuperCraftBros.guis;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.Penguin.SuperCraftBros.Main;
import me.Penguin.SuperCraftBros.crafting.craftingutil;
import me.Penguin.SuperCraftBros.objects.GUI;
import me.Penguin.SuperCraftBros.utils.button;
import me.Penguin.SuperCraftBros.utils.u;

public class Crafting extends GUI {

	public Crafting() {
		super(GUIType.CRAFTING);
	}

	@Override
	public Inventory getInventory(Player p) {
		Inventory inv = Bukkit.createInventory(null, 27, u.cc("&aThis is a crafting Inventory"));
		List<Integer> paneSlots = craftingutil.getPaneSlots();
		ItemStack pane = button.PANE.item;
		for (int i : paneSlots) inv.setItem(i, pane);
		return inv;		
	}

	@Override
	public void clicked(Player p, String locname) {}
	
	
	@Override
	public void clicked(InventoryClickEvent e) {
		Inventory inv = e.getClickedInventory();
		// NEED TO MAKE SURE THEY RETAIN THEIR ITEMS WHEN THEY CLOSE THE INVENTORY
		// ALSO NEED TO LISTEN TO INVENTORY DRAG EVENT
		int slot = e.getSlot();
		if (craftingutil.getPaneSlots().contains(slot)) {
			e.setCancelled(true);
			return;
		}
		// When they click on the result slot, wait 1 tick, and if the item is gone, clear the grid.
		else if (slot == 16) {
			if (!u.isItem(e.getCursor()) && u.isItem(e.getCurrentItem())) {
				new BukkitRunnable() {					
					@Override
					public void run() {
						ItemStack item = e.getInventory().getItem(16);
						if (!u.isItem(item)) {					
							craftingutil.clearGrid(e.getClickedInventory());
							return;
						}
					}
				}.runTaskLater(Main.get(), 1);
			} else e.setCancelled(true);
		} else {
			new BukkitRunnable() {				
				@Override
				public void run() {
					craftingutil.UpdateResult(inv);
				}
			}.runTaskLater(Main.get(), 1);
		}
	}
	
	
	
}
