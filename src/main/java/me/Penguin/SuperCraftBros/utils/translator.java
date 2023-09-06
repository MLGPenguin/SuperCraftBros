package me.Penguin.SuperCraftBros.utils;

import me.Penguin.SuperCraftBros.Main;
import me.Penguin.SuperCraftBros.Main.kitType;
import me.Penguin.SuperCraftBros.objects.Kit;
import me.Penguin.SuperCraftBros.objects.Map.MapType;

public class translator {	
	
	public static Kit getKit(String string) {
		kitType type;
		try { type = kitType.valueOf(string); } catch (NullPointerException | EnumConstantNotPresentException ex) { return null; }
		return getKit(type);
	}
	
	public static Kit getKit(kitType type) {
		return Main.ClassSelector.get(type);
	}
	
	public static MapType getMap(String name) {
		try { return MapType.valueOf(name); } catch (NullPointerException ex) { return null; }
	}
	
}
