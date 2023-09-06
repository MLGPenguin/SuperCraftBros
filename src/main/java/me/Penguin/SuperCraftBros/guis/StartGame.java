package me.Penguin.SuperCraftBros.guis;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.Penguin.SuperCraftBros.Main;
import me.Penguin.SuperCraftBros.customevents.CreateGameEvent;
import me.Penguin.SuperCraftBros.objects.GUI;
import me.Penguin.SuperCraftBros.objects.Game;
import me.Penguin.SuperCraftBros.objects.Map.MapType;
import me.Penguin.SuperCraftBros.objects.Parties;
import me.Penguin.SuperCraftBros.utils.translator;
import me.Penguin.SuperCraftBros.utils.u;

public class StartGame extends GUI {
	
	public StartGame() {
		super(GUIType.StartGame);	
	}
	
	public Inventory getInventory(Player p) {
		
		Inventory inv =  Bukkit.createInventory(null, 54, u.cc("&cSelect a Map..."));
		
		inv.setItem(0, MapType.SNOW.getIcon());
		inv.setItem(1, MapType.BASTION.getIcon());
		inv.setItem(2, MapType.BLACKHOLE.getIcon());
		inv.setItem(3, MapType.MUSHROOM.getIcon());
		inv.setItem(4, MapType.BEACH.getIcon());
		inv.setItem(5, MapType.JUNGLE.getIcon());
		inv.setItem(6, MapType.END.getIcon());
		inv.setItem(7, MapType.TEMPLE.getIcon());
		
		return inv;
	}

	@Override
	public void clicked(Player p, String locname) {
		MapType map = translator.getMap(locname);
		UUID uuid = p.getUniqueId();
		if (map != null) {
			if (Main.getGame(p) == null) {
				if (Parties.isInParty(uuid) && !(Parties.getParty(uuid).isLeader(uuid))) {
					p.sendMessage(u.cc("&cWait for your party leader to start a game"));
					return;
				}
				Game game = new Game(Parties.getRequiredJoiners(p), map);
				game.startLobby(p);
				CreateGameEvent event = new CreateGameEvent(p, game);
				Bukkit.getPluginManager().callEvent(event);
			} else p.sendMessage(u.cc("&cYou are already in a game."));							
		}
	}
	
}
