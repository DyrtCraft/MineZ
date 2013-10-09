package pl.themolka.minez.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.EntityEquipment;

import pl.DyrtCraft.DyrtCraftXP.api.XP;
import pl.themolka.minez.MineZ;

public class EntityDeathListener implements Listener {

	MineZ plugin;
	Zombie zombie;
	
	public EntityDeathListener(MineZ mineZ) {
		plugin = mineZ;
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		Entity dedacz = e.getEntity();
		Entity zabijajacy = e.getDamager();
		
		if(MineZ.isDyrtCraftXPEnabled()) {
			// Zombie (wyg) vs Player (przeg)
			if(zabijajacy instanceof Zombie && dedacz instanceof Player && dedacz !=null) {
				if(dedacz.isDead()) {
					Player dedaczPlayer = (Player) dedacz;
					String dedaczName = dedaczPlayer.getName();
					EntityEquipment ee = zombie.getEquipment();
					
					ee.setHelmet(MineZ.head(dedaczName)); // Ustawianie glowy gracza dla Zombie
				}
			}
		} else {
			// Jezeli DyrtCraftXP jest wylaczone
		}
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		e.setDroppedExp(0); // Exp = 0
		
		e.getDrops().remove(Material.EMERALD); // Nie dropienie itemu od sklepu
		
		Entity dedacz = e.getEntity();
		Entity zabijajacy = e.getEntity().getKiller();
		
		if(MineZ.isDyrtCraftXPEnabled()) {
			// Player (wyg) vs Player (przeg) & Player (przeg) vs Player (wyg)
			if(dedacz instanceof Player && zabijajacy != null) {
				Player dedaczPlayer = (Player) dedacz;
				Player zabijajacyPlayer = (Player) zabijajacy;
				
				XP.addXp(zabijajacyPlayer, 7, "Zabito gracza " + dedaczPlayer.getName());
				XP.delXp(dedaczPlayer, 7, "Smierc przez gracza " + zabijajacyPlayer.getName());
				return;
			}
			// Player (wyg) vs Zombie (przeg)
			if(dedacz instanceof Zombie && zabijajacy !=null) {
				Player zabijajcyPlayer = (Player) zabijajacy;
				
				XP.addXp(zabijajcyPlayer, 5, "Zabito Zombie");
				return;
			}
			// Zombie (wyg) vs Player (przeg)
			if(zabijajacy instanceof Zombie && dedacz instanceof Player && dedacz !=null) {
				Player dedaczPlayer = (Player) dedacz;
				String dedaczName = dedaczPlayer.getName();
				
				XP.delXp(dedaczPlayer, 5, "Smierc przez Zombie");
				
				zombie.setBaby(false); // Zombie od zabicia gracza nie jest dzieckiem
				zombie.setCanPickupItems(true); // Zombie moze podnosic itemy (np gracza)
				zombie.setCustomName(dedaczName); // Ustawianie nazwy nad glowa nicku gracza
				zombie.setCustomNameVisible(true); // Widocznosc nicku nad glowa Zombie
			}
		} else {
			// Jezeli DyrtCraftXP jest wylaczone
			if(zabijajacy instanceof Zombie && dedacz instanceof Player && dedacz !=null) {
				Player dedaczPlayer = (Player) dedacz;
				String dedaczName = dedaczPlayer.getName();
				
				zombie.setBaby(false); // Zombie od zabicia gracza nie jest dzieckiem
				zombie.setCanPickupItems(true); // Zombie moze podnosic itemy (np gracza)
				zombie.setCustomName(dedaczName); // Ustawianie nazwy nad glowa nicku gracza
				zombie.setCustomNameVisible(true); // Widocznosc nicku nad glowa Zombie
			}
		}
		
	}
	
}
