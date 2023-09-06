package me.Penguin.SuperCraftBros.interfaces;

import me.Penguin.SuperCraftBros.objects.GUI;

public interface Paged {
	
	public boolean hasNextPage();
	public int getPage();
	public GUI nextPage();
	public GUI previousPage();

}
