package me.superpenguin.supercraftbros.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

import me.superpenguin.supercraftbros.Main;
import me.superpenguin.supercraftbros.kits.Steve;
import me.superpenguin.supercraftbros.objects.Map.MapType;
import me.superpenguin.supercraftbros.utils.MIB;

public class Game {
	
	private List<Player> players, alivePlayers, ranking;
	private MapType map;	
	private gamestate state;
	private List<Entity> spawnedEntities;
	private int ID;
	private Timer timer;
	private Scoreboard scoreboard;
	
	public Game(List<Player> players, MapType map) {
		this.players = new ArrayList<>(players);
		this.alivePlayers = new ArrayList<>();
		this.map = map;
		this.spawnedEntities = new ArrayList<>();
		this.ranking = new ArrayList<>();
		this.timer = new Timer();
	}
	
	public MapType getMap() { return map; }
	public List<Player> getPlayers() { return players; }
	public List<Player> getAlivePlayers() { return alivePlayers; }
	public List<Player> getRanking() { return ranking; }
	public List<Player> getAlivePlayersWithout(Player p) {
		List<Player> players = new ArrayList<>(alivePlayers);
		if (players.contains(p)) players.remove(p);
		return players;
	}
	public int getAmountAlive() { return alivePlayers.size(); }
	public gamestate getState() { return state; }
	
	public void prepareForLobby(Player p) {
		UUID uuid = p.getUniqueId();
		alivePlayers.add(p);
		u.stripPlayer(p);
		p.teleport(map.getLobbySpawn());
		p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
		p.setHealth(20);;
		if (Main.playerData.containsKey(uuid)) Main.playerData.remove(uuid);
		p.getInventory().setItem(4, getKitSelectionItem());
		broadcastMessage("&6" + p.getName() + " has joined the game (" + getAmountAlive() + "/4)");
	}
	
	public void startLobby(Player started) {
		ID = Main.getNewGameID();
		Main.runningGames.put(ID, this);
		this.state = gamestate.LOBBY;
		for (Player p : players) prepareForLobby(p);		
		startLobbyCountdown();
		Bukkit.broadcastMessage(u.cc("&b" + started.getName() + " has started a game on " + map.getName() + "! &btype /games to join"));
	}
	
	public void startGame() {
		if (players.size() > 1) {
			for (Player p : players) {
				p.teleport(getSafeSpawn());
				SPlayer s = Main.getPlayer(p.getUniqueId());
				if (!s.hasKit()) s.setKit(new Steve());
				s.applyKit();
				s.setLives(s.getKit().getLives());
			}
			generateScoreboard();
			applyScoreboards();
			this.state = gamestate.ONGOING;
			timer.start();
		} else if (players.size() == 0) endGame();
		else {
			broadcastMessage("&cNot enough players to start, trying again!");
			startLobbyCountdown();		
		}		
	}
	
	private void startScoreboardTimer() {
		// TODO
	}
	
	private void applyScoreboards() {
		for (Player p : players) p.setScoreboard(scoreboard);
	}
	private void generateScoreboard() {
		scoreboard b = new scoreboard("lives", "&6Lives");
		for (Player p : players) {
			SPlayer s = Main.getPlayer(p.getUniqueId());
			b.addDynamicLine(p.getName(), "&4" + p.getName() + "&f: ", "&b" + s.getLives());
		}
		b.addStaticLine(" ");
		b.addDynamicLine("timer", "  ", u.cc("&c10:00 &e(W.I.P)"));
		this.scoreboard = b.getScoreboard();
	}
	
	private void startLobbyCountdown() {
		startMessageTimer(0, 60);
		startMessageTimer(30, 30);
		startMessageTimer(45, 15);
		startMessageTimer(50, 10);
		startMessageTimer(55, 5);
		startMessageTimer(56, 4);
		startMessageTimer(57, 3);
		startMessageTimer(58, 2);
		startMessageTimer(59, 1);		
		new BukkitRunnable() {			
			@Override
			public void run() {
				startGame();
			}
		}.runTaskLater(Main.getPlugin(Main.class), 60 * 20);
	}
	
	private void startMessageTimer(int secondsAway, int secondsRemaining) {
		new BukkitRunnable() {			
			@Override
			public void run() {
				broadcastMessage("&6" + secondsRemaining + " seconds until the game starts");
			}
		}.runTaskLater(Main.getPlugin(Main.class), secondsAway * 20);
	}
	
	public void broadcastMessage(String msg) { players.forEach(p -> p.sendMessage(u.cc(msg))); }	
	public void broadcastTitle(String msg) { players.forEach(p -> p.sendTitle(u.cc(msg), null, 10, 70, 20)); }	
	public void broadcastSoundEffect(Sound sound) { players.forEach(p -> p.playSound(p.getLocation(), sound, 1, 1)); }	
	public void broadcastGameLeaderboard() { players.forEach(p -> getGameLeaderboard().forEach(t -> p.sendMessage(t))); }	
	public void broadcastExperiment(Player p) { broadcastMessage("&c" + p.getName() + " has just used an experimental feature! please leave me some feedback."); }
	
	public void addPlayerToGame(Player p) {
		this.players.add(p);
		prepareForLobby(p);
	}
	
	public void addPlayersToGame(List<Player> players) { for (Player p : players) addPlayerToGame(p); }	
	public void addSpawnedEntity(Entity e) { spawnedEntities.add(e); }
	
	private List<String> getGameLeaderboard() {
		List<String> list = new ArrayList<>();
		for (int i = 0 ; i < ranking.size() ; i++) {
			Player p = ranking.get(i);
			SPlayer s = Main.getPlayer(p.getUniqueId());
			list.add(u.cc("&b" + (i+1) + "- &6" + p.getName() + ": &b" + s.getKills() + " kills"));
		}
		return list;
	}
	
	public Location getSafeSpawn() {
		Location spawn = null;
		int i = 0;
		while (spawn == null && i < 8) {
			Location s = randomSpawnPoint();
			if (isSpawnSafe(s)) spawn = s;
			i++;
		}
		return (spawn == null ? randomSpawnPoint() : spawn);
	}
	
	private Location randomSpawnPoint() {
		Random r = new Random();
		List<Location> spawns = map.getSpawnLocations();
		int rand = r.nextInt(spawns.size());
		return spawns.get(rand);
	}
	
	public void eliminatePlayer(Player p) {
		SPlayer s = Main.getPlayer(p.getUniqueId());		
		ranking.add(0, p);
		if (state != gamestate.LOBBY) s.addAlivems(timer.getCurrentms());
		alivePlayers.remove(p);
		Player lastHit = s.getLastDamagedBy();
		if (lastHit != null) broadcastMessage("&6&l" + p.getName() + " &c&lhas been eliminated by " + s.getLastDamagedBy().getName() + "!");
		else broadcastMessage("&6&l" + p.getName() + " &c&lhas been eliminated");
		if (getAmountAlive() == 1) {
			s.multiplyGems(1.5);
			s.addGems(3);
			p.sendMessage(u.cc("&aYou have earnt " + s.getGems() + " gems this round"));
			win(alivePlayers.get(0));
		}
		s.saveToIPlayer();
		endPlayer(p);
		if (getAmountAlive() == 1 || getAmountAlive() == 0) endGame();		
	}
	
	public void removePlayer(Player p) {
		players.remove(p);
	}
	
	private void win(Player p) {
		SPlayer s = Main.getPlayer(p.getUniqueId());
		s.addWin();
		s.multiplyGems(2);
		s.addGems(5);
		p.sendMessage(u.cc("&aYou have earnt " + s.getGems() + " gems this round"));
		s.addAlivems(timer.getCurrentms());
		s.saveToIPlayer();
		ranking.add(0, p);
		endPlayer(p);		
		alivePlayers.remove(p);
		broadcastMessage("&b&l" + p.getName() + " &a&lhas won the game!");
		broadcastTitle("&b&l" + p.getName() + " &a&lwon!");
		broadcastSoundEffect(Sound.ENTITY_PLAYER_LEVELUP);
		broadcastGameLeaderboard();
		Bukkit.broadcastMessage(u.cc("&b&l" + p.getName() + "&a&l has won on " + map.getName()  + "&b!"));
	}
	
	private void endPlayer(Player p) {
		SPlayer s = Main.getPlayerWithoutCreating(p.getUniqueId());
		if (s != null) {
			s.setBlocksToAir();
			if (s.hasKit()) s.removeKit();
		}		
		p.setGameMode(GameMode.SPECTATOR);
		p.teleport(map.getCentre());		
	}
	
	private boolean isSpawnSafe(Location l) {
		return (l.getWorld().getNearbyEntities(l, 8, 8, 8).stream().filter(p -> p instanceof Player).collect(Collectors.toList()).size() == 0);
	}
		
	private ItemStack getKitSelectionItem() {
		return new MIB(Material.COMPASS).setName("&bKIT SELECTOR").setLocname("selectkit").build();
	}
	
	public boolean containsPlayer(Player p) {
		return players.contains(p);
	}
	
	
	public void endGameWithoutRemoving() {
		timer.end();
		for (Player p : alivePlayers) endPlayer(p);
		alivePlayers.clear();
		this.state = gamestate.FINISHED;
		spawnedEntities.forEach(e -> e.remove());
		spawnedEntities.clear();
		players.forEach(p -> {
			Main.playerData.remove(p.getUniqueId());
			p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		});
	}
	
	public void endGame() {
		endGameWithoutRemoving();
		Main.runningGames.remove(ID);
		
	}
	
	
	public enum gamestate {
		LOBBY, ONGOING, FINISHED;
	}


}
