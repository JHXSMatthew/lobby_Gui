package com.mcndsj.lobby_Gui;

import org.bukkit.plugin.java.JavaPlugin;

public class LobbyGui extends JavaPlugin{
	
	private static LobbyGui instance;
	
	public void onEnable(){
		instance = this;
	}
	
	public static LobbyGui getInstance(){
		return instance;
	}

}
