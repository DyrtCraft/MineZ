package pl.themolka.minez;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;
import pl.themolka.minez.listeners.BandazListener;
import pl.themolka.minez.listeners.CreatureSpawnListener;
import pl.themolka.minez.listeners.Cuboid;
import pl.themolka.minez.listeners.EntityDeathListener;
import pl.themolka.minez.listeners.ExpAndLevelChangeListener;
import pl.themolka.minez.listeners.PlayerJoinAndQuitListener;
import pl.themolka.minez.listeners.PlayerTeleportListener;
import pl.themolka.minez.listeners.WodaListener;

/**
 * @author TheMolkaPL
 * @since Development Build 001
 * @see MineZ#isDyrtCraftXPEnabled()
 * @see MineZ#sendStarterKit(Player)
 * @see MineZ#sendStarterVIPKit(Player)
 */
public class MineZ extends JavaPlugin {
	
	private int token = 414735131;
	private String authors = "TheMolkaPL";
	private String version = "Development Build 021";
	
	@Override
	public void onDisable() {
		getLogger().info("Wylaczanie MineZ wersja " + API.getPlugin().getVersion() + " by " + API.getPlugin().getAuthors() + "...");
		getLogger().info("Zapisywanie pliku config.yml...");
		saveConfig();
		getLogger().info("Pomyslnie zapisano plik config.yml!");
		getLogger().info("");
		getLogger().info("Pomyslnie wylaczono plugin MineZ, zyczymy milego dnia!");
		getPluginLoader().disablePlugin(this);
	}
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		getCommand("minez").setExecutor(new pl.themolka.minez.MineZCommand(this));
		
		Bukkit.getPluginManager().registerEvents(new Sklep(this), this);
		Bukkit.getPluginManager().registerEvents(new BandazListener(this), this);
		Bukkit.getPluginManager().registerEvents(new CreatureSpawnListener(this), this);
		Bukkit.getPluginManager().registerEvents(new Cuboid(this), this);
		Bukkit.getPluginManager().registerEvents(new EntityDeathListener(this), this);
		Bukkit.getPluginManager().registerEvents(new ExpAndLevelChangeListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoinAndQuitListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerTeleportListener(this), this);
		Bukkit.getPluginManager().registerEvents(new WodaListener(this), this);
		Bukkit.getPluginManager().registerEvents(new SignsManager(this), this);
		getLogger().info("Zarejestrowano listenery");
		
		/*if(!(API.checkConfigToken())) {
			getConfig().options().copyDefaults(true);
		}*/
		if(!(API.isDyrtCraftXPEnabled())) {
			getLogger().warning("Do pelnego dzialania tego pluginu potrzeby jest plugin DyrtCraftXP!");
		} else {
			getLogger().info("Wykryto plugin DyrtCraftXP wersja " + DyrtCraftXP.getInstance().getVersion() + " by " + DyrtCraftXP.getInstance().getAuthors());
			getLogger().info("Wspolpraca z pluginem DyrtCraft...");
		}
	}
	
	/**
	 * @author TheMolkaPL
	 * @since Development Build 019
	 * @see API#getPluginVersion()
	 * @return String - autorzy pluginu
	 */
	public String getAuthors() {
		API.debug("public String getPluginAuthors()");
		return authors;
	}
	
	/**
	 * @author TheMolkaPL
	 * @since Development Build 020
	 * @see API#checkConfigToken()
	 * @see MineZ#getConfig()
	 * @return int - Kod Token pliku config
	 */
	public int getConfigToken() {
		API.debug("public int getConfigToken()");
		return token;
	}
	
	/**
	 * @author TheMolkaPL
	 * @since Development Build 013
	 * @see API#getPluginAuthors()
	 * @return String - wersja pluginu
	 */
	public String getVersion() {
		API.debug("public String getPluginVersion()");
		return version;
	}
	
	/**
	 * @author TheMolkaPL
	 * @since Development Build 020
	 * @see MineZ#onDisable()
	 */
	public void shutdown() {
		onDisable();
	}
	
}
