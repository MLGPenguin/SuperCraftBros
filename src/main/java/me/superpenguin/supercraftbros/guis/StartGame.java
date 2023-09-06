package me.superpenguin.supercraftbros.guis;

import java.util.UUID;

import me.superpenguin.supercraftbros.customevents.CreateGameEvent;
import me.superpenguin.supercraftbros.objects.Game;
import me.superpenguin.supercraftbros.objects.Map;
import me.superpenguin.supercraftbros.utils.translator;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.superpenguin.supercraftbros.Main;
import me.superpenguin.supercraftbros.objects.GUI;
import me.superpenguin.supercraftbros.objects.Parties;

public class StartGame extends GUI {
	
	public StartGame() {
		super(GUIType.StartGame);	
	}
	
	public Inventory getInventory(Player p) {
		
		Inventory inv =  Bukkit.createInventory(null, 54, u.cc("&cSelect a Map..."));
		
		inv.setItem(0, Map.MapType.SNOW.getIcon());
		inv.setItem(1, Map.MapType.BASTION.getIcon());
		inv.setItem(2, Map.MapType.BLACKHOLE.getIcon());
		inv.setItem(3, Map.MapType.MUSHROOM.getIcon());
		inv.setItem(4, Map.MapType.BEACH.getIcon());
		inv.setItem(5, Map.MapType.JUNGLE.getIcon());
		inv.setItem(6, Map.MapType.END.getIcon());
		inv.setItem(7, Map.MapType.TEMPLE.getIcon());
		
		return inv;
	}

	@Override
	public void clicked(Player p, String locname) {
		Map.MapType map = translator.getMap(locname);
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
