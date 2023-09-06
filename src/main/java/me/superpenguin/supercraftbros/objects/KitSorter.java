package me.superpenguin.supercraftbros.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import me.superpenguin.supercraftbros.SuperCraftBros.kitType;
import me.superpenguin.supercraftbros.SuperCraftBros.pack;

public class KitSorter {
	
	private List<kitType> unlockedKits;
	private SortingOrder order;
	
	public KitSorter(List<kitType> unlockedKits, SortingOrder order) {
		this.unlockedKits = unlockedKits;
		this.order = order;
	}
	
	public List<kitType> getSorted() {
		List<kitType> sorted = new ArrayList<>();
		switch (order) {
		case ALPHABETICAL:
			sorted.addAll(unlockedKits);
			sorted = sorted.stream().map(t -> t.toString()).sorted().map(t -> kitType.valueOf(t)).collect(Collectors.toList());
			return sorted;
		case REVERSEALPHABETICAL:
			List<kitType> alpha = new KitSorter(unlockedKits, SortingOrder.ALPHABETICAL).getSorted();
			List<kitType> reversealpha = new ArrayList<>();
			for (int i = alpha.size()-1 ; i >= 0 ; i-- ) reversealpha.add(alpha.get(i));
			return reversealpha;
		case RARITY:
			List<kitType> unfiltered = new ArrayList<>(unlockedKits);
			List<kitType> rarity = new ArrayList<>();
			rarity.addAll(getInPack(pack.RANK, unfiltered, true));
			rarity.addAll(getInPack(pack.ACHIEVEMENT, unfiltered, true));
			rarity.addAll(getInPack(pack.GEM, unfiltered, true));
			rarity.addAll(getInPack(pack.DEFAULT, unfiltered, true));
			rarity.addAll(unfiltered);
			return rarity;
		default: return unlockedKits;
		}
	}
	
	
	
	public enum SortingOrder {
		ALPHABETICAL, REVERSEALPHABETICAL, RARITY;
	}
	
	
	private List<kitType> getInPack(pack pack, List<kitType> types, boolean remove) {
		List<kitType> kits = pack.getKits();
		List<kitType> contained = new ArrayList<>();
		for (kitType t : types) if (kits.contains(t)) contained.add(t);
		if (remove) types.removeAll(contained);
		return contained;		
	}
	

}
