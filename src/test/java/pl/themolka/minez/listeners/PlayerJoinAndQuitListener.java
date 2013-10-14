package pl.themolka.minez.listeners;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftPlugin;
import pl.themolka.minez.API;
import pl.themolka.minez.MineZ;
import pl.themolka.minez.Scoreboard;
import pl.themolka.minez.Sklep;

public class PlayerJoinAndQuitListener implements Listener {
	
	MineZ plugin;

	public PlayerJoinAndQuitListener(MineZ mineZ) {
		plugin = mineZ;
	}
	
	public ArrayList<String> administracja_online;
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) throws NullPointerException {
		Scoreboard.setScoreboard(e.getPlayer());
		if(e.getPlayer().isOp()) {
			if(API.isDyrtCraftXPEnabled()) {
				DyrtCraftPlugin.sendMsgToOp(e.getPlayer().getName() + " dolaczyl na serwer", 0);
			}
			try {
				administracja_online.add(e.getPlayer().getName());
			} catch(NullPointerException ex) {}
			return;
		}
		if(API.isDyrtCraftXPEnabled()) {
			if(e.getPlayer().hasPermission("minez.vip")) {
				e.getPlayer().getInventory().addItem(Sklep.getSklepItem());
			}
		}
		e.getPlayer().setGameMode(GameMode.ADVENTURE);
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) throws NullPointerException {
		Scoreboard.setScoreboard(e.getPlayer());
		if(e.getPlayer().isOp()) {
			if(API.isDyrtCraftXPEnabled()) {
				DyrtCraftPlugin.sendMsgToOp(e.getPlayer().getName() + " wyszedl z serwera", 0);
			}
			try {
				administracja_online.remove(e.getPlayer().getName());
			} catch(NullPointerException ex) {}
			return;
		}
		if(API.isDyrtCraftXPEnabled()) {
			e.getPlayer().getInventory().remove(Sklep.getSklepItem());
		}
		if(plugin.getServer().getOnlinePlayers().length == 0) {
			administracja_online.removeAll(administracja_online);
		}
	}
	
}
