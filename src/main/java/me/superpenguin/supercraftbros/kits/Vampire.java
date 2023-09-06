package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import me.superpenguin.supercraftbros.objects.Game;
import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.superpenguin.supercraftbros.SuperCraftBros;
import me.superpenguin.supercraftbros.SuperCraftBros.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;
import me.superpenguin.supercraftbros.objects.SPlayer;

public class Vampire extends Kit {
	
	public Vampire() {
		super(	kitType.VAMPIRE,
				"Vampire",
				new Armour(u.getHead("8d44756e0b4ece8d746296a3d5e297e1415f4ba17647ffe228385383d161a9"))
				);
	}

	@Override
	public List<ItemStack> getItems() {
	return Arrays.asList(
				new MIB(Material.GHAST_TEAR).addEnchant(Enchantment.DAMAGE_ALL, 3).setLocname("weapon1").setName("&bFang").build()
				);		
	}
	
	@Override
	public void RightClickWithWeapon1(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Action a = e.getAction();
		UUID uuid = p.getUniqueId();
		Game g = SuperCraftBros.getGame(p);
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
				Location existing = p.getLocation();
				for (int i = 0 ; i < 6 ; i++) {
					Bat b = (Bat) p.getWorld().spawnEntity(existing, EntityType.BAT);
					g.addSpawnedEntity(b);
				}
				p.teleport(loc);
				s.setBonusCooldown(15);
				p.sendMessage(u.cc("&aTeleported to waypoint!"));
				return;
			} else p.sendMessage(s.getCooldownMessage());
		} else p.sendMessage(u.cc("&cNo Waypoint set!"));
	}
	
	@Override
	public void DamagedPlayer(EntityDamageByEntityEvent e) {
		Player vamp = (Player) e.getDamager();
		if (vamp.getHealth() < vamp.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) {
			vamp.setHealth(vamp.getHealth() + 1);
		}
	}
	

}
