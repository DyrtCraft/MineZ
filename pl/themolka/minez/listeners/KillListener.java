package pl.themolka.minez.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import pl.DyrtCraft.DyrtCraftXP.sql.Database;
import pl.themolka.minez.MineZ;

public class KillListener implements Listener {

	MineZ plugin;
	Database xp;
	
	public KillListener(MineZ mineZ) {
		plugin = mineZ;
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
			e.getEntityType();
			if(EntityType.PLAYER != null) {
				Player damager = (Player) e.getDamager();
				Player entity = (Player) e.getEntity();
				
				if(e.getEntity().isDead()) {
					if(MineZ.isDyrtCraftXPEnabled()) {
						xp.addXp(damager, 5, "Zbito gracza " + entity.getName());
						xp.delXp(entity, 5, "Smierc przez gracza " + damager.getName());
						plugin.getServer().broadcastMessage(ChatColor.GRAY + entity.getName() + " (" + xp.getXp(entity) + ") zostal zabity przez " + damager.getName() + " (" + xp.getXp(damager) + ")");
					}
					plugin.getServer().broadcastMessage(ChatColor.GRAY + entity.getName() + " zostal zabity przez " + damager.getName());
				}
			}
			if(EntityType.ZOMBIE != null) {
				Player damager = (Player) e.getDamager();
				
				if(e.getEntity().isDead()) {
					if(MineZ.isDyrtCraftXPEnabled()) {
						xp.addXp(damager, 3, "Zabito zombie");
					}
				}
			}
		}
		if(e.getDamager() instanceof Zombie) {
			e.getEntityType();
			if(EntityType.PLAYER != null) {
				Player entity = (Player) e.getEntity();
				
				if(e.getEntity().isDead()) {
					if(MineZ.isDyrtCraftXPEnabled()) {
						xp.delXp(entity, 3, "Smierc przez zombie");
						plugin.getServer().broadcastMessage(ChatColor.GRAY + entity.getName() + " (" + xp.getXp(entity) + ") zostal zabity przez zombie");
					}
					plugin.getServer().broadcastMessage(ChatColor.GRAY + entity.getName() + " zostal zabity przez zombie");
				}
			}
		}
	}
	
}
