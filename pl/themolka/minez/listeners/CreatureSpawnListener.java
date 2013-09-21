package pl.themolka.minez.listeners;

import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import pl.themolka.minez.MineZ;

public class CreatureSpawnListener implements Listener {

	public CreatureSpawnListener(MineZ mineZ) {}
	
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent e) {
		if(!(e.getEntity() instanceof Zombie)) {
			e.setCancelled(true);
		}
	}
	
}
