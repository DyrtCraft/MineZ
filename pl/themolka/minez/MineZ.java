package pl.themolka.minez;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class MineZ extends JavaPlugin {

	public void onEnable() {
		if(!(getServer().getPluginManager().isPluginEnabled("DyrtCraftXP"))) {
			getLogger().warning("Do pelnego dzialania tego pluginu potrzeby jest plugin DyrtCraftXP!");
		}
		getServer().getPluginManager().registerEvents(new pl.themolka.minez.listeners.CreatureSpawnListener(this), this);
		getServer().getPluginManager().registerEvents(new pl.themolka.minez.listeners.Cuboid(this), this);
		getServer().getPluginManager().registerEvents(new pl.themolka.minez.listeners.PlayerJoinAndQuitListener(this), this);
		
		getCommand("minez").setExecutor(new pl.themolka.minez.MineZCommand(this));
		
		saveDefaultConfig();
	}
	
	public static boolean isDyrtCraftXPEnabled() {
		if(Bukkit.getPluginManager().isPluginEnabled("DyrtCraftXP")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void sendStarterKit(Player player) {
		// Drewniany miecz
		ItemStack miecz = new ItemStack(Material.WOOD_SWORD);
		ItemMeta mieczMeta = miecz.getItemMeta();
		mieczMeta.setDisplayName(ChatColor.GOLD + "Drewniany miecz");
		miecz.setItemMeta(mieczMeta);
		
		// Butelka wody
		/*
		ItemStack butelka = new ItemStack(Material. Butelka wody );
		ItemMeta butelkaMeta = butelka.getItemMeta();
		butelkaMeta.setDisplayName("");
		butelka.setItemMeta(butelkaMeta);
		*/
		
		// Bandaz (papier)
		ItemStack bandaz = new ItemStack(Material.PAPER);
		ItemMeta bandazMeta = bandaz.getItemMeta();
		bandazMeta.setDisplayName(ChatColor.GOLD + "Bandaz");
		bandaz.setItemMeta(bandazMeta);
		
		player.getInventory().addItem(miecz);
		//player.getInventory().addItem(butelka);
		player.getInventory().addItem(bandaz);
	}
	
}
