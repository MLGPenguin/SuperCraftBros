package me.superpenguin.supercraftbros.guis;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.superpenguin.supercraftbros.objects.GUI;
import me.superpenguin.supercraftbros.utils.button;

public class BackPack extends GUI {
	
	public BackPack() {
		super(GUIType.BACKPACK);
	}

	@Override
	public Inventory getInventory(Player p) {
		Inventory inv = Bukkit.createInventory(null, 54, u.cc("&aWelcome to your Backpack"));
		List<Integer> panes = Arrays.asList(0,1,2,3,4,5,6,7,8,45,46,47,48,49,50,51,52,53);
		ItemStack pane = button.PANE.item;
		MIB catClaw = new MIB(Material.QUARTZ).setName("&c&lCat Claw").setLocname("CATCLAW").setGlowing(true);
		for (int i : panes) inv.setItem(i, pane);
		inv.setItem(9, catClaw.addLores("&bYou have 1").build());
		
		return inv;
	}

	@Override
	public void clicked(Player p, String locname) {}

}
