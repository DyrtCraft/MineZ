package pl.themolka.minez;

import org.bukkit.entity.Player;
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
	
	protected String authors = "TheMolkaPL";
	protected String version = "Development Build 019";
	
	private JavaPlugin javaPlugin;
	private MineZ plugin;
	
	@Override
	public void onDisable() { shutdown(); }
	
	@Override
	public void onLoad() { startup(); }
	
	/**
	 * @author TheMolkaPL
	 * @since Development Build 019
	 * @see API#getPluginVersion()
	 * @return String - autorzy pluginu
	 */
	public String getAuthors() {
		API.debug("public static String getPluginAuthors()");
		return authors;
	}
	
	/**
	 * @author TheMolkaPL
	 * @since Development Build 013
	 * @see API#getPluginAuthors()
	 * @return String - wersja pluginu
	 */
	public String getVersion() {
		API.debug("public static String getPluginVersion()");
		return version;
	}
	
	/**
	 * @author TheMolkaPL
	 * @since Development Build 019
	 */
	public void shutdown() {
		javaPlugin.getLogger().info("Wylaczanie MineZ wersja " + API.getPlugin().getVersion() + " by " + API.getPlugin().getAuthors() + "...");
		javaPlugin.getLogger().info("Zapisywanie pliku config.yml...");
		javaPlugin.saveConfig();
		javaPlugin.getLogger().info("Pomyslnie zapisano plik config.yml!");
		javaPlugin.getLogger().info("");
		javaPlugin.getLogger().info("Pomyslnie wylaczono plugin MineZ, zyczymy milego dnia!");
		javaPlugin.getPluginLoader().disablePlugin(this);
	}
	
	/**
	 * @author TheMolkaPL
	 * @since Development Build 019
	 */
	public void startup() {
		javaPlugin.saveDefaultConfig();
		javaPlugin.getCommand("minez").setExecutor(new pl.themolka.minez.MineZCommand(plugin));
		API.registerListeners();
		
		if(!(API.isDyrtCraftXPEnabled())) {
			javaPlugin.getLogger().warning("Do pelnego dzialania tego pluginu potrzeby jest plugin DyrtCraftXP!");
		} else {
			javaPlugin.getLogger().info("Wykryto plugin DyrtCraftXP wersja " + DyrtCraftXP.getInstance().getVersion() + " by " + DyrtCraftXP.getInstance().getAuthors());
			javaPlugin.getLogger().info("Wspolpraca z pluginem DyrtCraft...");
		}
	}
	
}
