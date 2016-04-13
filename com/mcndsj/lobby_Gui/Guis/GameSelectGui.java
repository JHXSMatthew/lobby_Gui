package com.mcndsj.lobby_Gui.Guis;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.mcndsj.lobby_Gui.Items.AbstractItem;

public class GameSelectGui extends AbstractGui{

	
	
	public GameSelectGui(AbstractItem item) {
		super("选择游戏");
		super.setAbstractItem(item);
		// TODO Auto-generated constructor stub
	}

	private void register(){
		
	}
	
	@Override
	protected void callOnClick(Player p, ItemStack item) {
		
		
	}
	

}
