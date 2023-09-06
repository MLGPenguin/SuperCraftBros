package me.Penguin.SuperCraftBros.customevents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DropBelowHalfHealthEvent extends Event {
	
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    @Override public HandlerList getHandlers() { return HANDLERS_LIST; }
    public static HandlerList getHandlerList() { return HANDLERS_LIST; }
    
    private Player player;
    private double oldHealth, newHealth;

    public DropBelowHalfHealthEvent(Player player, double previousHealth, double newHealth) {
        this.player = player;
        this.oldHealth = previousHealth;
        this.newHealth = newHealth;
    }


    public Player getPlayer() { return player; }
    public double getPreviousHealth() { return oldHealth; }
    public double getNewHealth() { return newHealth; }

}

	
	
