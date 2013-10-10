package pl.themolka.minez.listeners;

import java.util.List;

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
		String swiat = e.getBlock().getLocation().getWorld().toString();
		List<String> swiatMineZ = API.getInstance().getConfig().getStringList("swiaty-minez");
		String swiatSpawn = API.getInstance().getConfig().getString("swiat-spawn");
		boolean protectSpawn = API.getInstance().getConfig().getBoolean("chron-spawn");
		
		if(swiatMineZ.contains(swiat)) {
			if(!(e.getPlayer().getGameMode() == GameMode.CREATIVE && e.getPlayer().isOp())) {
				e.setCancelled(true);
			}
		}
		if(protectSpawn == true && swiatSpawn == swiat) {
			if(!(e.getPlayer().getGameMode() == GameMode.CREATIVE && e.getPlayer().isOp())) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		API.debug("public void onBlockPlace(BlockPlaceEvent)");
		String swiat = e.getBlock().getLocation().getWorld().toString();
		List<String> swiatMineZ = API.getInstance().getConfig().getStringList("swiaty-minez");
		String swiatSpawn = API.getInstance().getConfig().getString("swiat-spawn");
		boolean protectSpawn = API.getInstance().getConfig().getBoolean("chron-spawn");
		
		if(swiatMineZ.contains(swiat)) {
			if(!(e.getPlayer().getGameMode() == GameMode.CREATIVE && e.getPlayer().isOp())) {
				e.setCancelled(true);
			}
		}
		if(protectSpawn == true && swiatSpawn == swiat) {
			if(!(e.getPlayer().getGameMode() == GameMode.CREATIVE && e.getPlayer().isOp())) {
				e.setCancelled(true);
			}
		}
	}
	
}
