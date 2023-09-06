package me.superpenguin.supercraftbros.utils;

import me.superpenguin.supercraftbros.Main;
import me.superpenguin.supercraftbros.Main.kitType;
import me.superpenguin.supercraftbros.objects.Kit;
import me.superpenguin.supercraftbros.objects.Map;

public class translator {	
	
	public static Kit getKit(String string) {
		kitType type;
		try { type = kitType.valueOf(string); } catch (NullPointerException | EnumConstantNotPresentException ex) { return null; }
		return getKit(type);
	}
	
	public static Kit getKit(kitType type) {
		return Main.ClassSelector.get(type);
	}
	
	public static Map.MapType getMap(String name) {
		try { return Map.MapType.valueOf(name); } catch (NullPointerException ex) { return null; }
	}
	
}
