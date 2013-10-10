package pl.themolka.minez;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import pl.DyrtCraft.DyrtCraftXP.api.XP;

/**
 * @author TheMolkaPL
 * @since Development Build 009
 */
public class Sklep implements Listener {

	static Inventory inv;
	
	ItemStack drewniany_miecz, kamienny_miecz, butelka, bandaz, zegarek, melon, chleb;
	
	MineZ plugin;
	
	public Sklep(MineZ mineZ) {
		plugin = mineZ;
		
		inv = plugin.getServer().createInventory(null, 45, ChatColor.RED + "Sklep " + ChatColor.GOLD + "MineZ");
		
		drewniany_miecz = cretateItem(Material.WOOD_SWORD, "Drewniany Miecz", "Najlepszy na poczatek", 10);
		kamienny_miecz = cretateItem(Material.STONE_SWORD, "Kamienny miecz", "Podstawowa bron", 20);
		butelka = cretateItem(Material.getMaterial(373), "Woda", "Napojenie", 60);
		bandaz = cretateItem(Material.PAPER, "Bandaz", "Szybkie uleczanie", 70);
		zegarek = cretateItem(Material.getMaterial(347), "Zegarek", "Sprawdz czas!", 50);
		melon = cretateItem(Material.MELON, "Melon", "Mala przegryzka", 40);
		chleb = cretateItem(Material.BREAD, "Chleb", "Jedzenie", 55);
		
		inv.setItem(0, drewniany_miecz);
		inv.setItem(1, kamienny_miecz);
		inv.setItem(2, butelka);
		inv.setItem(3, bandaz);
		inv.setItem(4, zegarek);
		inv.setItem(5, melon);
		inv.setItem(6, chleb);
	}
	
	/**
	 * @author TheMolkaPL
	 * @since Development Build 009
	 * @param player Gracz dla ktorego ma zostac wyswietlony sklep
	 */
	public static void showSklep(Player player) {
		API.debug("public static void showSklep(Player)");
		if(API.isDyrtCraftXPEnabled()) {
			player.openInventory(inv);
		} else {
			player.sendMessage(ChatColor.RED + "Do poprawnego dzialania sklepu wymagany jest plugin DyrtCraftXP!");
		}
	}
	
	/**
	 * @author TheMolkaPL
	 * @since Development Build 009
	 * @return Emerald - sklep
	 */
	public static ItemStack getSklepItem() {
		API.debug("public static ItemStack getSklepItem()");
		
		ItemStack item = new ItemStack(Material.EMERALD);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(ChatColor.GOLD + "Sklep MineZ");
		itemMeta.setLore(Arrays.asList(ChatColor.DARK_PURPLE + "Sklep z itemami"));
		item.setItemMeta(itemMeta);
		return item;
	}
	
	protected ItemStack cretateItem(Material material, String name, String description, int cost) {
		ItemStack item = new ItemStack(material);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setLore(Arrays.asList(ChatColor.DARK_PURPLE + description, ChatColor.GOLD + "Koszt: " + cost + "XP"));
		itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + name);
		item.setItemMeta(itemMeta);
		return item;
	}
	
	protected void buyItem(Player player, Material material, String name, int cost) {
		API.debug("protected void buyItem(Player, Material, String, int)");
		if(API.isDyrtCraftXPEnabled()) {
			XP.delXp(player, cost, "Zakup w sklepie itemu: " + name);
			
			ItemStack item = new ItemStack(material);
			ItemMeta itemMeta = item.getItemMeta();
			itemMeta.setDisplayName(ChatColor.GOLD + "" + name);
			item.setItemMeta(itemMeta);
			
			player.getInventory().addItem(item);
		} else {
			player.sendMessage(ChatColor.RED + "Do poprawnego dzialania sklepu wymagany jest plugin DyrtCraftXP!");
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		try {
			if(e.getItem().getItemMeta().getDisplayName().contains("Sklep")) {
				if((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
					if(e.getPlayer().hasPermission("minez.vip")) {
						Sklep.showSklep(e.getPlayer());
					}
				}
			}
		} catch(NullPointerException ex) {}
	}
	
	@EventHandler
	public void onPlayerClickInventory(InventoryClickEvent e) {
		try {
			Player player = (Player) e.getWhoClicked();
			
			if(!e.getInventory().getName().equalsIgnoreCase(inv.getName())) return;
			
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Drewniany Miecz")) {
				e.setCancelled(true);
				buyItem(player, Material.WOOD_SWORD, "Drewniany Miecz", 10);
				player.closeInventory();
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Kamienny miecz")) {
				e.setCancelled(true);
				buyItem(player, Material.STONE_SWORD, "Kamienny miecz", 20);
				player.closeInventory();
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Woda")) {
				e.setCancelled(true);
				buyItem(player, Material.getMaterial(373), "Woda", 60);
				player.closeInventory();
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Bandaz")) {
				e.setCancelled(true);
				buyItem(player, Material.PAPER, "Bandaz", 70);
				player.closeInventory();
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Zegarek")) {
				e.setCancelled(true);
				buyItem(player, Material.getMaterial(347), "Zegarek", 50);
				player.closeInventory();
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Melon")) {
				e.setCancelled(true);
				buyItem(player, Material.MELON, "Melon", 40);
				player.closeInventory();
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Chleb")) {
				e.setCancelled(true);
				buyItem(player, Material.BREAD, "Chleb", 55);
				player.closeInventory();
			}

		} catch(NullPointerException ex) {}
	}
	
}
