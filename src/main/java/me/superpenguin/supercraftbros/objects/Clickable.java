package me.superpenguin.supercraftbros.objects;

import java.util.List;

import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.SuperCraftBros;

public class Clickable {
	
	private ItemStack item;
	private Player p;
	private boolean hasLocname, isBonus, isBonus2, isWeapon1 = false;
	private String locname = "";
	
	public Clickable(PlayerInteractEvent e) {
		this(e.getPlayer(), e.getItem());
	}
	
	public Clickable(Player p , ItemStack item) {
		this.item = item;
		this.p = p;
		this.hasLocname = (item != null && item.getItemMeta().hasLocalizedName());
		if (hasLocname) {
			locname = item.getItemMeta().getLocalizedName();
			if (locname.equals("bonus")) isBonus = true;
			else if (locname.equals("bonus2")) isBonus2 = true;
			else if (locname.equals("weapon1")) isWeapon1 = true;
		}
	}
	
	public List<Player> getOthers() { return SuperCraftBros.getGame(p).getAlivePlayersWithout(p); }
	public boolean hasLocname() { return hasLocname; }
	public boolean isBonus() { return isBonus; }
	public boolean isBonus2() { return isBonus2; }
	public boolean isWeapon1() { return isWeapon1; }

	public void takeOne() { item.setAmount(item.getAmount()-1); }
	
	
	public void distributePotionToOthers(PotionEffectType type, int strength, int seconds) {
		u.applyPotionEffectToAll(getOthers(), type, strength, seconds);
		takeOne();
	}
	
	public void applyPotionEffectWithChance(Player target, double chance, PotionEffectType type, int strength, int seconds) {
		if (u.testProbability(chance)) u.applyPotionEffect(target, type, strength, seconds);
	}
	
	public void setOthersOnFire(int ticks) {
		getOthers().forEach(i-> i.setFireTicks(ticks));
		takeOne();
	}
	
	public void setOthersOnFire(int ticks, boolean takeOne) {
		setOthersOnFire(ticks);
		if (takeOne) takeOne();
	}
	

}
