/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.citringo.booklineeditorplugin;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.BookMeta;

/**
 *
 * @author Citringo
 */
public class BookLineEditorPlugin extends JavaPlugin
{

	static String BR = System.getProperty("line.separator");
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
				sender.sendMessage("プレイヤーのみこのコマンドを使用できます。");
				return true;
			}
			if (args.length < 1)
				return false;
			
			Player player = (Player)sender;
			ItemStack book;
			if ((book = player.getItemInHand()).getType() != Material.BOOK_AND_QUILL)
			{
				sender.sendMessage("手に「本と羽ペン」を持ってください！！");
				return true;
			}
			
			BookMeta bm = (BookMeta)book.getItemMeta();
			
			List<String> lines = new ArrayList<>(Arrays.asList(String.join(BR, bm.getPages()).split(BR)));
			switch (args[0])
			{
				case "edit":	// edit line text
					if (args.length != 3)
					{
						sender.sendMessage("/blep edit <line> <text>");
						return true;
					}
					Integer line = Integer.parseInt(args[1]);
					if (line >= lines.size())
					{
						getLogger().info(String.format("line: %d, size: %d", line, lines.size()));
						for(int i = lines.size() - 1; i <= line; i++)
						{
							getLogger().info(String.format("i: %d", i));
						
							lines.add("");
							
						}
					}
					lines.set(line, args[2]);
					break;
				case "list":	// list start end
					if (args.length != 3)
					{
						sender.sendMessage("/blep list <start> <end>");
						return true;
					}
					Integer start = Integer.parseInt(args[1]);
					Integer end = Integer.parseInt(args[2]);
					if (start >= lines.size() || end >= lines.size())
					{
						sender.sendMessage("行数がオーバーしています。");
						return true;
					}
					for (int i = start; i <= end; i++)
						sender.sendMessage(ChatColor.WHITE + lines.get(i));
					break;
				case "delete":	// delete line
					if (args.length != 2)
					{
						sender.sendMessage("/blep delete <line>");
						return true;
					}
					Integer line2 = Integer.parseInt(args[1]);
					if (line2 >= lines.size())
					{
						sender.sendMessage("行数がオーバーしています。");
						return true;
					}
					lines.remove(line2.intValue());
					break;
				case "insert":	// insert line
					if (args.length != 2)
					{
						sender.sendMessage("/blep insert <line>");
						return true;
					}
					Integer line3 = Integer.parseInt(args[1]);
					if (line3 >= lines.size())
					{
						sender.sendMessage("行数がオーバーしています。");
						return true;
					}
					lines.add(line3, "");
					break;
			}
			
			bm.setPages(String.join(BR, lines));
			book.setItemMeta(bm);
			
			//sender.sendMessage(String.format("%s %d", args[1], args.length));
			return true;
		}
		return false;
	}

}
