package pl.dyrtcraft.dyrtcraftminez.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.themolka.minez.API;
import pl.themolka.minez.MineZ;

public class DolaczCommand implements CommandExecutor {

	MineZ plugin;
	
	public DolaczCommand(MineZ mineZ) {
		plugin = mineZ;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Metoda nie wykonywalna z konsoli!");
			return true;
		}
		Player player = (Player) sender;
		if(!(args.length==1)) {
			sender.sendMessage(ChatColor.GOLD + "Nie podano nazwy mapy/zbyt duzo argumentów!");
			sender.sendMessage(ChatColor.GRAY + "Dostepne mapy to " + API.mapy(ChatColor.GRAY));
			sender.sendMessage(ChatColor.GRAY + "/dolacz <nazwa mapy>");
			return true;
		}
		if(!(player.getLocation().getWorld().getName().equalsIgnoreCase("Spawn"))){
			player.sendMessage(ChatColor.RED + "Nie mozesz rozpoczac nowej rozgrywki, jezeli obecnie jestes w grze!");
			return true;
		}
		API.spawnPlayer(player, args[0]);
		return true;
	}
	
}
