package pl.themolka.minez;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import pl.DyrtCraft.DyrtCraftXP.CraftDyrt;

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
	
	public int configTokenV1 = 414735131;
	private String authors = "TheMolkaPL";
	private String version = "Development Build 031";
	
	@Override
	public void onDisable() {
		System.out.println("Wylaczanie MineZ wersja " + API.getPlugin().getVersion() + " by " + API.getPlugin().getAuthors() + "...");
		System.out.println("Zapisywanie pliku config.yml...");
		saveConfig();
		System.out.println("Pomyslnie zapisano plik config.yml!");
		System.out.println("");
		System.out.println("Pomyslnie wylaczono plugin MineZ, zyczymy milego dnia!");
		getPluginLoader().disablePlugin(this);
	}
	
	@Override
	public void onEnable() {
		getPluginLoader().enablePlugin(this);
		// Komendy
		getCommand("minez").setExecutor(new pl.themolka.minez.MineZCommand(this));
		
		// Komendy DyrtCraftMineZ
		getCommand("dolacz").setExecutor(new pl.dyrtcraft.dyrtcraftminez.commands.DolaczCommand(this));
		getCommand("mapa").setExecutor(new pl.dyrtcraft.dyrtcraftminez.commands.MapaCommand(this));
		getCommand("regulamin").setExecutor(new pl.dyrtcraft.dyrtcraftminez.commands.RegulaminCommand(this));
		getCommand("sklep").setExecutor(new pl.dyrtcraft.dyrtcraftminez.commands.SklepCommand(this));
		getCommand("vip").setExecutor(new pl.dyrtcraft.dyrtcraftminez.commands.VipCommand(this));
		getCommand("zabij").setExecutor(new pl.dyrtcraft.dyrtcraftminez.commands.ZabijCommand(this));
		
		// Properties
		File propFile = new File(getDataFolder(), "messages.properties");
		if(!propFile.exists()) {
			//PropertiesFile.write();
			try {
				propFile.createNewFile();
			} catch (IOException e) {}
		}
		
		// Rejestracja listenerow
		getLogger().info("Rejestrowanie listenerów...");
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
		API.log("Zarejestrowano listenery!");
		
		// Rejestracja listenerów DyrtCraftMineZ
		Bukkit.getPluginManager().registerEvents(new pl.dyrtcraft.dyrtcraftminez.Listeners(this), this);
		API.log("Zarejestrowano listenery DyrtCraftMineZ");
		
		// Config
		getConfig().options().copyDefaults(true);
		getConfig().options().copyHeader(true);
		if(API.checkConfigToken()) {
			getLogger().warning("Twoja wersja pliku config.yml znajdujaca sie w plugins/MineZ jest przestarzala!");
			getLogger().warning("Nadpisywanie nowego pliku...");
			File file = new File(getDataFolder(), "config.yml");
			file.delete();
			getConfig().options().copyDefaults(true);
			getConfig().options().copyHeader(true);
			try {
				getConfig().save("config.yml");
			} catch (IOException e) {}
			getLogger().info("Plik conifg.yml zostal podmieniony!");
			
		}
		
		// Plugin DyrtCraftXP
		if(!(API.isDyrtCraftXPEnabled())) {
			getLogger().warning("Do pelnego dzialania tego pluginu potrzeby jest plugin DyrtCraftXP!");
			return;
		} else {
			API.log("Wykryto plugin " + CraftDyrt.getPluginFullName());
			API.log("Wspolpraca z pluginem DyrtCraft...");
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
		return configTokenV1;
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
