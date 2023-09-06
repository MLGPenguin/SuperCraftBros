package me.Penguin.SuperCraftBros.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.Penguin.SuperCraftBros.utils.u;

public class Parties {
	
	public static void initialise() {
		current = new ArrayList<>();
		invites = new HashMap<>();
	}
	
	public static List<Party> current;
	public static HashMap<UUID, Set<Party>> invites;

	public static int getAmountofInvites(UUID uuid) {
		if (invites.containsKey(uuid)) return invites.get(uuid).size();
		else return 0;
	}
	
	public static void disbandParty(Party p) {
		current.remove(p);
	}
	
	public static boolean isInParty(UUID uuid) {
		for (Party p : current) {
			if (p.getAllMembers().contains(uuid)) return true;
		} 
		return false;	
	}
	
	public static Party getParty(UUID uuid) {
		for (Party p : current) {
			if (p.getAllMembers().contains(uuid)) return p;
		}
		return null;
	}
	
	public static Set<Party> getInvites(UUID uuid) {
		if (invites.containsKey(uuid)) return invites.get(uuid);
		else return new HashSet<>();
	}
	
	public static Party getOrCreateParty(Player leader) {
		UUID uuid = leader.getUniqueId();
		Party in = getParty(uuid);
		if (in == null) in = newParty(leader);
		return in;
	}
	
	public static Party newParty(Player leader) {
		Party p = new Party(leader);
		current.add(p);
		return p;
	}
	
	public static void addInvite(Player player, Party party) {
		Set<Party> existing = getInvites(player.getUniqueId());
		existing.add(party);
		invites.put(player.getUniqueId(), existing);
	}
	
	public static void invite(Player owner, Player invited) {
		if (owner.getName().equals(invited.getName())) {
			owner.sendMessage(u.cc("&cYou can't invite yourself, dickhead"));
			return;
		}
		Party o = Parties.getOrCreateParty(owner);
		if (o.getLeader().getUniqueId().equals(owner.getUniqueId())) {
			if (!o.getAllMembers().contains(invited.getUniqueId())) {
				if (o.getSize() < 4) {
					o.invite(invited);
					owner.sendMessage(u.cc("&aYou have invited " + invited.getName() + " to your party"));
				} else owner.sendMessage(u.cc("&cThis party is full!"));
			} else owner.sendMessage(u.cc("&cThis player is already in your party"));			
		} else owner.sendMessage(u.cc("&cSorry, You are not the owner of this party."));		
	}
	
	
	public static void removeInvite(Player player, Party party) {
		Set<Party> existing = getInvites(player.getUniqueId());
		if (existing.contains(party)) existing.remove(party);
		if (existing.size() == 0) invites.remove(player.getUniqueId());
		else invites.put(player.getUniqueId(), existing);
	}
	
	public static List<Player> getRequiredJoiners(Player p) {
		List<Player> players = new ArrayList<>();
		if (isInParty(p.getUniqueId())) 
			players.addAll(getParty(p.getUniqueId()).getAllMembers().stream().map(t -> Bukkit.getPlayer(t)).collect(Collectors.toList()));
		else players.add(p);
		return players;
	}
	
}
