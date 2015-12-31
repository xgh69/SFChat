package me.xgh69.sfchat.commands;

import me.xgh69.sfchat.main.SFChat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatCommand implements CommandExecutor
{
	private SFChat plugin;
	
	public ChatCommand()
	{
		plugin = SFChat.getInstance();
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(!sender.hasPermission("sfchat.admin"))
		{
			sender.sendMessage(plugin.getUtils().getMessage("no-perm"));
			return true;
		}
		
		if(args.length == 1)
		{
			if(args[0].equalsIgnoreCase("enable"))
			{
				plugin.getUtils().setChatEnable(true);
				sender.sendMessage(plugin.getUtils().getMessage("chat-enable").replace("$player", sender.getName()));
				return true;
			}
			else if(args[0].equalsIgnoreCase("disable"))
			{
				plugin.getUtils().setChatEnable(false);
				sender.sendMessage(plugin.getUtils().getMessage("chat-disable").replace("$player", sender.getName()));
				return true;
			}
			else if(args[0].equalsIgnoreCase("clear"))
			{
				for(Player p : Bukkit.getOnlinePlayers())
				{
					for(int i = 0;i < 300;i++)
					{
						p.sendMessage(" ");
					}
				}
				
				Bukkit.broadcastMessage(plugin.getUtils().getMessage("chat-clear").replace("$player", sender.getName()));
				
				return true;
			}
			else
			{
				sender.sendMessage(plugin.getUtils().getMessage("usage"));
				return true;
			}
		}
		else if(args.length == 2)
		{
			if(args[0].equalsIgnoreCase("join"))
			{
				if(args[1].equalsIgnoreCase("enable"))
				{
					if(sender instanceof Player)
					{
						sender.sendMessage(plugin.getUtils().getMessage("chat-join-enable"));
						plugin.getUtils().setVisible(sender.getName(), true);
						return true;
					}
					else
					{
						sender.sendMessage(plugin.getUtils().getMessage("chat-join-console"));
						return true;
					}
				}
				else if(args[1].equalsIgnoreCase("disable"))
				{
					if(sender instanceof Player)
					{
						sender.sendMessage(plugin.getUtils().getMessage("chat-join-disable"));
						plugin.getUtils().setVisible(sender.getName(), false);
						return true;
					}
					else
					{
						sender.sendMessage(plugin.getUtils().getMessage("chat-join-console"));
						return true;
					}
				}
				else
				{
					sender.sendMessage(plugin.getUtils().getMessage("usage"));
					return true;
				}
			}
			else
			{
				sender.sendMessage(plugin.getUtils().getMessage("usage"));
				return true;
			}
		}
		else
		{
			sender.sendMessage(plugin.getUtils().getMessage("usage"));
			return true;
		}
	}

}
