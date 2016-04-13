package com.mcndsj.lobby_Gui.Items;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.mcndsj.lobby_Gui.LobbyGui;
import com.mcndsj.lobby_Gui.Utils.ItemFactory;

public abstract class AbstractItem implements Listener{
	private HashMap<Integer,ItemStack> itemMap = null;
	
	private String title = null;
	private int size = -1;
	private String name = null;
	private byte data = 0;
	private Material type = null;
	private ItemMeta meta = null;
	private int slot = -1;
	
	public AbstractItem(String title,int size ,String itemName){
		this.name = itemName;
	}
	
	public AbstractItem(String title,int size, String itemName,Material material,ItemMeta meta,int slot){
		this.name = itemName;
		this.meta = meta;
		this.slot = slot;
		this.type = material;
		LobbyGui.getInstance().getServer().getPluginManager().registerEvents(this, LobbyGui.getInstance());
	}
	
	public AbstractItem(String title,int size ,String itemName,Material material,byte data,ItemMeta meta,int slot){
		this.name = itemName;
		this.meta = meta;
		this.slot = slot;
		this.type = material;
		this.data = data;
		LobbyGui.getInstance().getServer().getPluginManager().registerEvents(this, LobbyGui.getInstance());
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onJoin(PlayerJoinEvent evt){
		evt.getPlayer().getInventory().setItem(slot,ItemFactory.create(type, data, meta) );
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent evt){
		if(evt.getAction() == Action.PHYSICAL)
			return;
		if(evt.getPlayer().getItemInHand() == null 
				|| evt.getPlayer().getItemInHand().getType() != type 
				|| !evt.getPlayer().getItemInHand().hasItemMeta()
				|| evt.getPlayer().getItemInHand().getItemMeta() != meta){
			return;
		}
		
		evt.getPlayer().closeInventory();
		Inventory i = Bukkit.getServer().createInventory(null, size,title);
		for(Entry<Integer, ItemStack> entry : itemMap.entrySet()){
			i.setItem(entry.getKey(), entry.getValue().clone());
		}
		evt.getPlayer().openInventory(i);
	}
	
	public String getTitle(){
		return title;
	}
	
	
	public ItemStack getItem(int slot){
		return itemMap.get(slot);
	}
	
	public boolean registerItem(int key,ItemStack item){
		if(itemMap.containsKey(key))
			return false;
		
		itemMap.put(key, item);
		return true;
	}
	
	public ItemStack removeItem(int i){
		return itemMap.remove(i);
	}

}
