package pl.themolka.minez.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import pl.DyrtCraft.DyrtCraftXP.api.XP;
import pl.themolka.minez.API;
import pl.themolka.minez.MineZ;

public class WodaListener implements Listener {

	MineZ plugin;
	
	public WodaListener(MineZ mineZ) {
		plugin = mineZ;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		try {
			if(e.getAction() == Action.RIGHT_CLICK_AIR);
			if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if(e.getItem().getTypeId() == 373) {
					API.debug("public void onPlayerInteract(PlayerInteractEvent)");
					int curLvl = e.getPlayer().getLevel();
					int ostLvl = curLvl+5;
					
					e.getPlayer().getInventory().remove(Material.getMaterial(373));
					e.getPlayer().setExp(5);
					e.getPlayer().setLevel(ostLvl);
					
					if(API.isPoradyEnabled(e.getPlayer())) {
						e.getPlayer().sendMessage(ChatColor.GOLD + "========== Porada ==========");
						e.getPlayer().sendMessage(ChatColor.GRAY + "Ufffff, nareszcie woda!");
						e.getPlayer().sendMessage(ChatColor.GRAY + "Wlasnie wypiles wode oraz otrzymales mane.");
						e.getPlayer().sendMessage(ChatColor.GRAY + "Napajanie sie jest jedna z najwaznejszych rzeczy w swiecie MineZ, gdyz bez niej mozna sie odwodnic.");
					}
					if(API.isDyrtCraftXPEnabled()) {
						XP.addXp(e.getPlayer(), 5, "Wypicie wody");
					}
				}
			}
		} catch(NullPointerException ex) {}
	}
	
}