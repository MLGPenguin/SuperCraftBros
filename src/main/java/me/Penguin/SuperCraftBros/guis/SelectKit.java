package me.Penguin.SuperCraftBros.guis;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.Penguin.SuperCraftBros.Main;
import me.Penguin.SuperCraftBros.interfaces.Paged;
import me.Penguin.SuperCraftBros.objects.GUI;
import me.Penguin.SuperCraftBros.objects.Kit;
import me.Penguin.SuperCraftBros.objects.SPlayer;
import me.Penguin.SuperCraftBros.utils.translator;
import me.Penguin.SuperCraftBros.utils.u;

public class SelectKit extends GUI implements Paged {
	
	private int page;
	
	public SelectKit() {
		super(GUIType.SelectKit);
		page = 1;
	}
	
	public Inventory getInventory(Player p) {
		return Main.getKitInventory(page);
	}

	@Override
	public int getPage() {
		return page;		
	}

	@Override
	public boolean hasNextPage() {
		return Main.getPages() > page;
	}
	
	@Override
	public GUI nextPage() {
		if (hasNextPage()) page++;
		return this;
	}

	@Override
	public GUI previousPage() {
		if (page > 1) page--;
		return this;
	}
	
	@Override
	public void clicked(Player p, String locname) {
		if (locname.equals("next")) {
			if (hasNextPage()) u.openLaterandAdd(p, nextPage());
		} else if (locname.equals("previous")) {
			if (page > 1) u.openLaterandAdd(p, previousPage());						
		} else if (locname.equals("sort")) {
			
		} else {
			Kit kit = translator.getKit(locname);
			if (kit != null) {
				SPlayer sp = Main.getPlayer(p.getUniqueId());
				sp.setKit(kit);
				p.sendMessage(u.cc("&bSelected " + kit.getName() + " kit"));
			}
		}
	}
	

}
