package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.Main;
import me.superpenguin.supercraftbros.Main.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;

public class Pillager extends Kit {
	
	public Pillager() {
		super(	kitType.PILLAGER,
				"Pillager",
				new Armour(u.getHead("4aee6bb37cbfc92b0d86db5ada4790c64ff4468d68b84942fde04405e8ef5333"))
				);
	}

	@Override
	public List<ItemStack> getItems() {
		ItemStack a = new MIB(Material.TIPPED_ARROW, 64).build();
		PotionMeta m = (PotionMeta) a.getItemMeta();
		m.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 40, 1), false);
		m.setColor(PotionEffectType.SLOW.getColor());
		a.setItemMeta(m);
		return Arrays.asList(
				new MIB(Material.CROSSBOW).setLocname("weapon1").setName("&BOl' Betsy").addEnchant(Enchantment.QUICK_CHARGE, 4).addEnchant(Enchantment.PIERCING, 1).build(),
				new MIB(Material.REDSTONE).setLocname("bonus").setName("&bWeakening Agent").build(),
				a
			);		
	}

	@Override
	public void RightClickWithBonusItem(PlayerInteractEvent e) {
		u.applyPotionEffectToAll(Main.getGame(e.getPlayer()).getAlivePlayersWithout(e.getPlayer()), PotionEffectType.WEAKNESS, 1, 5);
		e.getPlayer().getInventory().getItemInMainHand().setAmount(0);
		
	}

	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.HEALTH_BOOST, 1);
	}

	@Override
	public void shootBow(EntityShootBowEvent e) {
		u.addTag(e.getProjectile(),	"Pillager");
	}
	
	@Override
	public void HandleProjectileHit(ProjectileHitEvent e) {
		Player p = (Player) e.getEntity().getShooter();
		//something needs to happen here.
	}
	
}
