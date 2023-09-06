package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import me.superpenguin.supercraftbros.SuperCraftBros;
import me.superpenguin.supercraftbros.objects.Kit;
import me.superpenguin.supercraftbros.objects.SPlayer;
import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.superpenguin.supercraftbros.objects.Armour;

public class Engineer extends Kit {
	
	public Engineer() {
		super(	SuperCraftBros.kitType.ENGINEER,
				"Engineer",
				new Armour(u.getHead("1062ad49dbcdb730291303e8d7011486adbae5a0d165a2a9fdc220b7735f223b"))
				);
	}

	@Override
	public List<ItemStack> getItems() {
	return Arrays.asList(
				new MIB(Material.CLOCK).addEnchant(Enchantment.DAMAGE_ALL, 2).setLocname("weapon1").setName("&bTelepad").build()
				);		
	}
	
	@Override
	public void RightClickWithWeapon1(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Action a = e.getAction();
		UUID uuid = p.getUniqueId();
		if (a == Action.RIGHT_CLICK_BLOCK) {
			if (p.isSneaking()) {
				SuperCraftBros.waypointTable.put(uuid, e.getClickedBlock().getLocation().add(0.5, 1, 0.5));
				p.sendMessage(u.cc("&aSet Waypoint!"));
				return;
			} 
		}
		if (SuperCraftBros.waypointTable.containsKey(uuid)) {
			SPlayer s = SuperCraftBros.getPlayer(uuid);
			if (s.isBonusReady()) {
				Location loc = SuperCraftBros.waypointTable.get(uuid);
				loc.setPitch(p.getLocation().getPitch());
				loc.setYaw(p.getLocation().getYaw());
				p.teleport(loc);
				s.setBonusCooldown(15);
				p.sendMessage(u.cc("&aTeleported to waypoint!"));
				return;
			} else p.sendMessage(s.getCooldownMessage());
		} else p.sendMessage(u.cc("&cNo Waypoint set!"));
	}
	
	@Override
	public void DamagedByPlayer(EntityDamageByEntityEvent e) {
		ItemStack weapon = ((Player)e.getDamager()).getInventory().getItemInMainHand();
		Material type = weapon.getType();
		String typestring = type.toString();
		if (typestring.contains("_SWORD") || typestring.contains("_axe") || typestring.contains("_pickaxe")) {
			e.setDamage(e.getFinalDamage()/2);
		}
	}
	
	


}
