package pl.themolka.minez;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;

import pl.DyrtCraft.DyrtCraftXP.api.XP;

public class Scoreboard {

	MineZ plugin;
	
	public Scoreboard(MineZ mineZ) {
		plugin = mineZ;
	}
	
	/**
	 * @author TheMolkaPL
	 * @since Developemnt Build 014
	 * @param player
	 */
	public static void setScoreboard(Player player) {
		API.debug("public static void setScoreboard(Player)");
		if(API.isDyrtCraftXPEnabled()) {
			try {
				// Jezeli plugin DyrtCraftXP jest wlaczony
				setLocalScoreboard(player);
			} catch(Exception ex) {
				setLocalScoreboard(player);
			}
		} else {
			// Jezeli plugin DyrtCraftXP jest wylaczony
			setLocalScoreboard(player);
		}
	}
	
	public static void setLocalScoreboardXP(Player player) throws NoSuchMethodException {
		player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		
		ScoreboardManager sm = Bukkit.getScoreboardManager();
		org.bukkit.scoreboard.Scoreboard tablicaxp = sm.getNewScoreboard();
		
		Objective obj = tablicaxp.registerNewObjective("tablicaxp","dummy");
		obj.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "MineZ");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		int ilosc_xp = XP.getXp(player.toString());
		Score xp = obj.getScore(Bukkit.getOfflinePlayer("§6Ilosc XP"));
		xp.setScore(ilosc_xp);
		
		// Mapa
		String world_name = player.getWorld().getName();
		Score mapa = obj.getScore(Bukkit.getOfflinePlayer("§6Mapa: " + ChatColor.RED + world_name));
		mapa.setScore(-1);
		
		/*
		 *  TODO: Wiecej info w scoreboard
		 */
		
		player.setScoreboard(tablicaxp);
	}
	
	public static void setLocalScoreboard(Player player) {
		player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		
		ScoreboardManager sm = Bukkit.getScoreboardManager();
		org.bukkit.scoreboard.Scoreboard tablica = sm.getNewScoreboard();
		
		Objective obj = tablica.registerNewObjective("tablica","dummy");
		obj.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "MineZ");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		// Mapa
		String world_name = player.getWorld().getName();
		Score mapa = obj.getScore(Bukkit.getOfflinePlayer("§6Mapa: " + ChatColor.RED + world_name));
		mapa.setScore(-1);
		
		/*
		 *  TODO: Wiecej info w scoreboard
		 */
		
		player.setScoreboard(tablica);
	}
	
}
