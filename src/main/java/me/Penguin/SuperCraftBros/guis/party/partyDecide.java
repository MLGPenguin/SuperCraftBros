package me.Penguin.SuperCraftBros.guis.party;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.Penguin.SuperCraftBros.objects.GUI;
import me.Penguin.SuperCraftBros.objects.Parties;
import me.Penguin.SuperCraftBros.objects.Party;
import me.Penguin.SuperCraftBros.utils.MIB;
import me.Penguin.SuperCraftBros.utils.u;

public class partyDecide extends GUI {
	
	private UUID partyOwner;
	
	public partyDecide(UUID owner) {
		super(GUIType.PARTYDECIDE);
		this.partyOwner = owner;
	}

	@Override
	public Inventory getInventory(Player p) {
		Inventory inv = Bukkit.createInventory(null, 54, u.cc("&b" + Bukkit.getPlayer(partyOwner).getName() + "'s Party Invite"));
		
		inv.setItem(19, new MIB(Material.GREEN_WOOL).setName("&a&LAccept").setLocname("accept").build());
		inv.setItem(25, new MIB(Material.RED_WOOL).setName("&c&lDecline").setLocname("decline").build());
		
		return inv;
		
	}

	@Override
	public void clicked(Player p, String locname) {
		Party o = Parties.getParty(partyOwner);
		if (locname.equals("accept")) {
			o.addMember(p);
			p.sendMessage(u.cc("&aYou have joined " + o.getLeader().getName() + "'s party"));
			o.broadcast("&a" + p.getName() + " has joined your party");
		} else if (locname.equals("decline")) {
			Parties.removeInvite(p, o);
			p.sendMessage(u.cc("&cYou have declined " + o.getLeader().getName() + "'s party invite"));
			o.getLeader().sendMessage(u.cc("&c" + p.getName() + " has declined your party invite"));
		}
		p.closeInventory();
	}
	
	
		

}
