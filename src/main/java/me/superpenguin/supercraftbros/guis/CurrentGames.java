package me.superpenguin.supercraftbros.guis;

import java.util.UUID;

import me.superpenguin.supercraftbros.objects.Game;
import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.superpenguin.supercraftbros.SuperCraftBros;
import me.superpenguin.supercraftbros.objects.GUI;
import me.superpenguin.supercraftbros.objects.Parties;

public class CurrentGames extends GUI {
	
	public CurrentGames() {
		super(GUIType.CurrentGames);
	}
	
	
	public Inventory getInventory(Player p) {
		Inventory inv = Bukkit.createInventory(null, 54, u.cc("&aOngoing Games"));
		
		for (int i : SuperCraftBros.runningGames.keySet()) {
			Game g = SuperCraftBros.runningGames.get(i);
			inv.addItem(
					new MIB(g.getMap().getIcon().getType()).addLores(
							"&aStatus: &c&l" + g.getState().toString(),
							"&aPlayers: &c" + g.getPlayers().size(),
							"&7",
							"&7&l* &bClick to join"
							).setLocname(String.valueOf(i))
					.setName(g.getMap().getName())
					.build()					
					);
		}		
		return inv;
	}
	
	@Override
	public void clicked(Player p, String locname) {
		UUID uuid = p.getUniqueId();
		if (SuperCraftBros.getGame(p) == null) {
			int ID = -1;
			try { ID = Integer.parseInt(locname); } catch (NumberFormatException ex) { return; }
			Game g = SuperCraftBros.getGame(ID);
			if (g.getState() == Game.gamestate.LOBBY) {
				if (Parties.isInParty(uuid) && !(Parties.getParty(uuid).isLeader(uuid))) {
					p.sendMessage(u.cc("&cWait for your party leader to start a game"));
					return;
				}
				if (4-g.getPlayers().size() >= Parties.getRequiredJoiners(p).size()) {
					g.addPlayersToGame(Parties.getRequiredJoiners(p));
					return;
				} else p.sendMessage(u.cc("&cThis game is full!"));
			} else p.sendMessage(u.cc("&cThis game has already started"));
		} else p.sendMessage(u.cc("&cYou are already in a game"));	
	}
	
	
	


}
