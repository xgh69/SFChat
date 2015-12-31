package me.xgh69.sfchat.main;

import java.io.File;

import me.xgh69.sfchat.commands.ChatCommand;
import me.xgh69.sfchat.listeners.PlayerListener;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SFChat extends JavaPlugin
{
	private static SFChat sfchat;
	private PlayerListener playerListener;
	private ChatCommand chatCommand;
	private SFChatUtils utils;
	private File configFile = new File("plugins" + File.separator + NAME + File.separator + "config.yml");
	
	public static final String NAME = "SFChat";
	public static final String VERION = "1.1";
	
	public static SFChat getInstance()
	{
		return sfchat;
	}
	
	public SFChatUtils getUtils()
	{
		return utils;
	}
	
	@Override
	public void onLoad()
	{
		sfchat = this;
		utils = new SFChatUtils();
	}
	
	@Override
	public void onEnable()
	{
		playerListener = new PlayerListener();
		chatCommand = new ChatCommand();
		Bukkit.getPluginManager().registerEvents(playerListener, this);
		getCommand("sfchat").setExecutor(chatCommand);
		if(!configFile.exists())
		{
			saveDefaultConfig();
		}
	}
}
