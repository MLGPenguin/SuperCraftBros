package me.superpenguin.supercraftbros.objects;

import java.util.Arrays;
import java.util.List;

import me.superpenguin.supercraftbros.utils.u;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

import me.superpenguin.supercraftbros.utils.MIB;

public class Map {
	
	static World w = Bukkit.getWorld("world");
	
	public enum MapType {
	
		SNOW(	"&bPenguin Paradise",
				new MIB(Material.SNOW_BLOCK).setLocname("SNOW"),
				new Location(w, 144.5, 52, -85.5, 90, 0),
				new Location(w, 152.5, 85, -76.5, 180, 0), 
				Arrays.asList(
				new Location(w, 122.5, 52, -109.5, 0, 0),
				new Location(w, 111.5, 46, -73.5, -135, 0),
				new Location(w, 125.5, 34, -56.5, -90, 0),
				new Location(w, 132.5, 40, -109.5, -90, 0),
				new Location(w, 166.5, 35, -106.5, 30, 0),
				new Location(w, 178.5, 38, -97.5, 45, 0),
				new Location(w, 178.5, 34, -73.5, 135, 0),
				new Location(w, 163.5, 46, -63.5, 135, 0)
				)
				),
		
		BASTION("&cBastion",
				new MIB(Material.POLISHED_BLACKSTONE_BRICKS).setLocname("BASTION"),
				new Location(w, -38.5, 50, 124.5, -90, 0),
				new Location(w, -42.5, 102, 116.5, -90, 0),
				Arrays.asList(
				new Location(w, -54.5, 33, 126.5, -135, 0),
				new Location(w, -53.5, 36, 114.5, -45, 0),
				new Location(w, -21.5, 35, 112.5, 0, 0),
				new Location(w, -19.5, 39, 130.5, 135, 0),
				new Location(w, -27.5, 29, 139.5, 90, 0),
				new Location(w, -50.5, 22, 120.5, 90, 0),
				new Location(w, -21.5, 19, 115.5, 45, 0),
				new Location(w, -21.5, 19, 130.5, -180, 0),
				new Location(w, -4.5, 70, 103.5, 90, 0),
				new Location(w, -1.5, 71, 128.5, 90, 0),
				new Location(w, -2.5, 61, 112.5, 90, 0),
				new Location(w, -5.5, 35, 109.5, 90, 0)				
				)
				),
		BLACKHOLE("&3Black Hole",
				new MIB(Material.BLACK_CONCRETE).setLocname("BLACKHOLE"),
				loc(130.5, 60, 53.5, -90),
				loc(129.5, 74, 56.5, -90),
				Arrays.asList(
				loc(119.5, 42, 28.5, -45),
				loc(108.5, 41, 53.5, -90),
				loc(131.5, 45, 40.5, 0),
				loc(121.5, 45, 74.5, -180),
				loc(114.5, 34, 65.5, -135),
				loc(155.5, 39, 65.5, 135),
				loc(127.5, 37, 59.5, -90),
				loc(142.5, 35, 49.5, -90)
				)
				),
		MUSHROOM("&5Mushroom Mayhem",
				new MIB(Material.MYCELIUM).setLocname("MUSHROOM"),
			loc(308.5, 97, -85.5, -90),
			loc(307.5, 122, -84.5, -90),
			Arrays.asList(
					loc(328.5, 68, -72.5, 135),
					loc(288.5, 80, -78.5, -90),
					loc(290.5, 66, -111.5, -35),
					loc(322.5, 68, -109.5, 45),
					loc(339.5, 68, -88.5, 90),
					loc(308.5, 82, -53.5, -180),
					loc(334.5, 95, -59.5, 135),
					loc(351.5, 95, -94.5, 90)
		)
		),
		BEACH("&1Squids Sands",
				new MIB(Material.SAND).setLocname("BEACH"),
			loc(7.5, 67, -86.5, -90),
			loc(6.5, 86, -85.5, -90),
			Arrays.asList(
				loc(36.5, 43, -113.5, 0),
				loc(46.5, 40, -76.5, 90),
				loc(22.5, 39, -51.5, 180),
				loc(-7.5, 41, -118.5, -45),
				loc(-16.5, 33, -110.5, -45),
				loc(-22.5, 32, -76.5, -90),
				loc(46.5, 40, -92.5, 90),
				loc(-18.5, 39, -98.5, 90)
				)
			),
		JUNGLE(	"&aJumanji - Welcome to the Jungle",
				new MIB(Material.JUNGLE_WOOD).setLocname("JUNGLE"),
				loc(-130.5, 60, -56.5, 90),
				loc(-132.5, 92, -56.5, 90),
				Arrays.asList(
					loc(-114.5, 62, -40.5, -180),
					loc(-108.5, 47, -32.5, 90),
					loc(-125.5, 49, -22.5, -180),
					loc(-164.5, 42, -51.5, -180),
					loc(-155.5, 39, -74.5, -90),
					loc(-118.5, 38, -81.5, 0),
					loc(-108.5, 49, -78.5, 45),
					loc(-123.5, 69, -66.5, 0)				
					)
				),
		END(	"&eThe End",
				new MIB(Material.END_STONE).setLocname("END"),
				loc(67.5, 93, -235.5, 90),
				loc(65.5, 108, -236.5, 90),
				Arrays.asList(
						loc(25.5, 62, -235.5, -90),
						loc(40.5, 62, -260.5, -45),
						loc(66.5, 62, -274.5, 0),
						loc(97.5, 60, -262.5, 45),
						loc(94.5, 60, -212.5, 135),
						loc(66.5, 62, -193.5, -180),
						loc(41.5, 60, -206.5, -135),
						loc(110.5, 60, -235.5, 90)
						)
				
				),
		TEMPLE(	"&6Cleopatras Cove",
				new MIB(Material.SANDSTONE).setLocname("TEMPLE"),
				loc(-128.5, 90, -216.5, 0),
				loc(-126.5, 118, -217.5, 0),
				Arrays.asList(
						loc(-93.5, 44, -216.5, 90),
						loc(-126.5, 44, -249.5, 0),
						loc(-159.5, 44, -217.5, -90),
						loc(-127.5, 44, -185.5, 180),
						loc(-135.5, 54, -225.5, -45),
						loc(-118.5, 54, -208.5, 135),
						loc(-125.5, 73, -215.5, 135),
						loc(-152.5, 44, -191.5, -135)
						)
				);
		
		
		private ItemStack icon;
		private Location lobbyspawn, centre;
		private List<Location> spawnLocations;
		private String name;
		
		MapType(String name, MIB Icon, Location centre, Location LobbySpawn, List<Location> spawnLocations){
			this.lobbyspawn = LobbySpawn;
			this.spawnLocations = spawnLocations;
			this.centre = centre;
			this.name = name;
			this.icon = Icon.setName(name).build();
		}
		
		public Location getLobbySpawn() { return lobbyspawn; }
		public List<Location> getSpawnLocations() { return spawnLocations; }
		public ItemStack getIcon() { return icon; }
		public Location getCentre() { return centre; }
		public World getWorld() { return w; }
		public String getName() { return u.cc(name); }
	}
	
	private static Location loc(double x, double y, double z, int yaw) {
		return new Location(w, x, y, z, yaw, 0);
	}
	
	
}