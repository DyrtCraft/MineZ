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
			} else {
				if(e.getLine(1).equalsIgnoreCase("Graj")) {
					e.setLine(0, ChatColor.DARK_GREEN + "" + ChatColor.UNDERLINE + "" + ChatColor.BOLD + "MineZ");
					e.setLine(1, ChatColor.AQUA + "Graj na mapie:");
					e.setLine(2, ChatColor.DARK_RED + "" + ChatColor.BOLD + e.getLine(2));
					e.setLine(3, "");
				}
				else if(e.getLine(1).equalsIgnoreCase("Sklep")) {
					e.setLine(0, ChatColor.DARK_GREEN + "" + ChatColor.UNDERLINE + "" + ChatColor.BOLD + "MineZ");
					e.setLine(1, ChatColor.AQUA + "Sklep");
					e.setLine(2, "");
					e.setLine(3, "");
				} else {
					e.getBlock().breakNaturally();
					e.setCancelled(true);
					e.getPlayer().sendMessage(ChatColor.RED + "Bledny argument!");
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		try {
			if(!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
			if(e.getClickedBlock().getState() instanceof Sign) {
				Sign s = (Sign) e.getClickedBlock().getState();
				if(s.getLine(0).equalsIgnoreCase(ChatColor.DARK_GREEN + "" + ChatColor.UNDERLINE + "" + ChatColor.BOLD + "MineZ")) {
					if(s.getLine(1).equalsIgnoreCase(ChatColor.AQUA + "Graj na mapie:")) {
						MineZ.spawnPlayer(e.getPlayer(), s.getLine(2));
						System.out.println("test");
					}
					else if(s.getLine(1).equalsIgnoreCase(ChatColor.AQUA + "Sklep")) {
						Sklep.showSklep(e.getPlayer());
						System.out.println("test");
					}
				}
			}
		} catch(NullPointerException ex) {}
	}
	
}
