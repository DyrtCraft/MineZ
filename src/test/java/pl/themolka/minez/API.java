package pl.themolka.minez;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftPlugin;
import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;

/**
 * @author TheMolkaPL
 * @since Development Build 019
 * @see {@link MineZ}
 */
public class API extends MineZ {

	private static MineZ plugin;
	
	private static MineZCommand commands;
	private static Scoreboard scoreboard;
	private static Sklep sklep;
	
	public API(MineZ mineZ) {
		plugin = mineZ;
	}
	
	/**
	 * @author TheMolkaPL
	 * @since Development Build 019
	 * @return {@link MineZCommand}
	 */
	public static MineZCommand getCommands() {
		return commands;
	}
	
	/**
	 * @author TheMolkaPL
	 * @since Development Build 019
	 * @return {@link MineZ}
	 */
	public static MineZ getPlugin() {
		return plugin;
	}
	
	/**
	 * @author TheMolkaPL
	 * @since Development Build 019
	 * @return {@link Scoreboard}
	 */
	public static Scoreboard getScoreboard() {
		return scoreboard;
	}
	
	/**
	 * @author TheMolkaPL
	 * @since Development Build 019
	 * @return {@link Sklep}
	 */
	public static Sklep getSklep() {
		return sklep;
	}
	
	/**
	 * @author TheMolkaPL
	 * @since Development Build 020
	 * @see MineZ#getConfig()
	 * @see MineZ#getConfigToken()
	 * @return true Jezeli haslo token jest prawdziwe
	 */
	public static boolean checkConfigToken() {
		API.debug("public static boolean checkConfigToken()");
		if(API.getInstance().configTokenV1 == API.getInstance().getConfig().getInt("CONFIG-TOKEN")) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @author TheMolkaPL
	 * @since Development Build 015
	 * @see API#log(String)
	 * @param debug
	 */
	public static void debug(String msg) {
		try {
			//if(plugin.getConfig().getBoolean("debug") == false) { /*Debug jest wlaczony*/ }
			Bukkit.getLogger().info("[MineZ] " + msg);
		} catch(NullPointerException ex) { /*Blad pliku config.yml*/ }
	}
	
	public static void disablePorady(Player player) {}
	
	public static void enablePorady(Player player) {}
	
	/**
	 * @author TheMolkaPL
	 * @return 
	 * @since Development Build 012
	 */
	public static MineZ getInstance() {
		return plugin;
	}
	
	/**
	 * @author TheMolkaPL
	 * @since Development Build 012
	 * @see MineZ#getSpawnWorld()
	 * @return Lista swiatow MineZ
	 */
	public static List<String> getMineZWorlds() {
		API.debug("public static List<String> getMineZWorlds()");
		try {
			List<String> lista_swiatow = plugin.getConfig().getStringList("swiaty-minez");
			return lista_swiatow;
		} catch(NullPointerException ex) {}
		return null;
	}
	
	/**
	 * @author TheMolkaPL
	 * @since Development Build 022
	 * @see API#debug(String)
	 * @param msg Log
	 */
	public static void log(String msg) {
		API.debug("public static void log(String)");
		Bukkit.getLogger().log(null, msg);
	}
	
	/**
	 * @author TheMolkaPL
	 * @since Development Build 012
	 * @see MineZ#getMineZWorlds()
	 * @return String - nazwa swiata na ktorym znajduje sie spawn
	 */
	public static String getSpawnWorldName() {
		API.debug("public static String getSpawnWorldName()");
		try {
			String spawn_world = plugin.getConfig().getString("swiat-spawn");
			return spawn_world;
		} catch(NullPointerException ex) {
			if(API.isDyrtCraftXPEnabled()) {
				DyrtCraftPlugin.sendMsgToOp("Nie znaleziono nazwy swiata spawnu - bledny plik config.yml", 1);
			}
		}
		return null;
	}
	
	public static ItemStack head(String owner) {
		ItemStack head = new ItemStack(Material.SKULL_ITEM, 1);
		SkullMeta headMeta = (SkullMeta) head.getItemMeta();
		headMeta.setDisplayName(ChatColor.GOLD + "Glowa gracza " + owner);
		headMeta.setOwner(owner);
		head.setItemMeta(headMeta);
		return head;
	}
	
	/**
	 * @author TheMolkaPL
	 * @since Development Build 001
	 * @see DyrtCraftXP
	 * @see API#isDyrtCraftSpawnEnabled()
	 * @return true Jezeli plugin {@link DyrtCraftXP} jest wlaczony
	 */
	public static boolean isDyrtCraftXPEnabled() {
		API.debug("public static boolean isDyrtCraftXPEnabled()");
		if(Bukkit.getPluginManager().isPluginEnabled("DyrtCraftXP")) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @author TheMolkaPL
	 * @since Development Build 012
	 * @return true Jezeli chron-spawn jest wlaczone w pliku config.yml
	 */
	public static boolean isSpawnProtected() {
		API.debug("public static boolean isSpawnProtected()");
		if(plugin.getConfig().getBoolean("chron-spawn") == true) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isPoradyEnabled(Player player) { return true; }
	
	public static void sendStarterKit(Player player) {
		API.debug("public static void sendStarterKit(Player)");
		
		// Drewniany miecz
		ItemStack miecz = new ItemStack(Material.WOOD_SWORD);
		ItemMeta mieczMeta = miecz.getItemMeta();
		mieczMeta.setDisplayName(ChatColor.GOLD + "Drewniany miecz");
		miecz.setItemMeta(mieczMeta);
		
		// Butelka wody
		ItemStack butelka = new ItemStack(Material.getMaterial(373));
		ItemMeta butelkaMeta = butelka.getItemMeta();
		butelkaMeta.setDisplayName(ChatColor.GOLD + "Woda");
		butelka.setItemMeta(butelkaMeta);
		
		// Bandaz (papier)
		ItemStack bandaz = new ItemStack(Material.PAPER);
		ItemMeta bandazMeta = bandaz.getItemMeta();
		bandazMeta.setDisplayName(ChatColor.GOLD + "Bandaz");
		bandaz.setItemMeta(bandazMeta);
		
		// Armor
		ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
		short a = 40;
		helmet.setDurability(a);
		ItemMeta helmetMeta = helmet.getItemMeta();
		helmetMeta.setDisplayName(ChatColor.GOLD + "");
		helmet.setItemMeta(helmetMeta);
		
		ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		short b = 45;
		chestplate.setDurability(b);
		ItemMeta chestplateMeta = chestplate.getItemMeta();
		chestplateMeta.setDisplayName(ChatColor.GOLD + "");
		chestplate.setItemMeta(chestplateMeta);
		
		ItemStack leggins = new ItemStack(Material.LEATHER_LEGGINGS);
		short c = 45;
		leggins.setDurability(c);
		ItemMeta legginsMeta = leggins.getItemMeta();
		legginsMeta.setDisplayName(ChatColor.GOLD + "");
		leggins.setItemMeta(legginsMeta);
		
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		short d = 20;
		boots.setDurability(d);
		ItemMeta bootsMeta = boots.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.GOLD + "");
		boots.setItemMeta(bootsMeta);
		
		// Dostawanie itemow
		player.getInventory().addItem(miecz);
		player.getInventory().addItem(butelka);
		player.getInventory().addItem(bandaz);
		
		// Dostawanie armoru
		player.getInventory().setHelmet(helmet);
		player.getInventory().setChestplate(chestplate);
		player.getInventory().setLeggings(leggins);
		player.getInventory().setBoots(boots);
	}
	
	public static void sendStarterVIPKit(Player player) {
		API.debug("public static void sendStarterVIPKit(Player)");
		
		// Kamienny miecz
		ItemStack miecz = new ItemStack(Material.STONE_SWORD);
		ItemMeta mieczMeta = miecz.getItemMeta();
		mieczMeta.setDisplayName(ChatColor.GOLD + "Kamienny miecz");
		miecz.setItemMeta(mieczMeta);
		
		// Butelka wody
		ItemStack butelka = new ItemStack(Material.getMaterial(373));
		ItemMeta butelkaMeta = butelka.getItemMeta();
		butelkaMeta.setDisplayName(ChatColor.GOLD + "Woda");
		butelka.setItemMeta(butelkaMeta);
		
		// Bandaz (papier)
		ItemStack bandaz = new ItemStack(Material.PAPER);
		ItemMeta bandazMeta = bandaz.getItemMeta();
		bandazMeta.setDisplayName(ChatColor.GOLD + "Bandaz");
		bandaz.setItemMeta(bandazMeta);
		
		// Chleb
		ItemStack chleb = new ItemStack(Material.BREAD);
		ItemMeta chlebMeta = chleb.getItemMeta();
		chlebMeta.setDisplayName(ChatColor.GOLD + "Chleb");
		chleb.setItemMeta(chlebMeta);
		
		// Armor
		ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
		ItemMeta helmetMeta = helmet.getItemMeta();
		helmetMeta.setDisplayName(ChatColor.GOLD + "");
		helmet.setItemMeta(helmetMeta);
		
		ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemMeta chestplateMeta = chestplate.getItemMeta();
		chestplateMeta.setDisplayName(ChatColor.GOLD + "");
		chestplate.setItemMeta(chestplateMeta);
		
		ItemStack leggins = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemMeta legginsMeta = leggins.getItemMeta();
		legginsMeta.setDisplayName(ChatColor.GOLD + "");
		leggins.setItemMeta(legginsMeta);
		
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		ItemMeta bootsMeta = boots.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.GOLD + "");
		boots.setItemMeta(bootsMeta);
		
		// Dostawanie itemow
		player.getInventory().addItem(Sklep.getSklepItem());
		
		player.getInventory().addItem(miecz);
		player.getInventory().addItem(butelka);
		
		player.getInventory().addItem(bandaz);
		player.getInventory().addItem(bandaz);
		player.getInventory().addItem(bandaz);
		player.getInventory().addItem(bandaz);
		player.getInventory().addItem(bandaz);
		
		player.getInventory().addItem(chleb);
		player.getInventory().addItem(chleb);
		player.getInventory().addItem(chleb);
		player.getInventory().addItem(chleb);
		player.getInventory().addItem(chleb);
		
		// Dostawanie armoru
		player.getInventory().setHelmet(helmet);
		player.getInventory().setChestplate(chestplate);
		player.getInventory().setLeggings(leggins);
		player.getInventory().setBoots(boots);
	}
	
	/**
	 * @author TheMolkaPL
	 * @since Development Build 012
	 * @param player Gracz ktory ma zostac zespawnowany
	 * @param world Swiat w ktorym player ma zostac zespawnowany
	 */
	public static void spawnPlayer(Player player, String world) {
		API.debug("public static void spawnPlayer(Player, String)");
		
		// Randomowa teleportacja na mape
		/*
		 * TODO: Randomowa teleportacja
		 */
		/*World swiat = null;
		int x = (Integer) null;
		int y = (Integer) null;
		int z = (Integer) null;
		Random random = new Random();
		
		try {
			swiat = Bukkit.getWorld(world);
			x = random.nextInt();
			y = random.nextInt();
			z = random.nextInt();
		} catch(NullPointerException ex) {
			player.sendMessage(ChatColor.RED + "Nie odnaleziono mapy o nazwie \"" + world + "\"!");
		}
		
		Location location = new Location(swiat, x, y, z);
		player.teleport(location);*/
		Location location = new Location(Bukkit.getWorld(world), 0, 64, 0);
		player.teleport(location);
		
		// Exp & Lvl
		float exp = 1;
		player.setExp(exp);
		player.setLevel(20);
		
		// Scoreboard
		Scoreboard.setScoreboard(player);
		
		// Glod
		player.setCanPickupItems(true);
		player.setFoodLevel(20);
		player.setGameMode(GameMode.ADVENTURE);
		player.setHealth(20.0);
		
		// Porada
		player.sendMessage(ChatColor.GOLD + "========== Porada ==========");
		player.sendMessage(ChatColor.GRAY + "Zostales przeteleportowany losowo na mapie " + Bukkit.getWorld(world) + ".");
		player.sendMessage(ChatColor.GRAY + "Witaj! Wlasnie rozpoczales walke o przetrwanie!");
		player.sendMessage(ChatColor.GRAY + "Zdobywaj itemy w wioskach, walcz z Zombie, przetrwaj!");
		player.sendMessage(ChatColor.GRAY + "- - - - - > " + ChatColor.BOLD + "Dobrej zabawy! :D");
		
		// Itemy
		if(player.hasPermission("minez.vip") || player.isOp()) {
			API.sendStarterVIPKit(player);
			return;
		} else {
			API.sendStarterKit(player);
			return;
		}
	}
	
}
