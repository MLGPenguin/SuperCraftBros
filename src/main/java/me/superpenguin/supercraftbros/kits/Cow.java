package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.utils.MIB;
import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.Main.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;

public class Cow extends Kit {

	public Cow() {
		super(	kitType.COW,
				"Cow",
				new Armour(u.getHead("be8456155142cbe4e61353ffbaff304d3d9c4bc9247fc27b92e33e6e26067edd"))
				);
	}
	
	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(new MIB(Material.COOKED_BEEF).setName("&bTender").setLocname("weapon1").addEnchant(Enchantment.DAMAGE_ALL, 2).build());
	}

	@Override
	public void DamagedByPlayer(EntityDamageByEntityEvent e) {
		u.applyPotionEffect((Player)e.getEntity(), PotionEffectType.SPEED, 4, 3);
	}
	

	

}
