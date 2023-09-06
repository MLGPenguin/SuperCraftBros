package me.superpenguin.supercraftbros.guis.party;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.superpenguin.supercraftbros.interfaces.Paged;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import me.superpenguin.supercraftbros.objects.GUI;
import me.superpenguin.supercraftbros.objects.Parties;
import me.superpenguin.supercraftbros.utils.button;

public class partyPlayerList extends GUI implements Paged {
	
	private int page;
	
	public partyPlayerList() {
		super(GUIType.partyPlayerList);
		page = 1;
	}
	
	@Override
	public Inventory getInventory(Player p) {
		Inventory inv = Bukkit.createInventory(null, 54, u.cc("&bInvite Players"));
		List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
		players.remove(p);
		int slot = 0;
		for (int i = (page-1)*45 ; i < page*45 && i < players.size(); i++) {
			inv.setItem(slot, getHead(players.get(i)));
			slot++;
		}
		
		inv.setItem(45,button.PREVIOUS.item);
		inv.setItem(53, button.NEXT.item);
		ItemStack pane = button.PANE.item;
		for (int i = 46 ; i < 53 ; i++) inv.setItem(i, pane);
		
		return inv;
	}
	
	@Override
	public void clicked(Player p, String locname) {
		if (locname.equalsIgnoreCase("next")) {
			if (hasNextPage()) u.openLaterandAdd(p, nextPage());
		} else if (locname.equalsIgnoreCase("previous")) {
			if (page > 1) u.openLaterandAdd(p, previousPage());
		} else {
			OfflinePlayer op = Bukkit.getOfflinePlayer(UUID.fromString(locname));
			if (op.isOnline()) {
				Player invited = op.getPlayer();
				Parties.invite(p, invited);
			}
		}
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
	public int getPage() {
		return page;		
	}

	@Override
	public boolean hasNextPage() {
		return ((double) (Bukkit.getOnlinePlayers().size()-1)/45) > page;
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
	

}
