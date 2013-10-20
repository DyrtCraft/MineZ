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
		if(!(args.length==0)) {}
		sender.sendMessage(ChatColor.GOLD + "========== Regulamin ==========");
		sender.sendMessage(ChatColor.YELLOW + "Wkrótce! ;D");
		return true;
	}

}
