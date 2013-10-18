package pl.themolka.minez.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
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
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		try {
			if(e.getAction() == Action.RIGHT_CLICK_AIR);
			if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if(e.getItem().getTypeId() == 373) {
					Player player = (Player) e.getPlayer();
					API.debug("public void onPlayerInteract(PlayerInteractEvent)");
					run(player);
					//task = plugin.getServer().getScheduler().runTaskTimer(plugin, run(player), 0, 20l);
				}
			}
		} catch(NullPointerException ex) {}
	}

	private Runnable run(Player player) {
		API.debug("public Runnable run(Player)");
		int curLvl = player.getLevel();
		int ostLvl = curLvl+5;
		
		ItemStack woda = new ItemStack(339, 1);
		player.getInventory().removeItem(new ItemStack[] { woda });
		player.setExp(5);
		player.setLevel(ostLvl);
		
		if(API.isPoradyEnabled(player)) {
			player.sendMessage(ChatColor.GOLD + "========== Porada ==========");
			player.sendMessage(ChatColor.GRAY + "Ufffff, nareszcie woda!");
			player.sendMessage(ChatColor.GRAY + "Wlasnie wypiles wode oraz otrzymales mane.");
			player.sendMessage(ChatColor.GRAY + "Napajanie sie jest jedna z najwaznejszych rzeczy w swiecie MineZ, gdyz bez niej mozna sie odwodnic.");
		}
		if(API.isDyrtCraftXPEnabled()) {
			XP.addXp(player, 5, "Wypicie wody");
		}
		return null;
	}
	
}
