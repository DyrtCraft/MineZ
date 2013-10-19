package pl.themolka.minez.listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import pl.themolka.minez.API;
import pl.themolka.minez.MineZ;

public class Cuboid implements Listener {

	public Cuboid(MineZ mineZ) {}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		API.debug("public void onBlockBreak(BlockBreakEvent)");
		if(!(e.getPlayer().getGameMode() == GameMode.CREATIVE && e.getPlayer().isOp())) {
			e.setCancelled(true);
			return;
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		API.debug("public void onBlockPlace(BlockPlaceEvent)");
		if(!(e.getPlayer().getGameMode() == GameMode.CREATIVE && e.getPlayer().isOp())) {
			e.setCancelled(true);
			return;
		}
	}
	
}
