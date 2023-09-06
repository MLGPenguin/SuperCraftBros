package me.Penguin.SuperCraftBros.objects;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Penguin.SuperCraftBros.Main;
import me.Penguin.SuperCraftBros.Main.kitType;
import me.Penguin.SuperCraftBros.Main.pack;
import me.Penguin.SuperCraftBros.customevents.DropBelowHalfHealthEvent;
import me.Penguin.SuperCraftBros.utils.u;

public abstract class Kit {
	
	private kitType type;
	private String name;
	private Armour armour;
	
	
	public Kit(kitType type, String name, Armour Armour) {
		this.type = type;
		this.name = name;
		this.armour = Armour;
		Main.ClassSelector.put(type, this);
	}
	
	public kitType getType() { return type; }
	public ItemStack[] getArmour() { return armour.get(); }
	public ItemStack getHead() { return armour.getHead(); }
	public int getLives() { return 5; }
	public String getName() { return name; }
	
	private boolean within(pack pack) {
		return pack.getKits().contains(type);				
	}
	public ItemStack getIcon() { 
		ItemStack i = getHead();
		ItemMeta m = i.getItemMeta();
		String colour =   within(pack.DEFAULT) ? "&7" 
						: within(pack.GEM) ? "&a" 
						: within(pack.RANK) ? "&5" 
						: within(pack.ACHIEVEMENT) ? "&6" 
						: "&b";
		m.setDisplayName(u.cc(colour + name));
		m.setLocalizedName(type.toString());
		i.setItemMeta(m);
		return i;
	}
	
	public abstract List<ItemStack> getItems();
	public void RightClickWithBonusItem(PlayerInteractEvent e) {}
	public void RightClickWithBonus2Item(PlayerInteractEvent e) {}
	public void RightClickWithWeapon1(PlayerInteractEvent e) {}
	public void RightClickPlayerWithBonusItem(PlayerInteractAtEntityEvent e) {}
	public void activatePerks(Player p) {}
	public void dropBelowHalfHealth(DropBelowHalfHealthEvent e) {}
	public void DamageDoneWithBonusItem(EntityDamageByEntityEvent e) {}
	public void DamageDoneWithWeapon1(EntityDamageByEntityEvent e) {}
	public void HandleProjectileThrow(ProjectileLaunchEvent e) {}
	public void HandleProjectileHit(ProjectileHitEvent e) {}
	public void DamagedByPlayer(EntityDamageByEntityEvent e) {}
	public void OnKitKilled(EntityDeathEvent e) {}
	public void DamagedPlayer(EntityDamageByEntityEvent e) {}
	public void takenDamage(EntityDamageEvent e) {}
	public void removePerks(Player p) {}
	public void shootBow(EntityShootBowEvent e) {}
	

	

}
