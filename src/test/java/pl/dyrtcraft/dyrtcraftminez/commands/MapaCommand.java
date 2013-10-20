package pl.dyrtcraft.dyrtcraftminez.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.themolka.minez.MineZ;

public class MapaCommand implements CommandExecutor {

	MineZ plugin;
	
	public MapaCommand(MineZ mineZ) {
		plugin = mineZ;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(args.length==0)) {}
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Metoda nie wykonywalna z konsoli!");
			return true;
		}
		Player player = (Player) sender;
		sender.sendMessage(ChatColor.GRAY + "Mapa na której obecnie sie znajdujesz to \"" + ChatColor.GOLD + player.getLocation().getWorld().getName() + ChatColor.GRAY + "\".");
		return true;
	}
	
}
