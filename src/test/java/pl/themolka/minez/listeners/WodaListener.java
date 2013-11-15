package pl.themolka.minez.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;

import pl.DyrtCraft.DyrtCraftXP.api.XP;

import pl.themolka.minez.API;
import pl.themolka.minez.MineZ;

public class WodaListener implements Listener {

	MineZ plugin;
	BukkitTask task = null;
	
	public WodaListener(MineZ mineZ) {
		plugin = mineZ;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		try {
			if(e.getItem().getTypeId() == 373) {
				API.debug("public void onPlayerInteract(PlayerInteractEvent)");
				
				Player player = (Player) e.getPlayer();
				
				int curLvl = player.getLevel();
				int ostLvl = curLvl+5;
				
				ItemStack woda = new ItemStack(373, 1);
				woda.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Woda");
				
				ItemStack butelka = new ItemStack(374, 1);
				ItemMeta butelkaMeta = butelka.getItemMeta();
				butelkaMeta.setDisplayName(ChatColor.GOLD + "Woda");
				butelka.setItemMeta(butelkaMeta);
				
				player.getInventory().removeItem(new ItemStack[] { woda });
				player.getInventory().addItem(new ItemStack[] { butelka });
				
				float exp = 1;
				player.setExp(exp);
				player.setLevel(ostLvl);
				
				if(API.isPoradyEnabled(player)) {
					player.sendMessage(ChatColor.GOLD + "========== Porada ==========");
					player.sendMessage(ChatColor.GRAY + "Ufffff, nareszcie woda!");
					player.sendMessage(ChatColor.GRAY + "Wlasnie wypiles wode oraz otrzymales mane.");
					player.sendMessage(ChatColor.GRAY + "Napajanie sie jest jedna z najwaznejszych rzeczy w swiecie MineZ, gdyz bez niej mozna sie odwodnic.");
				}
				if(API.isDyrtCraftXPEnabled()) {
					XP.addXp(player, 5, "Wypicie wody");
					return;
				}
			}
		} catch(NullPointerException ex) {}
	}
	
}
