package com.mcndsj.lobby_Gui.items;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
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
import com.mcndsj.lobby_Gui.utils.ItemFactory;

public abstract class AbstractItem implements Listener{
	private HashMap<Integer,ItemStack> itemMap = new HashMap<Integer,ItemStack>();
	
	private String title = null;
	private int size = -1;
	private String name = null;
	private byte data = 0;
	private Material type = null;
	private ItemMeta meta = null;
	private int slot = -1;
	
	public AbstractItem(String title,int size ,String itemName){
		this.title = title;
		this.name = itemName;
		this.size = size;
	}
	
	public AbstractItem(String title,int size,Material material,ItemMeta meta,int slot){
		this.title = title;
		this.name = meta.getDisplayName();
		this.size = size;
		this.meta = meta;
		this.slot = slot;
		this.type = material;
		LobbyGui.getInstance().getServer().getPluginManager().registerEvents(this, LobbyGui.getInstance());
	}
	
	public AbstractItem(String title,int size ,Material material,byte data,ItemMeta meta,int slot){
		this.name = meta.getDisplayName();
		this.meta = meta;
		this.size = size;
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
				|| !evt.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(meta.getDisplayName())){
			return;
		}
		
		evt.getPlayer().closeInventory();
	
		Inventory i = Bukkit.getServer().createInventory(null, size ,title);
		for(Entry<Integer, ItemStack> entry : itemMap.entrySet()){
			i.setItem(entry.getKey(), entry.getValue().clone());
		}
		evt.getPlayer().openInventory(i);
		evt.getPlayer().playSound(evt.getPlayer().getLocation(), Sound.CLICK, 1, 1);

	}
	
	public String getTitle(){
		return title;
	}
	
	
	/**
	 * @param slot
	 * @return
	 * 
	 * get item from click position
	 */
	public ItemStack getItem(int slot){
		if(!itemMap.containsKey(slot)){
			return null;
		}else{
			return itemMap.get(slot);
		}
	}
	
	/**
	 * @param key
	 * @param item
	 * @return
	 * 
	 * register items to inventory
	 */
	public boolean registerItem(int key,ItemStack item){
		if(itemMap.containsKey(key))
			return false;
		
		itemMap.put(key, item);
		return true;
	}
	
	/**
	 * @param i
	 * @return
	 * 
	 * remove item from inventory
	 */
	public ItemStack removeItem(int i){
		return itemMap.remove(i);
	}

}
