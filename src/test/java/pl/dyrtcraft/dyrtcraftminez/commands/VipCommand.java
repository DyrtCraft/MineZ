package pl.dyrtcraft.dyrtcraftminez.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import pl.themolka.minez.MineZ;

public class VipCommand implements CommandExecutor {

	MineZ plugin;
	
	public VipCommand(MineZ mineZ) {
		plugin = mineZ;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(args.length==0)) {}
		sender.sendMessage(ChatColor.GOLD + "========== Informacje o randze VIP ==========");
		sender.sendMessage(ChatColor.GOLD + "Funkcje:");
		sender.sendMessage(ChatColor.GRAY + "   - Lepsze oraz wiecej itemów przy rozpoczynaniu rozgrywki!");
		sender.sendMessage(ChatColor.GRAY + "   - Sklep dostepny w kazdej chwili z poziomu emeraldu w ekwipuku lub komendy /sklep!");
		sender.sendMessage(ChatColor.GOLD + "Czas trwania: " + ChatColor.GRAY + "31 dni");
		sender.sendMessage(ChatColor.GOLD + "Koszt: " + ChatColor.GRAY + null);
		return true;
	}

}
