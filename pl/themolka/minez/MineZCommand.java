package pl.themolka.minez;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftPlugin;

public class MineZCommand implements CommandExecutor {

	MineZ plugin;
	
	public MineZCommand(MineZ mineZ) {
		plugin = mineZ;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		Player player = (Player) sender;
		
		if(command.getName().equalsIgnoreCase("minez") || command.getName().equalsIgnoreCase("mz")) {
			if(args.length==0) {
				return erArg(player, "Nie podano zadnego argumentu!");
			}
			if(args.length==1) {
				if(args[0].equalsIgnoreCase("admin") || args[0].equalsIgnoreCase("a")) {
					return erAdminArg(player);
				}
				if(args[0].equalsIgnoreCase("delspawn")) {
					return erArg(player, "Zbyt malo argumentów");
				}
				if(args[0].equalsIgnoreCase("kit")) {
					return kitArg(player);
				}
				if(args[0].equalsIgnoreCase("pomoc")) {
					return pomocArg(player);
				}
				if(args[0].equalsIgnoreCase("setspawn")) {
					return erArg(player, "Zbyt malo argumentów");
				}
				if(args[0].equalsIgnoreCase("spawn")) {
					return spawnArg(player);
				} else {
					return erArg(player, "Podano bledy argument!");
				}
			}
			if(args.length==2) {
				if(args[0].equalsIgnoreCase("delspawn")) {
					String liczba = args[1];
					return delSpawnArg(player, liczba);
				}
				if(args[0].equalsIgnoreCase("setspawn")) {
					String liczba = args[1];
					return setSpawnArg(player, liczba);
				}
				if(args[0].equalsIgnoreCase("spawn")) {
					String liczba = args[1];
					return spawnArg(player, liczba);
				} else {
					return erArg(player, "Podano bledy argument!");
				}
			} else {
				return erArg(player, "Zbyt duzo argumentów!");
			}
		}
		return false;
	}
	
	protected boolean erArg(Player player, String er) {
		player.sendMessage(ChatColor.RED + er);
		player.sendMessage(ChatColor.RED + "Uzycie: " + plugin.getCommand("minez").getUsage());
		return true;
	}
	
	protected boolean erAdminArg(Player player) {
		if(!(player.isOp())) {
			player.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
			return true;
		}
		player.sendMessage(ChatColor.GOLD + "========== Pomoc ==========");
		player.sendMessage(ChatColor.RED + "/minez delspawn <numer>");
		player.sendMessage(ChatColor.RED + "/minez kit");
		player.sendMessage(ChatColor.RED + "/minez pomoc");
		player.sendMessage(ChatColor.RED + "/minez reload");
		player.sendMessage(ChatColor.RED + "/minez setspawn <numer>");
		player.sendMessage(ChatColor.RED + "/minez spawn [numer]");
		return true;
	}
	
	protected boolean delSpawnArg(Player player, String numer) {
		if(!(player.isOp())) {
			player.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
			return true;
		}
		plugin.getConfig().set("spawny." + numer, null);
		plugin.saveConfig();
		DyrtCraftPlugin.sendMsgToOp(player.getName() + " usunal spawn " + numer, 0);
		return true;
	}
	
	protected boolean kitArg(Player player) {
		if(!(player.isOp())) {
			player.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
			return true;
		}
		if(!(player instanceof Player)) {
			player.sendMessage(ChatColor.RED + "Nie mozesz wykonac tej komendy z poziomu konsoli!");
			return true;
		}
		MineZ.sendStarterKit(player);
		player.sendMessage(ChatColor.GRAY + "Otrzymanie startowego kitu.");
		return true;
	}
	
	protected boolean pomocArg(Player player) {
		player.sendMessage(ChatColor.GOLD + "========== Pomoc ==========");
		player.sendMessage(ChatColor.GRAY + "Witaj na serwerze MineZ DyrtCraft Netowrk!");
		player.sendMessage(ChatColor.GRAY + "Aby rozpoczac gre uzyj komendy /minez spawn!");
		return true;
	}
	
	protected boolean setSpawnArg(Player player, String numer) {
		if(!(player.isOp())) {
			player.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
			return true;
		}
		if(!(player instanceof Player)) {
			player.sendMessage(ChatColor.RED + "Nie mozesz wykonac tej komendy z poziomu konsoli!");
			return true;
		}
		World world = player.getLocation().getWorld();
		double x = player.getLocation().getX();
		double y = player.getLocation().getY();
		double z = player.getLocation().getZ();
		
		plugin.getConfig().set("spawny." + numer + ".world", world);
		plugin.getConfig().set("spawny." + numer + ".x", x);
		plugin.getConfig().set("spawny." + numer + ".y", y);
		plugin.getConfig().set("spawny." + numer + ".z", z);
		plugin.saveConfig();
		DyrtCraftPlugin.sendMsgToOp(player.getName() + " utworzyl nowy spawn " + numer, 0);
		return true;
	}
	
	protected boolean spawnArg(Player player, String numer) {
		if(!(player.isOp())) {
			player.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
			return true;
		}
		if(!(player instanceof Player)) {
			player.sendMessage(ChatColor.RED + "Nie mozesz wykonac tej komendy z poziomu konsoli!");
			return true;
		}
		World world = plugin.getServer().getWorld("spawn." + numer + ".world");
		double x = plugin.getConfig().getDouble("spawny." + numer + ".x");
		double y = plugin.getConfig().getDouble("spawny." + numer + ".y");
		double z = plugin.getConfig().getDouble("spawny." + numer + ".z");
		player.teleport(new Location(world, x, y, z));
		player.sendMessage(ChatColor.GRAY + "Teleportacja do spawnu " + numer + "...");
		return true;
	}
	
	protected boolean spawnArg(Player player) {
		if(!(player instanceof Player)) {
			player.sendMessage(ChatColor.RED + "Nie mozesz wykonac tej komendy z poziomu konsoli!");
			return true;
		}
		//TODO Randomowa teleportacja
		return true;
	}
	
}
