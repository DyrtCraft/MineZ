package pl.themolka.minez.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import pl.themolka.minez.MineZ;

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
			e.getAction();
			if((Action.RIGHT_CLICK_AIR != null) || (Action.RIGHT_CLICK_BLOCK != null)) {
				e.getItem().getType();
				if(Material.PAPER != null) {
					int foodLvl = e.getPlayer().getFoodLevel();
					
					int foodLvl2 = foodLvl+10;
					e.getPlayer().setFoodLevel(foodLvl2);
					
					e.getPlayer().setHealth(20.0);
					
					e.getPlayer().getInventory().remove(Material.PAPER);
				}
			}
		} catch(NullPointerException ex) {}
	}
	
}
