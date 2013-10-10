package pl.themolka.minez.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scoreboard.DisplaySlot;

import pl.themolka.minez.API;
import pl.themolka.minez.MineZ;
import pl.themolka.minez.Scoreboard;

/**
 * @author TheMolkaPL
 * @since Development Build 019
 */
public class PlayerMoveListener implements Listener {

	MineZ plugin;
	
	public PlayerMoveListener(MineZ mineZ) {
		plugin = mineZ;
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		API.debug("public void onPlayerMove(PlayerMoveEvent)");
		e.getPlayer().getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		API.getScoreboard();
		Scoreboard.setScoreboard(e.getPlayer());
	}
	
}
