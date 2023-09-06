package me.superpenguin.supercraftbros.interfaces;

import me.superpenguin.supercraftbros.objects.GUI;

public interface Paged {
	
	public boolean hasNextPage();
	public int getPage();
	public GUI nextPage();
	public GUI previousPage();

}
