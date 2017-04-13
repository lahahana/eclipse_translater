package translater.config;

import java.util.LinkedHashMap;
import java.util.Map;

public class Config {

	public static Map<String, String> langMapping = new LinkedHashMap<String, String>();
	
	static {
		langMapping.put("Engilsh", "en");
		langMapping.put("Chinese", "zh-CN");
		langMapping.put("Japanese", "ja");
		langMapping.put("Korea", "ko");
		langMapping.put("French", "fr");
		langMapping.put("Dutch", "nl");
		langMapping.put("German", "de");
		langMapping.put("Spanish", "es-ES"); 
		langMapping.put("Portuguese", "pt-PT");
	}
	
}
