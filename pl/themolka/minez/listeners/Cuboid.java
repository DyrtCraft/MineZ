package pl.themolka.minez.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import pl.themolka.minez.MineZ;

public class Cuboid implements Listener {

	public Cuboid(MineZ mineZ) {}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if(!(e.getPlayer().isOp())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if(!(e.getPlayer().isOp())) {
			e.setCancelled(true);
		}
	}
	
}
