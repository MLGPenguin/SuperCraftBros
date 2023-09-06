package me.Penguin.SuperCraftBros.guis;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.Penguin.SuperCraftBros.Main;
import me.Penguin.SuperCraftBros.objects.GUI;
import me.Penguin.SuperCraftBros.objects.Game;
import me.Penguin.SuperCraftBros.objects.Game.gamestate;
import me.Penguin.SuperCraftBros.objects.Parties;
import me.Penguin.SuperCraftBros.utils.MIB;
import me.Penguin.SuperCraftBros.utils.u;

public class CurrentGames extends GUI {
	
	public CurrentGames() {
		super(GUIType.CurrentGames);
	}
	
	
	public Inventory getInventory(Player p) {
		Inventory inv = Bukkit.createInventory(null, 54, u.cc("&aOngoing Games"));
		
		for (int i : Main.runningGames.keySet()) {
			Game g = Main.runningGames.get(i);
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
		if (Main.getGame(p) == null) {
			int ID = -1;
			try { ID = Integer.parseInt(locname); } catch (NumberFormatException ex) { return; }
			Game g = Main.getGame(ID);
			if (g.getState() == gamestate.LOBBY) {
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
