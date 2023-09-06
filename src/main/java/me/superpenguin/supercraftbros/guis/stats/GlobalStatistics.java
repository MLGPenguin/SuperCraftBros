package me.superpenguin.supercraftbros.guis.stats;

import me.superpenguin.supercraftbros.objects.IPlayer;
import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.superpenguin.supercraftbros.SuperCraftBros;
import me.superpenguin.supercraftbros.objects.GUI;

public class GlobalStatistics extends GUI {
	 
	public GlobalStatistics() {
		super(GUIType.GLOBALSTATS);
	}

	@Override
	public Inventory getInventory(Player p) {
		Inventory inv = Bukkit.createInventory(null, 54, u.cc("&bStatistics"));
		
		IPlayer ip = SuperCraftBros.getPersistentPlayer(p.getUniqueId());
		
		inv.setItem(0, new MIB(Material.IRON_SWORD).setName("&4Kills").addLores("&bYou have " + ip.getKills() + " kills").setLocname("kills").build());
		inv.setItem(1, new MIB(Material.BARRIER).setName("&4Deaths").addLores("&bYou have " + ip.getDeaths() + " deaths").build());
		inv.setItem(2, new MIB(Material.TOTEM_OF_UNDYING).setName("&4Wins").addLores("&bYou have " + ip.getWins() + " wins").build());
		inv.setItem(3, new MIB(Material.FLOWER_POT).setName("&4Games Played").addLores("&bYou have played " + ip.getGamesPlayed() + " games").build());
		inv.setItem(4, new MIB(Material.DIAMOND_SWORD).setName("&4Damage Done").addLores("&bYou have done " + u.dc(ip.getDamageDone()/2) + " hearts of damage").build());
		inv.setItem(5, new MIB(Material.CLOCK).setName("&4Time Lived &c&o(W.I.P)").addLores("&bYou have lived for " + u.dc(ip.getmsLived()/1000) + " seconds").build());
		inv.setItem(6, new MIB(Material.WATER_BUCKET).setName("&4Suicides &c&o(W.I.P)").addLores("&bYou have killed yourself " + u.dc(ip.getSuicides()) + " times").build());
		
		// etc.
		
		return inv;
	}

	@Override
	public void clicked(Player p, String locname) {
		switch (locname) {
		default: return;
		}
	}
	
	

}
