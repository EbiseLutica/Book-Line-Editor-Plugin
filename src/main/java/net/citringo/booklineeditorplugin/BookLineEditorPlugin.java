/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.citringo.booklineeditorplugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 *
 * @author Citringo
 */
public class BookLineEditorPlugin extends JavaPlugin
{

	@Override
	public void onEnable()
	{
		getLogger().info("BookLineEditorPlugin is enabled!");
	}

	@Override
	public void onDisable()
	{
		getLogger().info("BookLineEditorPlugin is disabled!");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if (cmd.getName().equalsIgnoreCase("blep"))
		{
			if (!(sender instanceof Player))
			{
				sender.sendMessage("You are not player!");
				return true;
			}
			if (args.length < 1)
				return false;
			
			Player player = (Player)sender;
			
			switch (args[0])
			{
				case "edit":	// edit line text
					if (args.length != 3)
					{
						sender.sendMessage(ChatColor.RED + "/blep edit <line> <text>");
						return true;
					}
					
					break;
				case "list":	// list start end
					if (args.length != 3)
					{
						sender.sendMessage(ChatColor.RED + "/blep list start end");
						return true;
					}
					break;
				case "delete":	// delete line
					if (args.length != 2)
					{
						sender.sendMessage(ChatColor.RED + "/blep delete line");
						return true;
					}
					break;
				case "insert":	// insert line
					if (args.length != 2)
					{
						sender.sendMessage(ChatColor.RED + "/blep insert line");
						return true;
					}
					break;
			}
			//sender.sendMessage(String.format("%s %d", args[1], args.length));
			return true;
		}
		return false;
	}

}
