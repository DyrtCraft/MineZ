package pl.themolka.minez.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;

import pl.themolka.minez.MineZ;

public class ExpAndLevelChangeListener implements Listener {

	MineZ plugin;
	
	public ExpAndLevelChangeListener(MineZ mineZ) {
		plugin = mineZ;
	}
	
	@EventHandler
	public void onPlayerExpChange(PlayerExpChangeEvent e) {
		int exp = e.getAmount();
		
		if(e.getPlayer().getLevel() == 0) {
			while(exp < 8) {
				e.getPlayer().damage(0.5);
				e.setAmount(exp--);
			}
		}
	}
	
	@EventHandler
	public void onPlayerLevelChange(PlayerLevelChangeEvent e) {
		if(e.getNewLevel() > 20) {
			e.getPlayer().setLevel(20);
			e.getPlayer().sendMessage(ChatColor.GRAY + "Otrzymano maksymalny poziom paska!");
		}
	}
	
}
