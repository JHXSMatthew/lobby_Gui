package com.mcndsj.lobby_Gui.Guis;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.mcndsj.lobby_Gui.LobbyGui;
import com.mcndsj.lobby_Gui.Items.AbstractItem;

public abstract class AbstractGui implements Listener {
	private String title = null;
	protected AbstractItem item = null;
	
	public AbstractGui(){
		LobbyGui.getInstance().getServer().getPluginManager().registerEvents(this, LobbyGui.getInstance());
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent evt){
		if(evt.getClickedInventory() == null &&
				evt.getClickedInventory().getTitle() != null &&
				!evt.getClickedInventory().getTitle().equals(title)){
			return;
		}
		if(evt.getCurrentItem() == null || evt.getCurrentItem().getType() == Material.AIR)
			return;
		
		
		callOnClick((Player)evt.getWhoClicked(), evt.getCurrentItem());
	}
	
	protected abstract void callOnClick(Player p,ItemStack item );
	
	protected void setAbstractItem (AbstractItem item){
		this.item = item;
	}
	public AbstractItem getItem(){
		return item;
	}
}