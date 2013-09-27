package pl.themolka.minez;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftPlugin;
import pl.themolka.minez.listeners.PlayerJoinAndQuitListener;

/**
 * @author TheMolkaPL
 * @since Development Build 001
 * @see MineZ
 */
public class MineZCommand implements CommandExecutor {

	MineZ plugin;
	PlayerJoinAndQuitListener pjaql;
	
	public MineZCommand(MineZ mineZ) {
		plugin = mineZ;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		if(command.getName().equalsIgnoreCase("minez") || command.getName().equalsIgnoreCase("mz")) {
			if(args.length==0) {
				return pomocArg(sender);
			}
			if(args.length==1) {
				if(args[0].equalsIgnoreCase("admin") || args[0].equalsIgnoreCase("a")) {
					return erAdminArg(sender);
				}
				if(args[0].equalsIgnoreCase("kit")) {
					return kitArg(sender);
				}
				if(args[0].equalsIgnoreCase("pomoc")) {
					return pomocArg(sender);
				}
				if(args[0].equalsIgnoreCase("reload")) {
					return reloadArg(sender);
				}
				if(args[0].equalsIgnoreCase("sklep")) {
					return sklepArg(sender);
				}
				if(args[0].equalsIgnoreCase("spawn")) {
					return spawnArg(sender);
				}
				if(args[0].equalsIgnoreCase("staff")) {
					return staffArg(sender);
				} else {
					return erArg(sender, "Podano bledny argument!");
				}
			}
			if(args.length==2) {
				if(args[0].equalsIgnoreCase("kit")) {
					if(args[1].equalsIgnoreCase("vip")) {
						return kitVIPArg(sender);
					}
					return erArg(sender, "Podano bledny argument!");
				} else {
					return erArg(sender, "Podano bledny argument!");
				}
			} else {
				return erArg(sender, "Zbyt duzo argumentów!");
			}
		}
		return false;
	}
	
	protected boolean erArg(CommandSender sender, String er) {
		sender.sendMessage(ChatColor.RED + er);
		sender.sendMessage(ChatColor.RED + "Uzycie: " + plugin.getCommand("minez").getUsage());
		return true;
	}
	
	protected boolean erAdminArg(CommandSender sender) {
		if(!(sender.isOp())) {
			sender.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
			return true;
		}
		sender.sendMessage(ChatColor.GOLD + "========== Pomoc ==========");
		sender.sendMessage(ChatColor.RED + "/minez kit [vip]");
		sender.sendMessage(ChatColor.RED + "/minez pomoc");
		sender.sendMessage(ChatColor.RED + "/minez reload");
		sender.sendMessage(ChatColor.RED + "/minez spawn [gracz]");
		sender.sendMessage(ChatColor.RED + "/minez staff");
		return true;
	}
	
	protected boolean kitArg(CommandSender sender) {
		if(!(sender.isOp())) {
			sender.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
			return true;
		}
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Nie mozesz wykonac tej komendy z poziomu konsoli!");
			return true;
		}
		Player player = (Player) sender;
		MineZ.sendStarterKit(player);
		sender.sendMessage(ChatColor.GRAY + "Otrzymanie startowego kitu.");
		return true;
	}
	
	protected boolean kitVIPArg(CommandSender sender) {
		if(!(sender.isOp())) {
			sender.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
			return true;
		}
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Nie mozesz wykonac tej komendy z poziomu konsoli!");
			return true;
		}
		Player player = (Player) sender;
		MineZ.sendStarterVIPKit(player);
		sender.sendMessage(ChatColor.GRAY + "Otrzymanie startowego kitu dla VIP.");
		return true;
	}
	
	protected boolean pomocArg(CommandSender sender) {
		sender.sendMessage(ChatColor.GOLD + "========== Pomoc ==========");
		sender.sendMessage(ChatColor.GRAY + "Witaj na serwerze MineZ DyrtCraft Network!");
		sender.sendMessage(ChatColor.GRAY + "Aby rozpoczac gre uzyj komendy /minez spawn!");
		return true;
	}
	
	protected boolean reloadArg(CommandSender sender) {
		if(!(sender.isOp())) {
			sender.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
			return true;
		} else {
			if(MineZ.isDyrtCraftXPEnabled()) {
				DyrtCraftPlugin.sendMsgToOp(sender.getName() + " przeladowyuje plik config.yml", 0);
				plugin.reloadConfig();
				DyrtCraftPlugin.sendMsgToOp(sender.getName() + " przeladowal plik config.yml", 0);
				return true;
			}
			sender.sendMessage(ChatColor.GRAY + "Przeladowywanie pliku config.yml...");
			plugin.reloadConfig();
			sender.sendMessage(ChatColor.GRAY + "Pomyslnie przeladowano plik config.yml!");
			return true;
		}
	}
	
	protected boolean sklepArg(CommandSender sender) {
		if(!(sender.isOp())) {
			sender.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
			return true;
		}
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Nie mozesz wykonac tej komendy z poziomu konsoli!");
			return true;
		}
		Player player = (Player) sender;
		Sklep.showSklep(player);
		return true;
	}
	
	protected boolean spawnArg(CommandSender sender) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Nie mozesz wykonac tej komendy z poziomu konsoli!");
			return true;
		}
		Player player = (Player) sender;
		// Randomowa teleportacja na mape
		Random random = new Random();
		int randomPlayerNumber = random.nextInt(plugin.getServer().getOnlinePlayers().length);
		
		Player gracz = plugin.getServer().getOnlinePlayers()[randomPlayerNumber];
		player.teleport(gracz);
		
		// Glod
		player.setFoodLevel(20);
		player.setGameMode(GameMode.ADVENTURE);
		player.setHealth(20.0);
		
		// Porada
		player.sendMessage(ChatColor.GOLD + "========== Porada ==========");
		player.sendMessage(ChatColor.GRAY + "Zostales przeteleportowany losowo na mape.");
		player.sendMessage(ChatColor.GRAY + "Witaj! Wlasnie rozpoczales walke o przetrwanie!");
		player.sendMessage(ChatColor.GRAY + "Zdobywaj itemy w wioskach, walcz z Zombie, przetrwaj!");
		player.sendMessage(ChatColor.GRAY + "- - - - - > " + ChatColor.BOLD + "Dobrej zabawy! :D");
		
		// Itemy
		if(player.hasPermission("minez.vip") || player.isOp()) {
			MineZ.sendStarterVIPKit(player);
			return true;
		}
		MineZ.sendStarterKit(player);
		return true;
	}
	
	protected boolean staffArg(CommandSender sender) {
		Object lista = pjaql.administracja_online.toArray();
		
		sender.sendMessage(ChatColor.GOLD + "Lista administracji online:");
		sender.sendMessage(ChatColor.GRAY + lista.toString());
		return true;
	}
	
}
