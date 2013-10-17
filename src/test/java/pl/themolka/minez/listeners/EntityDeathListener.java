package pl.themolka.minez.listeners;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import pl.DyrtCraft.DyrtCraftXP.api.XP;

import pl.themolka.minez.API;
import pl.themolka.minez.MineZ;

public class EntityDeathListener implements Listener {

	MineZ plugin;
	Zombie zombie;
	
	public EntityDeathListener(MineZ mineZ) {
		plugin = mineZ;
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		Entity dedacz = e.getDamager();
		Entity zabijajacy = e.getEntity();
		
		if(API.isDyrtCraftXPEnabled()) {
			// Zombie (wyg) vs Player (przeg)
			if(zabijajacy instanceof Zombie && dedacz instanceof Player && dedacz !=null) {
				if(dedacz.isDead()) {
					Player dedaczPlayer = (Player) dedacz;
					String dedaczName = dedaczPlayer.getName();
					EntityEquipment ee = zombie.getEquipment();
					
					ee.setHelmet(API.head(dedaczName)); // Ustawianie glowy gracza dla Zombie
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
		
		if(API.isDyrtCraftXPEnabled()) {
			// Player (wyg) vs Zombie (przeg)
			if(dedacz instanceof Zombie && zabijajacy !=null) {
				Player zabijajcyPlayer = (Player) zabijajacy;
				
				XP.addXp(zabijajcyPlayer, 5, "Zabito Zombie");
				return;
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
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		Player dedacz = (Player) e.getEntity();
		EntityDamageEvent damageEvent = dedacz.getLastDamageCause();
		Entity damager = ((EntityDamageByEntityEvent)damageEvent).getDamager();
		short idv2 = 3;
		
		if(!(API.isDyrtCraftXPEnabled())) {
			// TODO
			return;
		}
		
		if(dedacz instanceof Player && damager instanceof Zombie) {
			// Zombie (zabijajacy)
			Zombie zombie = (Zombie) dedacz.getWorld().spawnEntity(dedacz.getLocation(), EntityType.ZOMBIE);
			ItemStack head = new ItemStack(397, 1, idv2);
			SkullMeta meta = (SkullMeta) head.getItemMeta();
			meta.setOwner(dedacz.getName());
			head.setItemMeta(meta);
			EntityEquipment equipment = zombie.getEquipment();
			equipment.setHelmet(head);
			zombie.setBaby(false); // Zombie od zabicia gracza nie jest dzieckiem
			zombie.setCanPickupItems(true); // Zombie moze podnosic itemy (np gracza)
			zombie.setCustomName(dedacz.getName()); // Ustawianie nazwy nad glowa nicku gracza
			zombie.setCustomNameVisible(true); // Widocznosc nicku nad glowa Zombie
			
			XP.delXp(dedacz, 5, "Smierc przez Zombie");
			
			e.setDeathMessage(ChatColor.WHITE + dedacz.getName() + ChatColor.WHITE + " zostal zamordowany przez Zombie!");
			return;
		}
		else if(dedacz instanceof Player && damager instanceof Player) {
			Player damagerPlayer = (Player) damager;
			
			XP.addXp(damagerPlayer, 7, "Zabito gracza " + dedacz.getName());
			damagerPlayer.sendMessage(XP.showXp(dedacz.getName()));
			XP.delXp(dedacz, 7, "Smierc przez gracza " + damagerPlayer.getName());
			dedacz.sendMessage(XP.showXp(dedacz.getName()));
			dedacz.setCanPickupItems(false);
			dedacz.setExp(0);
			dedacz.setLevel(0);
			dedacz.setGameMode(GameMode.ADVENTURE);
			
			e.setDeathMessage(ChatColor.WHITE + dedacz.getName() + ChatColor.WHITE + " zostal zabity przez gracza " + ChatColor.WHITE + damagerPlayer.getKiller().getName() + ChatColor.WHITE + " przy uzyciu " + damagerPlayer.getItemInHand().getType().toString().replaceAll("_", " "));
			return;
		}
	}
	
}
