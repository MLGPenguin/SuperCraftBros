package me.superpenguin.supercraftbros.guis.party;

import java.util.UUID;

import me.superpenguin.supercraftbros.objects.Party;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import me.superpenguin.supercraftbros.objects.GUI;
import me.superpenguin.supercraftbros.objects.Parties;

public class partyInvites extends GUI {
	
	public partyInvites() {
		super(GUIType.PARTYINVITES);
	}

	@Override
	public Inventory getInventory(Player p) {
			Inventory inv = Bukkit.createInventory(null, 54, u.cc("&bParty Invites"));
			int slot = 0;
			for (Party o : Parties.getInvites(p.getUniqueId())) {
				inv.setItem(slot, getHead(o.getLeader()));
			}
			
			return inv;	
	}
	
	private ItemStack getHead(Player p) {
		ItemStack s = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta m = (SkullMeta) s.getItemMeta();
		m.setOwningPlayer(p);
		m.setLocalizedName(p.getUniqueId().toString());
		s.setItemMeta(m);
		return s;
	}

	@Override
	public void clicked(Player p, String locname) {
		u.openLaterandAdd(p, new partyDecide(UUID.fromString(locname)));		
	}
	
	

}
