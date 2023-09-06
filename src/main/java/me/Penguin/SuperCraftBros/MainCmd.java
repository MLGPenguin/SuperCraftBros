package me.Penguin.SuperCraftBros;

import java.util.List;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import me.Penguin.SuperCraftBros.guis.BackPack;
import me.Penguin.SuperCraftBros.guis.Crafting;
import me.Penguin.SuperCraftBros.guis.CurrentGames;
import me.Penguin.SuperCraftBros.guis.StartGame;
import me.Penguin.SuperCraftBros.guis.ViewingKits;
import me.Penguin.SuperCraftBros.guis.party.partyHome;
import me.Penguin.SuperCraftBros.guis.stats.GlobalStatistics;
import me.Penguin.SuperCraftBros.objects.Parties;
import me.Penguin.SuperCraftBros.objects.Party;
import me.Penguin.SuperCraftBros.utils.u;

public class MainCmd implements TabExecutor {
	@SuppressWarnings("unused")
	private Main plugin;
	
	public MainCmd(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("startgame").setExecutor(this);
		plugin.getCommand("kitsomething").setExecutor(this);
		plugin.getCommand("games").setExecutor(this);
		plugin.getCommand("gems").setExecutor(this);
		plugin.getCommand("party").setExecutor(this);
		plugin.getCommand("statistics").setExecutor(this);
		plugin.getCommand("customcrafting").setExecutor(this);
		plugin.getCommand("backpack").setExecutor(this);
	}

	@Override
	public boolean onCommand( CommandSender s,  Command cmd,  String label, String[] args) {
		if (s instanceof Player) {
			Player p = (Player) s;
			UUID uuid = p.getUniqueId();
		 if (cmd.getName().equalsIgnoreCase("startgame")) {
				if (p.hasPermission("supercraftbros.admin")) {
					if (args.length == 0) {
						u.openLaterandAdd(p, new StartGame());
						return true;
					}
				}
			} else if (cmd.getName().equalsIgnoreCase("kitsomething")) {
				if (args.length == 0) {
					if (s instanceof Player) {
						u.openLaterandAdd(p, new ViewingKits());
						return true;
					}				
				}
			} else if (cmd.getName().equalsIgnoreCase("games")) {
				if (args.length == 0) {
					u.openLaterandAdd(p, new CurrentGames());
					return true;
				}
			} else if (cmd.getName().equalsIgnoreCase("gems")) {
				if (args.length == 0) {
					p.sendMessage(u.cc("&aYou have &a&n" + u.dc(Main.getPersistentPlayer(p.getUniqueId()).getGems()) + "&a Gems"));
					return true;
				}
			} else if (cmd.getName().equalsIgnoreCase("party")) {
				if (args.length == 0) {
					u.openLaterandAdd(p, new partyHome());
					return true;
				} else if (args.length == 2) {
					if (args[0].equalsIgnoreCase("invite")) {
						if (u.isPlayer(args[1])) {
							Player invited = u.getPlayer(args[1]);
							Parties.invite(p, invited);
							return true;
						} else send(p, "&cPlease specify a valid player");
					} else if (args[0].equalsIgnoreCase("kick")) {
						if (Parties.isInParty(uuid)) {
							Party o = Parties.getParty(uuid);
							if (o.isLeader(uuid)) {
								if (u.isPlayer(args[1])) {
									Player kicked = u.getPlayer(args[1]);
									if (!kicked.getUniqueId().equals(p.getUniqueId())) {
										if (o.hasPlayer(kicked.getUniqueId())) {
											o.removePlayer(uuid);
											kicked.sendMessage(u.cc("&cYou have been kicked from your current party"));
											p.sendMessage(u.cc("&cYou have kicked " + p.getName() + " from your party"));
											return true;
										} else send(p, "&cThis player is not in your party");
									} else send(p, "&c/Party leave");									
								} else send(p, "&cPlease specify a valid player");
							} else send(p, "&cYou must be the leader of the party to kick people");
						} else send(p, "&cYou are not in a party");
						return true;
					}
				} else if (args[0].equalsIgnoreCase("leave")) {			
					Party o = Parties.getParty(uuid);
					if (o != null) {
						o.removePlayer(uuid);
						p.sendMessage(u.cc("&cYou have left your party"));
						return true;
					} else send(p, "&cYou are not in a party");
				}
			} else if (cmd.getName().equalsIgnoreCase("statistics")) {
				if (args.length == 0) {
					u.openLaterandAdd(p, new GlobalStatistics());
					return true;
				}
			} else if (cmd.getName().equalsIgnoreCase("customcrafting")) {
				u.openLaterandAdd(p, new Crafting());
				return true;
			} else if (cmd.getName().equalsIgnoreCase("backpack")) {
				u.openLaterandAdd(p, new BackPack());
				return true;
			}
		}
		
		return false;
	}

	private void send(Player p, String msg) {
		p.sendMessage(u.cc(msg));
	}
	
	@Override
	public List<String> onTabComplete( CommandSender s,  Command cmd, String label,  String[] args) {
		if (s instanceof Player) {
			Player p = (Player) s;
			if (cmd.getName().equalsIgnoreCase("party")) {
				if (args.length == 1) return u.TabCompleter(args[0], "invite", "kick", "leave");
			}
		}		
		return null;
	}
}