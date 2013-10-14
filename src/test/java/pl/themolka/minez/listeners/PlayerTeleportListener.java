package pl.themolka.minez.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import pl.themolka.minez.MineZ;
import pl.themolka.minez.Scoreboard;

public class PlayerTeleportListener implements Listener {

	MineZ plugin;
	
	public PlayerTeleportListener(MineZ mineZ) {
		plugin = mineZ;
	}
	
	// Najlepsze rozwiazanie jezeli chodzi o Scoreboard w linijce "Mapa"
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent e) {
		Scoreboard.setScoreboard(e.getPlayer());
	}
	
}
