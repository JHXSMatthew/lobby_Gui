package com.mcndsj.lobby_Gui.lobbySelector;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.Inventory;

public class SelectorManager {
	
	private String bungeeName;
	private List<Inventory> invList;
	
	public SelectorManager(){
		invList = new ArrayList<Inventory>();
	}
	
	public void register(String bungeeName, int count){
		this.bungeeName = bungeeName;
		
	}

}
