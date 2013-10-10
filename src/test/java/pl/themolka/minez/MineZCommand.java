package pl.themolka.minez;

import org.bukkit.ChatColor;
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
		// Komenda /minez i /mz
		if(command.getName().equalsIgnoreCase("minez") || command.getName().equalsIgnoreCase("mz")) {
			// Liczba argumentow - 0
			if(args.length==0) {
				// Nie podano zadnych argumentow
				return pomocArg(sender);
			}
			// Liczba argumentow - 1
			if(args.length==1) {
				// Argument 0: about, version, author
				if(args[0].equalsIgnoreCase("about") || args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("author")) {
					return aboutArg(sender);
				}
				// Argument 0: admin, a
				if(args[0].equalsIgnoreCase("admin") || args[0].equalsIgnoreCase("a")) {
					return erAdminArg(sender);
				}
				// Argument 0: kit
				if(args[0].equalsIgnoreCase("kit")) {
					return kitArg(sender);
				}
				// Argument 0: pomoc, help, ?
				if(args[0].equalsIgnoreCase("pomoc") || args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("?")){
					return pomocArg(sender);
				}
				// Argument 0: reload
				if(args[0].equalsIgnoreCase("reload")) {
					return reloadArg(sender);
				}
				// Argument 0: sklep, shop
				if(args[0].equalsIgnoreCase("sklep") || args[0].equalsIgnoreCase("shop")) {
					return sklepArg(sender);
				}
				// Argument 0: spawn, start, join
				if(args[0].equalsIgnoreCase("spawn") || args[0].equalsIgnoreCase("start") || args[0].equalsIgnoreCase("join")) {
					return erArg(sender, "Nie podano nazwy mapy!");
				}
				// Argument 0: staff
				if(args[0].equalsIgnoreCase("staff")) {
					return staffArg(sender);
				} else {
					// Zaden z argumentow 0 nie zostal spelniony
					return erArg(sender, "Podano bledny argument!");
				}
			}
			// Liczba argumentow - 2
			if(args.length==2) {
				// Argument 0: kit
				if(args[0].equalsIgnoreCase("kit")) {
					// Argument 1: vip
					if(args[1].equalsIgnoreCase("vip")) {
						return kitVIPArg(sender);
					}
					// Zaden z argumentow 1 nie zostal spelniony
					return erArg(sender, "Podano bledny argument!");
				}
				// Argument 0: spawn, start, join
				if(args[0].equalsIgnoreCase("spawn") || args[0].equalsIgnoreCase("start") || args[0].equalsIgnoreCase("join")) {
					return spawnArg(sender, args[1]);
				} else {
					// Zaden z argumentow 0 nie zostal spelniony
					return erArg(sender, "Podano bledny argument!");
				}
			} else {
				// Liczba argumentow nie zostala spelniona
				return erArg(sender, "Zbyt duzo argumentów!");
			}
		}
		return false;
	}
	
	protected boolean erArg(CommandSender sender, String er) {
		API.debug("protected boolean erArg(CommandSender, String)");
		
		sender.sendMessage(ChatColor.RED + er);
		sender.sendMessage(ChatColor.RED + "Uzycie: " + plugin.getCommand("minez").getUsage());
		return true;
	}
	
	protected boolean aboutArg(CommandSender sender) {
		API.debug("protected boolean aboutArg(CommandSender)");
		
		sender.sendMessage(ChatColor.GOLD + " >==========[ " + ChatColor.BOLD + ChatColor.AQUA + "MineZ" + ChatColor.RESET + ChatColor.GOLD + " ]==========< ");
		sender.sendMessage(ChatColor.GOLD + "Wersja: " + ChatColor.GRAY + API.getPlugin().getVersion());
		sender.sendMessage(ChatColor.GOLD + "Autor: " + ChatColor.GRAY + API.getPlugin().getAuthors());
		sender.sendMessage(ChatColor.GOLD + " >==========[ " + ChatColor.BOLD + ChatColor.AQUA + "MineZ" + ChatColor.RESET + ChatColor.GOLD + " ]==========< ");
		return true;
	}
	
	protected boolean erAdminArg(CommandSender sender) {
		API.debug("protected boolean aboutArg(CommandSender)");
		
		if(!(sender.isOp())) {
			sender.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
			return true;
		}
		sender.sendMessage(ChatColor.GOLD + "========== Pomoc ==========");
		sender.sendMessage(ChatColor.RED + "/minez about");
		sender.sendMessage(ChatColor.RED + "/minez kit [vip]");
		sender.sendMessage(ChatColor.RED + "/minez pomoc");
		sender.sendMessage(ChatColor.RED + "/minez reload");
		sender.sendMessage(ChatColor.RED + "/minez sklep");
		sender.sendMessage(ChatColor.RED + "/minez spawn <mapa>");
		sender.sendMessage(ChatColor.RED + "/minez staff");
		return true;
	}
	
	protected boolean kitArg(CommandSender sender) {
		API.debug("protected boolean kitArg(CommandSender)");
		
		if(!(sender.isOp())) {
			sender.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
			return true;
		}
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Nie mozesz wykonac tej komendy z poziomu konsoli!");
			return true;
		}
		Player player = (Player) sender;
		API.sendStarterKit(player);
		sender.sendMessage(ChatColor.GRAY + "Otrzymanie startowego kitu.");
		return true;
	}
	
	protected boolean kitVIPArg(CommandSender sender) {
		API.debug("protected boolean kitVIPArg(CommandSender)");
		
		if(!(sender.isOp())) {
			sender.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
			return true;
		}
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Nie mozesz wykonac tej komendy z poziomu konsoli!");
			return true;
		}
		Player player = (Player) sender;
		API.sendStarterVIPKit(player);
		sender.sendMessage(ChatColor.GRAY + "Otrzymanie startowego kitu dla VIP.");
		return true;
	}
	
	protected boolean pomocArg(CommandSender sender) {
		API.debug("protected boolean pomocArg(CommandSender)");
		
		sender.sendMessage(ChatColor.GOLD + "========== Pomoc ==========");
		sender.sendMessage(ChatColor.GRAY + "Witaj na serwerze MineZ DyrtCraft Network!");
		sender.sendMessage(ChatColor.GRAY + "Aby rozpoczac gre uzyj komendy /minez spawn!");
		return true;
	}
	
	protected boolean reloadArg(CommandSender sender) {
		API.debug("protected boolean reloadArg(CommandSender)");
		
		if(!(sender.isOp())) {
			sender.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
			return true;
		} else {
			if(API.isDyrtCraftXPEnabled()) {
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
		API.debug("protected boolean sklepArg(CommandSender)");
		
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
	
	protected boolean spawnArg(CommandSender sender, String world) {
		API.debug("protected boolean spawnArg(CommandSender, String)");
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Nie mozesz wykonac tej komendy z poziomu konsoli!");
			return true;
		}
		Player player = (Player) sender;
		if(!(player.getLocation().getWorld().toString() == API.getSpawnWorldName())) {
			player.sendMessage(ChatColor.RED + "Nie mozesz rozpoczac nowej rozgrywki, jezeli obecnie jestes w grze!");
			return true;
		}
		API.spawnPlayer(player, world);
		return true;
	}
	
	protected boolean staffArg(CommandSender sender) {
		API.debug("protected boolean staffArg(CommandSender)");
		
		Object lista = pjaql.administracja_online.toArray();
		
		sender.sendMessage(ChatColor.GOLD + "Lista administracji online:");
		sender.sendMessage(ChatColor.GRAY + lista.toString());
		return true;
	}
	
}
