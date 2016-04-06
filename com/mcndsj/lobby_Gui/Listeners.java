package com.mcndsj.lobby_Gui;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Listeners {
	
	@EventHandler
	public void interact(PlayerInteractEvent evt){
		if(evt.getAction() == Action.PHYSICAL
				|| evt.getItem() == null 
				|| evt.getPlayer().getItemInHand() == null 
				|| evt.getPlayer().getItemInHand().getType() == Material.AIR){
			return;
		}
		
	}
}
