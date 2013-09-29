package pl.themolka.minez;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;

/**
 * @author TheMolkaPL
 * @since Development Build 001
 * @see MineZ#isDyrtCraftXPEnabled()
 * @see MineZ#sendStarterKit(Player)
 * @see MineZ#sendStarterVIPKit(Player)
 */
public class MineZ extends JavaPlugin {
	
	protected static MineZ plugin;
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new pl.themolka.minez.Sklep(this), this);
		getServer().getPluginManager().registerEvents(new pl.themolka.minez.listeners.BandazListener(this), this);
		getServer().getPluginManager().registerEvents(new pl.themolka.minez.listeners.CreatureSpawnListener(this), this);
		getServer().getPluginManager().registerEvents(new pl.themolka.minez.listeners.Cuboid(this), this);
		getServer().getPluginManager().registerEvents(new pl.themolka.minez.listeners.EntityDeath(this), this);
		getServer().getPluginManager().registerEvents(new pl.themolka.minez.listeners.PlayerJoinAndQuitListener(this), this);
		getServer().getPluginManager().registerEvents(new pl.themolka.minez.SignsManager(this), this);
		
		getCommand("minez").setExecutor(new pl.themolka.minez.MineZCommand(this));
		
		saveDefaultConfig();
		
		if(!(MineZ.isDyrtCraftXPEnabled())) {
			getLogger().warning("Do pelnego dzialania tego pluginu potrzeby jest plugin DyrtCraftXP!");
		}
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
		List<String> lista_swiatow = plugin.getConfig().getStringList("swiaty-minez");
		return lista_swiatow;
	}
	
	/**
	 * @author TheMolkaPL
	 * @since Development Build 012
	 * @see MineZ#getMineZWorlds()
	 * @return String - nazwa swiata na ktorym znajduje sie spawn
	 */
	public static String getSpawnWorld() {
		String spawn_world = plugin.getConfig().getString("swiat-spawn");
		return spawn_world;
	}
	
	/**
	 * @author TheMolkaPL
	 * @since Development Build 001
	 * @see DyrtCraftXP
	 * @return true Jezeli plugin {@link DyrtCraftXP} jest wlaczony
	 */
	public static boolean isDyrtCraftXPEnabled() {
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
		if(plugin.getConfig().getBoolean("chron-spawn") == true) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isPoradyEnabled(Player player) {
		return true;
	}
	
	public static void sendStarterKit(Player player) {
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
		// Randomowa teleportacja na mape
		/*
		 * TODO: Randomowa teleportacja
		 */
		
		// Glod
		player.setFoodLevel(20);
		player.setGameMode(GameMode.ADVENTURE);
		player.setHealth(20.0);
		
		// Porada
		player.sendMessage(ChatColor.GOLD + "========== Porada ==========");
		player.sendMessage(ChatColor.GRAY + "Zostales przeteleportowany losowo na mape.");
		player.sendMessage(ChatColor.GRAY + "Witaj! Wlasnie rozpoczales walke o przetrwanie!");
		player.sendMessage(ChatColor.GRAY + "Zdobywaj itemy w wioskach, walcz z Zombie, przetrwaj!");
		player.sendMessage(ChatColor.GRAY + "- - - - - > " + ChatColor.BOLD + "Dobrej zabawy! :D");
		
		// Itemy
		if(player.hasPermission("minez.vip") || player.isOp()) {
			MineZ.sendStarterVIPKit(player);
		}
		MineZ.sendStarterKit(player);
	}
	
}
