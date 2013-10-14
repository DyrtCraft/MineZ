package pl.themolka.minez.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import pl.DyrtCraft.DyrtCraftXP.api.XP;
import pl.themolka.minez.API;
import pl.themolka.minez.MineZ;
import pl.themolka.minez.Sklep;

/**
 * @author TheMolkaPL
 * @since Development Build 005
 * @see MineZ#sendStarterKit(org.bukkit.entity.Player)
 * @see MineZ#sendStarterVIPKit(org.bukkit.entity.Player)
 */
public class BandazListener implements Listener {

	MineZ plugin;
	
	public BandazListener(MineZ mineZ) {
		plugin = mineZ;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		try {
			if((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
				if(e.getItem().getType() == Material.PAPER) {
					// Usunecie bandazu (papieru)
					e.getPlayer().getInventory().removeItem(new ItemStack[] { new ItemStack(339, 1) });
					
					// Obliczenie food lvl
					int foodLvl = e.getPlayer().getFoodLevel();
					int foodLvl2 = foodLvl+10;
					
					// Dodanie HP i food lvl
					e.getPlayer().setFoodLevel(foodLvl2);
					e.getPlayer().setHealth(20.0);
					
					// Porada
					if(API.isPoradyEnabled(e.getPlayer())) {
						e.getPlayer().sendMessage(ChatColor.GOLD + "========== Porada ==========");
						e.getPlayer().sendMessage(ChatColor.GRAY + "Czesc, wlasnie uleczyles sie bandazem!");
						e.getPlayer().sendMessage(ChatColor.GRAY + "Bandaz to przydatny item podczas ostrej walki, gdy potrzebujesz szybkiego wyleczenia.");
						e.getPlayer().sendMessage(ChatColor.GRAY + "Niestety jest to przedmiot dosc ciezki do zdobycia :(");
					}
					XP.addXp(e.getPlayer(), 5, "Uleczyles sie bandazem");
				}
			}
		} catch(NullPointerException ex) {}
	}
	
}
