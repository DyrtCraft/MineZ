package pl.themolka.minez;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFile {
	
	static MineZ plugin;
	
	public PropertiesFile(MineZ mineZ) {
		plugin = mineZ;
	}

	public static String get(String string) {
		API.debug("public static void get(String)");
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream(plugin.getDataFolder() + File.pathSeparator + "messages.properties"));
			
			String a = prop.getProperty(string);
			return a;
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		return string;
	}
	
	public static void write() {
		API.debug("public static void write()");
		Properties prop = new Properties();
		
		try {
			prop.setProperty("wooden-sword", "Drewniany miecz");
			prop.setProperty("stone-sword", "Kamienny miecz");
			prop.setProperty("iron-sword", "Zelazny miecz");
			
			prop.setProperty("bandage", "Bandaz");
			prop.setProperty("water", "Woda");
			prop.setProperty("clock", "Melon");
			prop.setProperty("melon", "Melon");
			prop.setProperty("bread", "Chleb");
			
			prop.setProperty("armor-leather-helmet", "Skorzany helm");
			prop.setProperty("armor-leather-chestplate", "Skorzany napiersnik");
			prop.setProperty("armor-leather-leggins", "Skorzane spodnie");
			prop.setProperty("armor-leather-boots", "Skorzane buty");
			
			prop.setProperty("armor-chain-helmet", "Chainowy helm");
			prop.setProperty("armor-chain-chestplate", "Chainowy napiersnik");
			prop.setProperty("armor-chain-leggins", "Chainowe spodnie");
			prop.setProperty("armor-chain-boots", "Skorzane buty");
			
			prop.setProperty("armor-iron-helmet", "Zelazny helm");
			prop.setProperty("armor-iron-chestplate", "Zelazny napiersnik");
			prop.setProperty("armor-iron-leggins", "Zelazne spodnie");
			prop.setProperty("armor-iron-boots", "Zelazne buty");
			
			prop.store(new FileOutputStream(plugin.getDataFolder() + File.pathSeparator + "messages.properties"), null);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
