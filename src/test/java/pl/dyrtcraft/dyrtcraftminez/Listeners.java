package pl.dyrtcraft.dyrtcraftminez;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import pl.themolka.minez.MineZ;

public class Listeners implements Listener {

	MineZ plugin;
	
	public Listeners(MineZ mineZ) {
		plugin = mineZ;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
	}
	
}
