package pl.dyrtcraft.dyrtcraftminez.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import pl.themolka.minez.MineZ;

public class RegulaminCommand implements CommandExecutor {

	MineZ plugin;
	
	public RegulaminCommand(MineZ mineZ) {
		plugin = mineZ;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length == 0) {
			sender.sendMessage(rules());
			return true;
		}
		if(args.length == 2) {
			if(args[0].equalsIgnoreCase("staff")) {
				sender.sendMessage(staffRules());
				return true;
			} else {
				sender.sendMessage(rules());
				return true;
			}
		} else {
			sender.sendMessage(rules());
			return true;
		}
	}
	
	private String rules() {
		String a = ChatColor.YELLOW + "Wkrótce! ;D";
		return a;
	}
	
	private String staffRules() {
		String a = ChatColor.YELLOW + "Wkrótce! ;D";
		return a;
	}

}
