package me.superpenguin.supercraftbros.kits;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.superpenguin.supercraftbros.Main.kitType;
import me.superpenguin.supercraftbros.objects.Armour;
import me.superpenguin.supercraftbros.objects.Kit;
import me.superpenguin.supercraftbros.utils.MIB;

public class SkeletonHorse extends Kit {
	
	public SkeletonHorse() {
		super(	kitType.SKELETONHORSE,
				"Skeleton Horse",
				new Armour(u.getHead("47effce35132c86ff72bcae77dfbb1d22587e94df3cbc2570ed17cf8973a"))
				);
	}

	@Override
	public List<ItemStack> getItems() {
	return Arrays.asList(
				new MIB(Material.BONE).addEnchant(Enchantment.KNOCKBACK, 1).addEnchant(Enchantment.DAMAGE_ALL, 1).setLocname("weapon1").setName("&bBonk").build(),
				new MIB(Material.BOW).setLocname("bonus").setName("&bBazooka").addEnchant(Enchantment.ARROW_INFINITE, 1).build(),
				new MIB(Material.ARROW).build()
				);		
	}
	
	@Override
	public void activatePerks(Player p) {
		u.applyInfinitePotionEffect(p, PotionEffectType.SPEED, 3);
	}
	
}
