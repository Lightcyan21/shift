package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

	
	private Properties props;
	private File configFile;
	
	private static ConfigManager instance;
	
	public static final String CONF_CLIENTA_RECIPIENT = "CONF_CLIENTA_RECIPIENT";
	public static final String CONF_CLIENTA_AUTHOR = "CONF_CLIENTA_AUTHOR";
	
	
	public ConfigManager(){
		initProperties();
	}
	
	public static ConfigManager getInstance(){
		if(instance == null){
			instance = new ConfigManager();
		}
		return instance;
	}
	
	
	public String getProperty(String key){
		return props.getProperty(key);
	}
	
	public void initProperties() {
		if (props == null) {
			props = new Properties();
			
			configFile = new File("config.ini");
			
			if(configFile.exists())
				try {
					props.load(new FileInputStream("config.ini"));
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
