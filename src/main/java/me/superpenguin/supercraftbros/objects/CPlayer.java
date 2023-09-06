package me.superpenguin.supercraftbros.objects;

import me.superpenguin.supercraftbros.Main;
import me.superpenguin.supercraftbros.utils.levelutil;
import org.bukkit.entity.Player;

/**
 * Class CPlayer is used for custom methods that affect the in-game player.
 * No data is stored within this class.
 * @author baww1
 *
 */
public class CPlayer {
	
	private Player player;
	private IPlayer ip;
	
	public CPlayer(Player player) {
		this.player = player;
		ip = Main.getPersistentPlayer(player.getUniqueId());
	}

	/**
	 * Updates the XP Bar to the player's current XP and Level
	 */
	public void updateLevelXP() {
		int level = ip.getLevel();
		player.setLevel(level);
		player.setExp(ip.getXP()/ levelutil.getXP.get(level));
	}
	
	
}
