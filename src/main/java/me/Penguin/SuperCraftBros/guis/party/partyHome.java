package me.Penguin.SuperCraftBros.guis.party;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.Penguin.SuperCraftBros.objects.GUI;
import me.Penguin.SuperCraftBros.objects.Parties;
import me.Penguin.SuperCraftBros.utils.MIB;
import me.Penguin.SuperCraftBros.utils.u;

public class partyHome extends GUI {
	
	public partyHome() {
		super(GUIType.partyHome);
	}
	
	@Override
	public Inventory getInventory(Player p) {
		
		Inventory inv = Bukkit.createInventory(null, 54, u.cc("&aParty Hub"));
		
		inv.setItem(19, new MIB(Material.BOOK).setName("&aInvites").setLocname("invites")
				.addLores("&aYou have " + Parties.getAmountofInvites(p.getUniqueId()) + " invites").build());
		inv.setItem(25, new MIB(Material.PAPER).setName("&aInvite Someone").setLocname("inviting").build());
		if (Parties.isInParty(p.getUniqueId())) 
			inv.setItem(40, new MIB(Material.WRITABLE_BOOK).setName("&6Party Members").setLocname("partymembers").build());
				
		return inv;
	}
	
	@Override
	public void clicked(Player p, String locname) {
		switch (locname) {
		case "invites":
			if (Parties.getAmountofInvites(p.getUniqueId()) == 0) {
				p.sendMessage(u.cc("&cYou have no invites"));
				return;
			}
			u.openLaterandAdd(p, new partyInvites());
			return;
		case "inviting":
			if (Bukkit.getOnlinePlayers().size() == 1) {
				p.sendMessage(u.cc("&cThere are no other players online"));
				return;
			}
			u.openLaterandAdd(p, new partyPlayerList());
			return;
		case "partymembers":
			u.openLaterandAdd(p, new partyMemberList());
			return;
		}
	}
	

}
