package me.xgh69.sfchat.main;

import org.bukkit.ChatColor;

public class SFChatUtils
{
	private SFChat plugin;
	private boolean chat = true;
	
	public SFChatUtils()
	{
		plugin = SFChat.getInstance();
	}
	
	public String fixColors(String s)
	{
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	public void setChatEnable(boolean b)
	{
		chat = b;
	}
	
	public boolean isChatEnable()
	{
		return chat;
	}
	
	public boolean isVisible(String userName)
	{
		if(plugin.getConfig().contains("users." + userName))
		{
			return plugin.getConfig().getBoolean("users." + userName);
		}
		else
		{
			return true;
		}
	}
	
	public String getMessage(String s)
	{
		s = s.replace(".", "_");
		if(!plugin.getConfig().contains("messages." + s))
		{
			return fixColors("&fSan&9Fierro&f&l: &7Nie znaleziono wiadomosci.");
		}
		
		return fixColors(plugin.getConfig().getString("messages." + s));
	}
	
	public void setVisible(String userName, boolean b)
	{
		plugin.getConfig().set("users." + userName, b);
		plugin.saveConfig();
	}
}
