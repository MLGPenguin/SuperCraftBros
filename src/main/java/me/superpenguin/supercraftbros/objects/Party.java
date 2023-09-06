package me.superpenguin.supercraftbros.objects;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Party {
	
	private UUID leader;
	private String leadername;
	private List<UUID> members;
	
	public Party(Player leader) {
		this.leader = leader.getUniqueId();
		this.leadername = leader.getName();
		members = new ArrayList<>();
	}
	
	/**
	 * Including leader.
	 * @return
	 */
	public Set<UUID> getAllMembers() {
		Set<UUID> all = new HashSet<>();
		all.addAll(members);
		all.add(leader);
		return all;
	}
	
	public Set<Player> getAllPlayers() {
		return getAllMembers().stream().map(t -> Bukkit.getPlayer(t)).collect(Collectors.toSet());
	}

	public void addMember(Player member) { members.add(member.getUniqueId()); }
	public boolean isLeader(UUID uuid) { return uuid.equals(leader); }
	public Player getLeader() { return Bukkit.getPlayer(leader); }
	
	public void removePlayer(UUID uuid) {
		if (getAllMembers().size() == 1) disband();
		if (uuid.equals(leader)) {
			leader = members.get(0);
			members.remove(0);
		} else {
			members.remove(uuid);
		}
	}
	
	public void broadcast(String msg) { for (Player p : getAllPlayers()) p.sendMessage(u.cc(msg)); }
	public void disband() { Parties.disbandParty(this); }
	public boolean hasPlayer(UUID uuid) { return getAllMembers().contains(uuid); }
	public int getSize() { return getAllMembers().size(); }
	
	public void invite(Player p) {
		p.sendMessage(u.cc("&aYou have been invited to join " + leadername + "'s party, check your invites to join with /party"));
		Parties.addInvite(p, this);
	}

}
