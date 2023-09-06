package me.Penguin.SuperCraftBros.objects;

import java.util.HashMap;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Armour {
	
	private HashMap<piece, String> colours;
	private boolean Elytra;
	private ItemStack head;
	
	public Armour(ItemStack head, String ChestplateHex, String LeggingsHex, String BootsHex) {
		this.head = head;
		this.colours = new HashMap<>();
		colours.put(piece.BOOT, BootsHex);
		colours.put(piece.LEG, LeggingsHex);
		colours.put(piece.CHEST, ChestplateHex);
	}
	
	public Armour(ItemStack head, String LeggingsHex, String BootsHex) {
		this.head = head;
		this.colours = new HashMap<>();
		colours.put(piece.LEG, LeggingsHex);
		colours.put(piece.BOOT, BootsHex);
		this.Elytra = true;
	}
	
	public Armour(ItemStack head, boolean Elytra) {
		this.head = head;
		this.Elytra = Elytra;
		this.colours = new HashMap<>();
		colours.put(piece.LEG, "#F9FFFE");
		colours.put(piece.BOOT, "#F9FFFE");
		colours.put(piece.CHEST, "#F9FFFE");
	}
	
	public Armour(ItemStack head) {
		this(head, false);
	}
	
	public ItemStack getPiece(piece piece) {
		ItemStack i = new ItemStack(piece.getMaterial());
		LeatherArmorMeta m = (LeatherArmorMeta) i.getItemMeta();
		m.setColor(getColor(colours.get(piece)));
		m.setUnbreakable(true);
		m.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_DYE);
		i.setItemMeta(m);
		return i;
	}
	
	private Color getColor(String colorStr) {
	    return  Color.fromRGB(
	            Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
	            Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
	            Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
	}
	
	private ItemStack getElytra() {
		ItemStack e = new ItemStack(Material.ELYTRA);
		ItemMeta m = e.getItemMeta();
		m.setUnbreakable(true);
		m.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		e.setItemMeta(m);
		return e;
	}
	
	public ItemStack getHead() { return head; }
	
	public ItemStack[] get() {
		return new ItemStack[] {
				getPiece(piece.BOOT),
				getPiece(piece.LEG),
				Elytra ? getElytra() : getPiece(piece.CHEST),
				head
		};
	}

	
	public enum piece {
		CHEST(Material.LEATHER_CHESTPLATE), LEG(Material.LEATHER_LEGGINGS), BOOT(Material.LEATHER_BOOTS);
		
		private Material m;
		
		piece(Material mat){
			this.m = mat;
		}
		
		public Material getMaterial() { return m; }
	}
	
}
