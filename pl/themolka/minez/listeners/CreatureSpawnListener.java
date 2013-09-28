package pl.themolka.minez.listeners;

import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import pl.themolka.minez.MineZ;

public class CreatureSpawnListener implements Listener {

	public CreatureSpawnListener(MineZ mineZ) {}
	
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent e) {
		if(!(e.getEntity() instanceof Zombie)) {
			e.setCancelled(true);
		} else {
			Zombie zombie = (Zombie) e.getEntity();
			zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
			zombie.setBaby(false);
			zombie.setCanPickupItems(true);
		}
	}
	
}
