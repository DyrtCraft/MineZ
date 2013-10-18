package pl.themolka.minez;

import java.util.Arrays;

import org.bukkit.Bukkit;
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
	
	ItemStack drewniany_miecz, kamienny_miecz, butelka, bandaz, zegarek, melon, chleb, skoraHelmet, skoraChestplate, skoraLeggings, skoraBoots, chainHelmet, chainChestplate, chainLeggings, chainBoots;
	
	MineZ plugin;
	
	public Sklep(MineZ mineZ) {
		plugin = mineZ;
		
		inv = Bukkit.createInventory(null, 36, ChatColor.RED + "" + ChatColor.BOLD + "Sklep MineZ");
		
		drewniany_miecz = cretateItem(Material.WOOD_SWORD, "Drewniany Miecz", "Najlepszy na poczatek", 10);
		kamienny_miecz = cretateItem(Material.STONE_SWORD, "Kamienny miecz", "Podstawowa bron", 20);
		butelka = cretateItem(Material.getMaterial(373), "Woda", "Napojenie", 60);
		bandaz = cretateItem(Material.PAPER, "Bandaz", "Szybkie uleczanie", 70);
		zegarek = cretateItem(Material.getMaterial(347), "Zegarek", "Sprawdz czas!", 50);
		melon = cretateItem(Material.MELON, "Melon", "Mala przegryzka", 40);
		chleb = cretateItem(Material.BREAD, "Chleb", "Jedzenie", 55);
		
		skoraHelmet = cretateItem(Material.LEATHER_HELMET, "Skorzany helm", "Zbroja", 15);
		skoraChestplate = cretateItem(Material.LEATHER_CHESTPLATE, "Skorzany napiersnik", "Zbroja", 15);
		skoraLeggings = cretateItem(Material.LEATHER_LEGGINGS, "Skorzane spodnie", "Zbroja", 15);
		skoraBoots = cretateItem(Material.LEATHER_BOOTS, "Skorzane buty", "Zbroja", 15);
		
		chainHelmet = cretateItem(Material.CHAINMAIL_HELMET, "Chainowy helm", "Zbroja", 30);
		chainChestplate = cretateItem(Material.CHAINMAIL_CHESTPLATE, "Chainowy napiersnik", "Zbroja", 30);
		chainLeggings = cretateItem(Material.CHAINMAIL_LEGGINGS, "Chainowe spodnie", "Zbroja", 30);
		chainBoots = cretateItem(Material.CHAINMAIL_BOOTS, "Chainowe buty", "Zbroja", 30);
		
		inv.setItem(0, drewniany_miecz);
		inv.setItem(1, kamienny_miecz);
		inv.setItem(2, butelka);
		inv.setItem(3, bandaz);
		inv.setItem(4, zegarek);
		inv.setItem(5, melon);
		inv.setItem(6, chleb);
		
		inv.setItem(27, skoraHelmet);
		inv.setItem(28, skoraChestplate);
		inv.setItem(29, skoraLeggings);
		inv.setItem(30, skoraBoots);
		
		inv.setItem(32, chainHelmet);
		inv.setItem(33, chainChestplate);
		inv.setItem(34, chainLeggings);
		inv.setItem(35, chainBoots);
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
	public void onPlayerClickInventory(InventoryClickEvent e) throws NoSuchMethodException {
		try {
			String _ = e.getCurrentItem().getItemMeta().getDisplayName();
			Player player = (Player) e.getWhoClicked();
			if(!e.getInventory().getName().equalsIgnoreCase(inv.getName())) return;
			
			if(_.equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Drewniany Miecz")) {
				e.setCancelled(true);
				buyItem(player, Material.WOOD_SWORD, "Drewniany Miecz", 10);
				player.sendMessage(ChatColor.GOLD + "Pomyslnie zakupiono Drewniany Miecz! Kosztowalo Cie to 10 XP.");
			}
			if(_.equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Kamienny miecz")) {
				e.setCancelled(true);
				buyItem(player, Material.STONE_SWORD, "Kamienny miecz", 20);
				player.sendMessage(ChatColor.GOLD + "Pomyslnie zakupiono Kamienny Miecz! Kosztowalo Cie to 20 XP.");
			}
			if(_.equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Woda")) {
				e.setCancelled(true);
				buyItem(player, Material.getMaterial(373), "Woda", 60);
				player.sendMessage(ChatColor.GOLD + "Pomyslnie zakupiono Woda! Kosztowalo Cie to 60 XP.");
			}
			if(_.equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Bandaz")) {
				e.setCancelled(true);
				buyItem(player, Material.PAPER, "Bandaz", 70);
				player.sendMessage(ChatColor.GOLD + "Pomyslnie zakupiono Bandaz! Kosztowalo Cie to 70 XP.");
			}
			if(_.equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Zegarek")) {
				e.setCancelled(true);
				buyItem(player, Material.getMaterial(347), "Zegarek", 50);
				player.sendMessage(ChatColor.GOLD + "Pomyslnie zakupiono Zegarek! Kosztowalo Cie to 50 XP.");
			}
			if(_.equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Melon")) {
				e.setCancelled(true);
				buyItem(player, Material.MELON, "Melon", 40);
				player.sendMessage(ChatColor.GOLD + "Pomyslnie zakupiono Melon! Kosztowalo Cie to 40 XP.");
			}
			if(_.equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Chleb")) {
				e.setCancelled(true);
				buyItem(player, Material.BREAD, "Chleb", 55);
				player.sendMessage(ChatColor.GOLD + "Pomyslnie zakupiono Chleb! Kosztowalo Cie to 55 XP.");
			}
			
			if(_.equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Skorzany helm")) {
				e.setCancelled(true);
				buyItem(player, Material.LEATHER_HELMET, "Skorzany helm", 15);
				player.sendMessage(ChatColor.GOLD + "Pomyslnie zakupiono Zbroje! Kosztowalo Cie to 15 XP.");
			}
			if(_.equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Skorzany napiersnik")) {
				e.setCancelled(true);
				buyItem(player, Material.LEATHER_CHESTPLATE, "Skorzany napiersnik", 15);
				player.sendMessage(ChatColor.GOLD + "Pomyslnie zakupiono Zbroje! Kosztowalo Cie to 15 XP.");
			}
			if(_.equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Skorzane spodnie")) {
				e.setCancelled(true);
				buyItem(player, Material.LEATHER_LEGGINGS, "Skorzane spodnie", 15);
				player.sendMessage(ChatColor.GOLD + "Pomyslnie zakupiono Zbroje! Kosztowalo Cie to 15 XP.");
			}
			if(_.equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Skorzane buty")) {
				e.setCancelled(true);
				buyItem(player, Material.LEATHER_BOOTS, "Skorzane buty", 15);
				player.sendMessage(ChatColor.GOLD + "Pomyslnie zakupiono Zbroje! Kosztowalo Cie to 15 XP.");
			}
			
			if(_.equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Chainowy helm")) {
				e.setCancelled(true);
				buyItem(player, Material.CHAINMAIL_HELMET, "Chainowy helm", 30);
				player.sendMessage(ChatColor.GOLD + "Pomyslnie zakupiono Zbroje! Kosztowalo Cie to 30 XP.");
			}
			if(_.equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Chainowy napiersnik")) {
				e.setCancelled(true);
				buyItem(player, Material.CHAINMAIL_CHESTPLATE, "Chainowy napiersnik", 30);
				player.sendMessage(ChatColor.GOLD + "Pomyslnie zakupiono Zbroje! Kosztowalo Cie to 30 XP.");
			}
			if(_.equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Chainowe spodnie")) {
				e.setCancelled(true);
				buyItem(player, Material.CHAINMAIL_LEGGINGS, "Chainowe spodnie", 30);
				player.sendMessage(ChatColor.GOLD + "Pomyslnie zakupiono Zbroje! Kosztowalo Cie to 30 XP.");
			}
			if(_.equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Chainowe buty")) {
				e.setCancelled(true);
				buyItem(player, Material.CHAINMAIL_BOOTS, "Chainowe buty", 30);
				player.sendMessage(ChatColor.GOLD + "Pomyslnie zakupiono Zbroje! Kosztowalo Cie to 30 XP.");
			}

		} catch(NullPointerException ex) {}
	}
	
}
