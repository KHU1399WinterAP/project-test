package main.java.config;

import main.java.enums.LanguageKey;
import main.java.models.Language;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class LanguageConfig {
	public static final HashMap<LanguageKey, Language> LANGUAGES = new HashMap<>(
			Map.of(LanguageKey.EN, new Language("src/main/language/en.properties"),
					LanguageKey.FA, new Language("src/main/language/fa.properties"))
	);
	
	public static LanguageKey currentLanguageKey = LanguageKey.EN;
	
	public static void init() {
		try {
			for (var key : LANGUAGES.keySet()) {
				var language = LANGUAGES.get(key);
				var input = new FileInputStream(language.PATH);
				
				language.setProperties(new Properties());
				language.getProperties().load(input);
				
				input.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getWord(String key) {
		var word = LANGUAGES.get(currentLanguageKey).getProperties().getProperty(key);
		
		if (word.startsWith("&"))
			word = word.substring(1);
		
		return word;
	}
}
