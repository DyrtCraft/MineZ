package pl.themolka.minez;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignsManager implements Listener {

	MineZ plugin;
	
	String alfa = "Alfa";
	String beta = "Beta";
	String gamma = "Gamma";
	
	public SignsManager(MineZ mineZ) {
		plugin = mineZ;
	}
	
	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		if(e.getLine(0).equalsIgnoreCase("[MineZ]")) {
			if(!(e.getPlayer().isOp())) {
				e.getBlock().breakNaturally();
				e.setCancelled(true);
				e.getPlayer().sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
				return;
			} else {
				if(e.getLine(1).equalsIgnoreCase("Graj")) {
					e.setLine(0, ChatColor.DARK_GREEN + "" + ChatColor.UNDERLINE + "" + ChatColor.BOLD + "MineZ");
					e.setLine(1, ChatColor.AQUA + "Graj na mapie");
					e.setLine(2, ChatColor.DARK_RED + "" + ChatColor.BOLD + e.getLine(2));
					e.setLine(3, "");
					return;
				}
				else if(e.getLine(1).equalsIgnoreCase("Sklep")) {
					e.setLine(0, ChatColor.DARK_GREEN + "" + ChatColor.UNDERLINE + "" + ChatColor.BOLD + "MineZ");
					e.setLine(1, ChatColor.AQUA + "Sklep");
					e.setLine(2, "");
					e.setLine(3, "");
					return;
				} else {
					e.getBlock().breakNaturally();
					e.setCancelled(true);
					e.getPlayer().sendMessage(ChatColor.RED + "Bledny argument!");
					return;
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		try {
			API.debug("public void onPlayerInteract(PlayerInteractEvent)");
			
			if(!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
			if(e.getClickedBlock().getState() instanceof Sign) {
				Sign s = (Sign) e.getClickedBlock().getState();
				if(s.getLine(0).equalsIgnoreCase(ChatColor.DARK_GREEN + "" + ChatColor.UNDERLINE + "" + ChatColor.BOLD + "MineZ")) {
					if(s.getLine(1).equalsIgnoreCase(ChatColor.AQUA + "Sklep")) {
						Sklep.showSklep(e.getPlayer());
						return;
					}
					if(s.getLine(1).equalsIgnoreCase(ChatColor.AQUA + "Graj na mapie")) {
						if(s.getLine(2).equalsIgnoreCase(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Alfa")) {
							API.spawnPlayer(e.getPlayer(), alfa);
							return;
						} else {
							if(s.getLine(2).equalsIgnoreCase(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Alfa")) {
								API.spawnPlayer(e.getPlayer(), beta);
								return;
							} else {
								if(s.getLine(2).equalsIgnoreCase(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Alfa")) {
									API.spawnPlayer(e.getPlayer(), gamma);
									return;
								}
							}
						}
					}
				}
			}
		} catch(NullPointerException ex) {}
	}
	
}
