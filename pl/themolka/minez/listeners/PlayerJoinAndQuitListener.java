package pl.themolka.minez.listeners;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftPlugin;
import pl.themolka.minez.MineZ;
import pl.themolka.minez.Sklep;

public class PlayerJoinAndQuitListener implements Listener {
	
	MineZ plugin;

	public PlayerJoinAndQuitListener(MineZ mineZ) {
		plugin = mineZ;
	}
	
	public ArrayList<String> administracja_online;
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if(e.getPlayer().isOp()) {
			if(MineZ.isDyrtCraftXPEnabled()) {
				DyrtCraftPlugin.sendMsgToOp(e.getPlayer().getName() + " dolaczyl na serwer", 0);
				return;
			}
			administracja_online.add(e.getPlayer().getName());
			return;
		}
		if(MineZ.isDyrtCraftXPEnabled()) {
			if(e.getPlayer().hasPermission("minez.vip")) {
				e.getPlayer().getInventory().addItem(Sklep.getSklepItem());
				return;
			}
			return;
		}
		e.getPlayer().setGameMode(GameMode.ADVENTURE);
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		if(e.getPlayer().isOp()) {
			if(MineZ.isDyrtCraftXPEnabled()) {
				DyrtCraftPlugin.sendMsgToOp(e.getPlayer().getName() + " wyszedl z serwera", 0);
				return;
			}
			administracja_online.remove(e.getPlayer().getName());
			return;
		}
		if(MineZ.isDyrtCraftXPEnabled()) {
			e.getPlayer().getInventory().remove(Sklep.getSklepItem());
			return;
		}
		if(plugin.getServer().getOnlinePlayers().length == 0) {
			administracja_online.removeAll(administracja_online);
		}
	}
	
}
