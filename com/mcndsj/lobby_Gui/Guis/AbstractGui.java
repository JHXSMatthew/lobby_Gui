package com.mcndsj.lobby_Gui.Guis;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.mcndsj.lobby_Gui.LobbyGui;

public abstract class AbstractGui implements Listener {
	private String title = null;
	
	public AbstractGui(String title){
		this.title = title;
		LobbyGui.getInstance().getServer().getPluginManager().registerEvents(this, LobbyGui.getInstance());
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent evt){
		if(evt.getClickedInventory() == null &&
				evt.getClickedInventory().getTitle() != null &&
				!evt.getClickedInventory().getTitle().equals(title)){
			return;
		}
		callOnClick((Player)evt.getWhoClicked(), evt.getCurrentItem());
	}
	
	protected abstract void callOnClick(Player p,ItemStack item);
	
}