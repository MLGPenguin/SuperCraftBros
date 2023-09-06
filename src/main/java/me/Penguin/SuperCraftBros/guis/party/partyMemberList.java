package me.Penguin.SuperCraftBros.guis.party;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import me.Penguin.SuperCraftBros.objects.GUI;
import me.Penguin.SuperCraftBros.objects.Parties;
import me.Penguin.SuperCraftBros.utils.u;

public class partyMemberList extends GUI {
	
	public partyMemberList() {
		super(GUIType.PARTYMEMBERLIST);
	}
	
	@Override
	public Inventory getInventory(Player p) {
		Inventory inv = Bukkit.createInventory(null, 54, u.cc("&bParty Members"));
		List<Player> players = new ArrayList<>(Parties.getParty(p.getUniqueId()).getAllPlayers());	
		int slot = 0;
		for (Player t : players) {
			inv.setItem(slot, getHead(t));
			slot++;
		}
		
		return inv;
	}
	
	private ItemStack getHead(Player p) {
		ItemStack s = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta m = (SkullMeta) s.getItemMeta();
		m.setOwningPlayer(p);
		m.setDisplayName(u.cc("&b" + p.getName()));
		m.setLocalizedName(p.getUniqueId().toString());
		s.setItemMeta(m);
		return s;
	}
	
	@Override
	public void clicked(Player p, String locname) {}

}
