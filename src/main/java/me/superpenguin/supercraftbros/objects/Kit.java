package me.superpenguin.supercraftbros.objects;

import java.util.List;

import me.superpenguin.supercraftbros.SuperCraftBros;
import me.superpenguin.supercraftbros.customevents.DropBelowHalfHealthEvent;
import me.superpenguin.supercraftbros.utils.u;
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

public abstract class Kit {
	
	private SuperCraftBros.kitType type;
	private String name;
	private Armour armour;
	
	
	public Kit(SuperCraftBros.kitType type, String name, Armour Armour) {
		this.type = type;
		this.name = name;
		this.armour = Armour;
		SuperCraftBros.ClassSelector.put(type, this);
	}
	
	public SuperCraftBros.kitType getType() { return type; }
	public ItemStack[] getArmour() { return armour.get(); }
	public ItemStack getHead() { return armour.getHead(); }
	public int getLives() { return 5; }
	public String getName() { return name; }
	
	private boolean within(SuperCraftBros.pack pack) {
		return pack.getKits().contains(type);				
	}
	public ItemStack getIcon() { 
		ItemStack i = getHead();
		ItemMeta m = i.getItemMeta();
		String colour =   within(SuperCraftBros.pack.DEFAULT) ? "&7"
						: within(SuperCraftBros.pack.GEM) ? "&a"
						: within(SuperCraftBros.pack.RANK) ? "&5"
						: within(SuperCraftBros.pack.ACHIEVEMENT) ? "&6"
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
