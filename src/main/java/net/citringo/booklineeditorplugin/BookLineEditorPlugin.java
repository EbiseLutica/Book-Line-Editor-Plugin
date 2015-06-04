/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.citringo.booklineeditorplugin;
import org.bukkit.plugin.java.JavaPlugin;
/**
 *
 * @author Citringo
 */
public class BookLineEditorPlugin extends JavaPlugin {
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
}
