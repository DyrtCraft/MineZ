package pl.themolka.minez.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import pl.DyrtCraft.DyrtCraftXP.api.XP;
import pl.themolka.minez.MineZ;

public class EntityDeath implements Listener {

	MineZ plugin;
	
	public EntityDeath(MineZ mineZ) {
		plugin = mineZ;
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		e.setDroppedExp(0);
		
		if(e.getEntity() instanceof Player) {
			if(e.getEntityType() == EntityType.PLAYER) {
				Player killer = e.getEntity().getKiller();
				Player entity = (Player) e.getEntity();
				
				if(e.getEntity().isDead()) {
					if(MineZ.isDyrtCraftXPEnabled()) {
						XP.addXp(killer, 5, "Zbito gracza " + entity.getName());
						XP.delXp(entity, 5, "Smierc przez gracza " + killer.getName());
						plugin.getServer().broadcastMessage(ChatColor.GRAY + entity.getName() + " (" + XP.getXp(entity) + ") zostal zabity przez " + killer.getName() + " (" + XP.getXp(killer) + ")");
					}
					plugin.getServer().broadcastMessage(ChatColor.GRAY + entity.getName() + " zostal zabity przez " + killer.getName());
				}
			}
			if(e.getEntityType() == EntityType.ZOMBIE) {
				Player damager = (Player) e.getEntity();
				
				if(e.getEntity().isDead()) {
					if(MineZ.isDyrtCraftXPEnabled()) {
						XP.addXp(damager, 3, "Zabito zombie");
					}
				}
			}
		}
		if(e.getEntity().getKiller() instanceof Zombie) {
			if(e.getEntityType() == EntityType.PLAYER) {
				//Zombie killer = (Zombie) e.getEntity().getKiller();
				Player entity = (Player) e.getEntity();
				
				if(e.getEntity().isDead()) {
					if(MineZ.isDyrtCraftXPEnabled()) {
						XP.delXp(entity, 3, "Smierc przez zombie");
						plugin.getServer().broadcastMessage(ChatColor.GRAY + entity.getName() + " (" + XP.getXp(entity) + ") zostal zabity przez zombie");
					}
					plugin.getServer().broadcastMessage(ChatColor.GRAY + entity.getName() + " zostal zabity przez zombie");
				}
			}
		}
	}
	
}
