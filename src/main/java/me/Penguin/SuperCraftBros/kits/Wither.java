package me.Penguin.SuperCraftBros.kits;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

import me.Penguin.SuperCraftBros.Main.kitType;
import me.Penguin.SuperCraftBros.objects.Armour;
import me.Penguin.SuperCraftBros.objects.Kit;
import me.Penguin.SuperCraftBros.tags.WitherResistant;
import me.Penguin.SuperCraftBros.utils.MIB;
import me.Penguin.SuperCraftBros.utils.u;

public class Wither extends Kit implements WitherResistant {
	
	public Wither() {
		super(	kitType.WITHER,
				"Wither",
				new Armour(u.getHead("a435164c05cea299a3f016bbbed05706ebb720dac912ce4351c2296626aecd9a"))
				);
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(
				new MIB(Material.COAL).setLocname("weapon1").setName("&BTooth").addEnchant(Enchantment.KNOCKBACK, 1).addEnchant(Enchantment.DAMAGE_ALL, 2).build(),
				new MIB(Material.BOW).setLocname("bonus").setName("&BHEAD LAUNCHER").build(),
				new MIB(Material.ARROW, 5).build()
				);		
	}
	
	@Override
	public void shootBow(EntityShootBowEvent e) {
		Arrow a  = (Arrow) e.getProjectile();
		a.remove();
		Player shooter = (Player) e.getEntity();
		World w = shooter.getWorld();
		w.playSound(shooter.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1, 1);
		WitherSkull s = shooter.launchProjectile(WitherSkull.class);
		s.setVelocity(s.getVelocity().multiply(2));
		shooter.getInventory().removeItem(new ItemStack(Material.ARROW));
	}

	
	
}
