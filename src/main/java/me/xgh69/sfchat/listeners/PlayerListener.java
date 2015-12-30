package me.xgh69.sfchat.listeners;

import me.xgh69.sfchat.main.SFChat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener
{
	private SFChat plugin;
	
	public PlayerListener()
	{
		plugin = SFChat.getInstance();
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent evt)
	{
		Player player = evt.getPlayer();
		
		if(!plugin.getUtils().isChatEnable() && !player.hasPermission("sfchat.admin"))
		{
			player.sendMessage(plugin.getUtils().getMessage("chat-locked"));
			evt.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent evt)
	{
		Player player = evt.getPlayer();
		
		if(!plugin.getUtils().isVisible(player.getName()))
		{
			evt.setJoinMessage("");
		}
		else
		{
			evt.setJoinMessage(plugin.getUtils().getMessage("join-message").replace("$player", player.getName()));
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent evt)
	{
		Player player = evt.getPlayer();
		
		if(!plugin.getUtils().isVisible(player.getName()))
		{
			evt.setQuitMessage("");
		}
		else
		{
			evt.setQuitMessage(plugin.getUtils().getMessage("quit-message").replace("$player", player.getName()));
		}
	}
}
