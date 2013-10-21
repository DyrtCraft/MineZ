package pl.dyrtcraft.dyrtcraftminez.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.themolka.minez.MineZ;
import pl.themolka.minez.Sklep;

public class SklepCommand implements CommandExecutor {

	MineZ plugin;
	
	public SklepCommand(MineZ mineZ) {
		plugin = mineZ;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(args.length==0)) {}
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Metoda nie wykonywalna z konsoli!");
			return true;
		}
		if(!(sender.isOp() || sender.hasPermission("minez.vip"))) {
			sender.sendMessage(ChatColor.RED + "Nie mozesz korzystaz ze sklepu z komendy!");
			sender.sendMessage(ChatColor.GOLD + "Kup range VIP, aby móc korzystac ze sklepu i wiele wiele wiecej!");
			return true;
		}
		Player player = (Player) sender;
		Sklep.showSklep(player);
		return true;
	}
	
}
