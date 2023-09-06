package me.Penguin.SuperCraftBros.customevents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.Penguin.SuperCraftBros.objects.Game;

public class CreateGameEvent extends Event {

	private static final HandlerList HANDLERS_LIST = new HandlerList();
	@Override public HandlerList getHandlers() { return HANDLERS_LIST; }
	public static HandlerList getHandlerList() { return HANDLERS_LIST; }

	private Player player;
	private Game game;

	public CreateGameEvent(Player creator, Game game) {
		this.player = creator;
		this.game = game;
	}


	public Player getPlayer() { return player; }
	public Game getGame() { return game; }

}
